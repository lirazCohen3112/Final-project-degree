package com.example.thefinalproject.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thefinalproject.R;
import com.example.thefinalproject.businessManager.activities.main;
import com.example.thefinalproject.businessManager.customerManagement.Coustumer;
import com.example.thefinalproject.businessManager.customerManagement.CoustumerList;
import com.example.thefinalproject.client.activities.ClientMainActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class LoginPageActivity extends AppCompatActivity {

    private FirebaseDatabase database;

    private DatabaseReference myRef;
    private Button login_LBL_sing_up;
    private Button login_LBL_connection;
    private TextInputLayout login_EDT_PhoneNum;

   // private Coustumer coustumer;
    private boolean phoneNumberExists = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        database = FirebaseDatabase.getInstance();

        findViews();

        login_LBL_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkingUserExists();
            }
        });

        login_LBL_sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSingUpActivity();
            }
        });
    }

    private void findViews() {
        login_LBL_sing_up = findViewById(R.id.login_LBL_sing_up);
        login_LBL_connection = findViewById(R.id.login_LBL_connection);
        login_EDT_PhoneNum = findViewById(R.id.login_EDT_PhoneNum);
    }

    public void openSingUpActivity(){
        Intent intent = new Intent(LoginPageActivity.this, singUpActicity.class);
        startActivity(intent);
        finish();
    }

    public void checkingUserExists(){

        String phoneNumText = login_EDT_PhoneNum.getEditText().getText().toString().trim();
        if (!phoneNumText.isEmpty()) {
            try {
               // int phoneNumber = Integer.parseInt(phoneNumText);

                readData(phoneNumText ,  new loginCallback() {
                    @Override
                    public void onResult(boolean phoneNumberExists) {
                        if (Integer.parseInt(phoneNumText) == 526553500 && phoneNumberExists == true)
                        {
                            Intent intent = new Intent(LoginPageActivity.this, main.class);

                            startActivity(intent);
                            finish();
                        } else if(phoneNumberExists == true  ) {
                            Intent intent = new Intent(LoginPageActivity.this, ClientMainActivity.class);
                            intent.putExtra("phoneNumText", phoneNumText);
                            startActivity(intent);
                            finish();
                        } else {
                            login_EDT_PhoneNum.setError("Invalid phone number");
                            login_EDT_PhoneNum.requestFocus();
                        }
                    }
                });
            } catch (NumberFormatException e) {
                login_EDT_PhoneNum.setError("Invalid phone number");
                login_EDT_PhoneNum.requestFocus();
            }
        } else {
            login_EDT_PhoneNum.setError("Phone number is required");
            login_EDT_PhoneNum.requestFocus();
        }
    }

    public void readData(String phoneNum , final loginCallback loginCallback) {

        myRef = database.getReference("customers");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot c :  dataSnapshot.getChildren())
                {
                    int cc = c.getValue(Coustumer.class).getPhoneNum();
                    if(cc == Integer.parseInt(phoneNum)){
                        phoneNumberExists = true;
                  //    coustumer = c.getValue(Coustumer.class);
                    }
                }
                loginCallback.onResult(phoneNumberExists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                loginCallback.onResult(false );
            }
        });
    }

    interface loginCallback {
        void onResult(boolean phoneNumberExists );
    }
}

