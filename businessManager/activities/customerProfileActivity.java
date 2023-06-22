package com.example.thefinalproject.businessManager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.thefinalproject.R;

public class customerProfileActivity extends AppCompatActivity {

    private FrameLayout table_records_LAY_list;
    private ImageButton customer_LBL_Return_menu;
    private TextView profile_LBL_name;
    private TextView profile_LBL_email;
    private TextView profile_LBL_phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_coustuber);

        findViews();

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String phoneNum = intent.getStringExtra("phoneNum");
            String email = intent.getStringExtra("email");

            profile_LBL_name.setText(name);
            profile_LBL_email.setText(email);
            profile_LBL_phoneNum.setText(phoneNum);
        }

       customer_LBL_Return_menu.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                openMenu();
            }
       });
    }

    private void findViews() {
        table_records_LAY_list = findViewById(R.id.table_records_LAY_list);
        profile_LBL_name = findViewById(R.id.table_records_LBL_name);
        profile_LBL_email = findViewById(R.id.table_records_LBL_email);
        profile_LBL_phoneNum = findViewById(R.id.table_records_LBL_phoneNum);
        customer_LBL_Return_menu = findViewById(R.id.customer_LBL_Return_menu);
    }

    private void openMenu(){
        Intent intent = new Intent(customerProfileActivity.this, main.class);
        startActivity(intent);
        finish();
    }
}
