package com.example.thefinalproject.client.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thefinalproject.R;
import com.example.thefinalproject.client.ListCares.Client_FragmentListCares;

public class Client_CaresListActivity extends AppCompatActivity {

    private FrameLayout table_records_LAY_list;
    private Client_FragmentListCares fragments_list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_activity_care);

        findViews();

        fragments_list = new Client_FragmentListCares();
        getSupportFragmentManager().beginTransaction().add(R.id.table_records_LAY_list , fragments_list).commit();

    }

    private void findViews() {
        table_records_LAY_list = findViewById(R.id.table_records_LAY_list);
    }

}
