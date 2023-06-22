package com.example.thefinalproject.businessManager.ManagementCareTypes;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.R;
import com.example.thefinalproject.businessManager.customerManagement.CoustumerList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentsListCare extends Fragment {
    private RecyclerView main_LST_list_care;
    private ArrayList<Care> cares;
    private CareAdapter careAdapter;
    private ImageView button_add_care;

    private FirebaseDatabase database;

    private DatabaseReference myRef;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_care, container, false);

        database = FirebaseDatabase.getInstance();

        readData();

        main_LST_list_care = view.findViewById(R.id.main_LST_list_care);
        button_add_care = view.findViewById(R.id.button_add_care);

        button_add_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditProfileDialog();
            }
        });

        Show();

        return view;
    }

    public void Show() {
        cares = CareList.getCareList();
        careAdapter = new CareAdapter(this, cares);
        main_LST_list_care.setLayoutManager(new LinearLayoutManager(getContext()));
        main_LST_list_care.setAdapter(careAdapter);
    }

    private void openEditProfileDialog() {
        Dialog editProfileDialog = new Dialog(requireContext());
        editProfileDialog.setContentView(R.layout.add_care_dialog);

        // Find views inside the dialog
        EditText dialog_care_name = editProfileDialog.findViewById(R.id.dialog_care_name);
        EditText dialog_explanation = editProfileDialog.findViewById(R.id.dialog_explanation);
        EditText dialog_care_cost = editProfileDialog.findViewById(R.id.dialog_care_cost);
        Button button_add = editProfileDialog.findViewById(R.id.button_add);

        // Handle button clicks inside the dialog
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("cares").push();

                Care care = new Care(String.valueOf(dialog_care_name.getText()),
                        String.valueOf(dialog_explanation.getText()), String.valueOf(dialog_care_cost.getText()));
                CareList.add(care);

                myRef.setValue(care);

                editProfileDialog.show();
                editProfileDialog.dismiss();
            }
        });

        editProfileDialog.show();
    }

    private void readData() {
        myRef = database.getReference("cares");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CareList.clear();
                for (DataSnapshot m :  dataSnapshot.getChildren())
                {
                    CareList.add(m.getValue(Care.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
