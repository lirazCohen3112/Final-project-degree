package com.example.thefinalproject.client.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thefinalproject.R;
import com.example.thefinalproject.businessManager.customerManagement.Coustumer;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FrameLayout table_records_LAY_list;

    private ImageButton customer_LBL_Return_menu;
    private TextView profile_LBL_name;
    private TextView profile_LBL_email;

    private FirebaseDatabase database;

    private DatabaseReference myRef;

    private Coustumer coustumer;

    private Button customer_LBL_Edit_profile;
    private String phoneNumText;
    private TextView profile_LBL_phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_activity_profil);

        database = FirebaseDatabase.getInstance();

        phoneNumText = getIntent().getStringExtra("phoneNumText");

        findViews();

        readData(phoneNumText, new profileCallback() {
            @Override
            public void onResult(Coustumer coustumer) {
                    if (coustumer != null) {
               //         System.out.println("yyyyyyyyyy" + coustumer.getName());
                //        System.out.println("yyyyyyyyy" + phoneNumText);
                        profile_LBL_name.setText(coustumer.getName());
                        profile_LBL_phoneNum.setText(phoneNumText);
                        profile_LBL_email.setText(coustumer.getEmail());
                    }
            }
        });

        customer_LBL_Return_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu();
            }
        });

        customer_LBL_Edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditProfileDialog();
            }
        });
    }

    private void findViews() {
        table_records_LAY_list = findViewById(R.id.table_records_LAY_list);
        profile_LBL_name = findViewById(R.id.table_records_LBL_name);
        profile_LBL_email = findViewById(R.id.table_records_LBL_email);
        profile_LBL_phoneNum = findViewById(R.id.table_records_LBL_phoneNum);
        customer_LBL_Return_menu = findViewById(R.id.customer_LBL_Return_menu);
        customer_LBL_Edit_profile = findViewById(R.id.customer_LBL_Edit_profile);

    }

    private void openEditaprofile(){
    //    Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
    //    startActivity(intent);
     //   finish();
    }

    public void openMenu() {
        Intent intent = new Intent(ProfileActivity.this, ClientMainActivity.class);
        startActivity(intent);
        finish();
    }

    public void readData(String phoneNum , profileCallback profileCallback) {

        myRef = database.getReference("customers");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot c :  dataSnapshot.getChildren())
                {
                    int cc =   c.getValue(Coustumer.class).getPhoneNum();
                    if(cc == Integer.parseInt(phoneNum)){
                        coustumer = c.getValue(Coustumer.class);
                    }
                }
                profileCallback.onResult(coustumer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                profileCallback.onResult(coustumer );
            }
        });
    }

    interface profileCallback {
        void onResult(Coustumer coustumer);
    }

    private void openEditProfileDialog() {
        Dialog editProfileDialog = new Dialog(this);
        editProfileDialog.setContentView(R.layout.edit_profile_dialog); // Replace with your custom layout

        // Find views inside the dialog
        Button button_Change_name = editProfileDialog.findViewById(R.id.button_Change_name);
        Button button_Change_email = editProfileDialog.findViewById(R.id.button_Change_email);
        Button button_closed = editProfileDialog.findViewById(R.id.button_closed);
        EditText text_Change_name = editProfileDialog.findViewById(R.id.text_Change_name);
        EditText text_Change_email = editProfileDialog.findViewById(R.id.text_Change_email);

        // Handle button clicks inside the dialog
        button_Change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef = database.getReference("customers");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot c :  dataSnapshot.getChildren())
                        {
                            int cc =   c.getValue(Coustumer.class).getPhoneNum();
                            if(cc == Integer.parseInt(phoneNumText)){
                                Coustumer coustumer = c.getValue(Coustumer.class);
                                coustumer.setName(String.valueOf(text_Change_name.getText()));
                                myRef.setValue(coustumer);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                //String newName = String.valueOf(text_Change_name.getText());
               // profile_LBL_name.setText(newName);
                //    database.getReference("customers").child(phoneNumText).child("name").setValue(newName);
                Toast.makeText(ProfileActivity.this, "The name has changed", Toast.LENGTH_SHORT).show();
            }
        });

        button_Change_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profile_LBL_phoneNum.setText(text_Change_email.getText());
                Toast.makeText(ProfileActivity.this, "The email has changed", Toast.LENGTH_SHORT).show();
            }
        });

        button_closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfileDialog.dismiss(); // Close the dialog
                readData(phoneNumText, new profileCallback() {
                    @Override
                    public void onResult(Coustumer coustumer) {
                        if (coustumer != null) {
                            profile_LBL_name.setText(coustumer.getName()); // Update the name in the activity
                        }
                    }
                });
            }
        });

        // Show the dialog
        editProfileDialog.show();
    }
}


