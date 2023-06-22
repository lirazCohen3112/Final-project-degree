package com.example.thefinalproject.businessManager.financialManagement;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentsListMovement extends Fragment{

    private RecyclerView main_LST_list_movement;
    private ArrayList<Movement> movements;
    private MovementAdapter movementAdapter;
    private ShapeableImageView title_image;
    private TextView account_EDT_balance;
    private FrameLayout table_records_LAY_list;
    private RadioGroup movement_RG_typeMovement;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String optionMovement;
    private TextInputLayout movement_EDT_size;
    private TextInputLayout movement_EDT_description;
    private ImageView movement_LBL_add_movement;
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list_movement, container, false);
        database = FirebaseDatabase.getInstance();

        findViews();

        readDataBalance();

        readData();
        movement_LBL_add_movement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditProfileDialog();
            }
        });

        Show();

        return view;
    }

    public void Show() {
        movements = Account.getMovementList();
        movementAdapter = new MovementAdapter(this, movements);
        main_LST_list_movement.setLayoutManager(new LinearLayoutManager(getContext()));
        main_LST_list_movement.setAdapter(movementAdapter);
    }

    private void findViews() {

        main_LST_list_movement = view.findViewById(R.id.main_LST_list_movement);
        account_EDT_balance = view.findViewById(R.id.account_EDT_balance);
        movement_RG_typeMovement = view.findViewById(R.id.movement_RG_typeMovement);
        movement_EDT_size = view.findViewById(R.id.movement_EDT_size);
        movement_EDT_description = view.findViewById(R.id.movement_EDT_description);
        movement_LBL_add_movement = view.findViewById(R.id.movement_LBL_add_movement);
        title_image = view.findViewById(R.id.title_image);
    }

    private void openEditProfileDialog() {
        Dialog editProfileDialog = new Dialog(getContext());
        editProfileDialog.setContentView(R.layout.add_movemennt_dialog); // Replace with your custom layout

        // Find views inside the dialog
        RadioGroup movement_RG_typeMovement = editProfileDialog.findViewById(R.id.movement_RG_typeMovement);
        EditText movement_EDT_size = editProfileDialog.findViewById(R.id.movement_EDT_size);
        EditText movement_EDT_description = editProfileDialog.findViewById(R.id.movement_EDT_description);
        MaterialButton movement_LBL_add_movement = editProfileDialog.findViewById(R.id.movement_LBL_add_movement);

        movement_RG_typeMovement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.movement_OPT_expense) {
                    optionMovement = "expense";
                    // title_image.setImageResource(R.drawable.icon_minus);;
                } else if (checkedId == R.id.movement_OPT_revenue) {
                    optionMovement = "revenue";
                    //     title_image.setImageResource(R.drawable.icon_plus);
                }
            }
        });

        // Handle button clicks inside the dialog
        movement_LBL_add_movement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("movements").push();

                Movement movement = new Movement(optionMovement , movement_EDT_size.getText().length(), String.valueOf(movement_EDT_description.getText()));
                Account.add(movement);

                myRef.setValue(movement);

                // Show the dialog
                editProfileDialog.show();
                editProfileDialog.dismiss();
            }
        });
        editProfileDialog.show();
    }

    private void readDataBalance() {
        DatabaseReference balanceRef = FirebaseDatabase.getInstance().getReference("balance");
        balanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int balance = dataSnapshot.getValue(Integer.class);
                    Account.setBalance(balance);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle onCancelled if needed
            }
        });
    }

    private void readData() {

        myRef = database.getReference("movement");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Account.clear();
                for (DataSnapshot m :  dataSnapshot.getChildren())
                {
                    Account.add(m.getValue(Movement.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
