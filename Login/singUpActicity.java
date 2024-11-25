package com.example.thefinalproject.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thefinalproject.R;
import com.example.thefinalproject.businessManager.customerManagement.Coustumer;
import com.example.thefinalproject.businessManager.customerManagement.CoustumerList;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class singUpActicity extends AppCompatActivity {

    // Firebase database instance and reference
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    // UI components
    private Button singUp_LBL_singUp;
    private TextInputLayout singUp_EDT_name;
    private TextInputLayout singUp_EDT_mail;
    private TextInputLayout singUp_EDT_phoneNum;
    private boolean phoneNumberExists = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        // Initialize the Firebase database instance
        database = FirebaseDatabase.getInstance();

        // Locate UI components
        findViews();

        // Set the sign-up button's click listener
        singUp_LBL_singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get user input from the form fields
                String name = singUp_EDT_name.getEditText().getText().toString();
                String emailText = singUp_EDT_mail.getEditText().getText().toString();
                String phoneNumText = singUp_EDT_phoneNum.getEditText().getText().toString();

                 // Validate that required fields are not empty
                if (emailText.isEmpty() || phoneNumText.isEmpty()) {
                    singUp_LBL_singUp.setError("Age and phone number are required");

                } else {
                    try {
                      // Check if the phone number already exists in the database
                        readData(phoneNumText ,  new Callback() {
                            @Override
                            public void onResult(boolean phoneNumberExists) {

                                if (phoneNumText.matches("[0-9]+") && phoneNumberExists == false) {
                                    int phoneNum = Integer.parseInt(phoneNumText.replaceFirst("^0+(?!$)", ""));
                                    CoustumerList.addCoustomer(name, phoneNum, emailText);
                                    saveData(name, phoneNum, emailText);
                                    openReturnLoginPageActivity();

                                } else if(phoneNumberExists == true){
                                    Toast.makeText(singUpActicity.this, "This phone number already exists in the system", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(singUpActicity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } catch (NumberFormatException e) {

                        Toast.makeText(singUpActicity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

   // Locate and initialize UI components
    private void findViews() {
        singUp_EDT_name = findViewById(R.id.singUp_EDT_name);
        singUp_LBL_singUp = findViewById(R.id.singUp_LBL_singUp);
        singUp_EDT_mail = findViewById(R.id.singUp_EDT_mail);
        singUp_EDT_phoneNum = findViewById(R.id.singUp_EDT_PhoneNum);
    }

    // Navigate to the login page after successful registration
    public void openReturnLoginPageActivity() {
        Intent intent = new Intent(singUpActicity.this, LoginPageActivity.class);
        startActivity(intent);
        finish();
    }

   // Save the new customer's data to the Firebase database
    public void saveData(String name, int phoneNum, String email) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("customers").push();

        ///////////////**
        Coustumer coustumer = new Coustumer(name, phoneNum, email);
        CoustumerList.addCoustomer(name, phoneNum, email);

        myRef.setValue(coustumer);

        //  myRef.child("customers").child("" + phoneNum).child("name").setValue(name);
        // myRef.child("customers").child("" + phoneNum).child("age").setValue(age);
    }

    // Check if a phone number already exists in the database
    public void readData(String phoneNum , final Callback callback) {

        DatabaseReference myRef = database.getReference("customers");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot c :  dataSnapshot.getChildren())
                {
                  int cc =   c.getValue(Coustumer.class).getPhoneNum();
                  if(cc == Integer.parseInt(phoneNum)){
                      phoneNumberExists = true;
                  }
                }
                callback.onResult(phoneNumberExists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onResult(false);
            }
        });
    }

    // Callback interface to handle the result of the phone number existence check
    interface Callback {
        void onResult(boolean phoneNumberExists);
    }
}
