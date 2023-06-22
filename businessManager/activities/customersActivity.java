package com.example.thefinalproject.businessManager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thefinalproject.R;
import com.example.thefinalproject.businessManager.customerManagement.FragmentListCustomers;
import com.google.android.material.button.MaterialButton;

public class customersActivity extends AppCompatActivity {

    private FrameLayout table_records_LAY_list;
    private FragmentListCustomers fragments_list;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coustumers);

        findViews();

        fragments_list = new FragmentListCustomers();
        getSupportFragmentManager().beginTransaction().add(R.id.table_records_LAY_list , fragments_list).commit();

    }

    private void findViews() {
        table_records_LAY_list = findViewById(R.id.table_records_LAY_list);
    }
}
