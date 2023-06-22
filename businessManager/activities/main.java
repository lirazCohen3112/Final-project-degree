package com.example.thefinalproject.businessManager.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.thefinalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class main extends AppCompatActivity{

    private ImageButton chat_BTN_chat;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViews();

        setSupportActionBar(findViewById(R.id.toolbar));

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment1);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.customers) {
                    navController.navigate(R.id.customers);
                    return true;
                }

                if (itemId == R.id.expenses) {
                    navController.navigate(R.id.expenses);
                    return true;
                }

                if (itemId == R.id.choose) {
                    navController.navigate(R.id.choose);
                    return true;
                }

                if (itemId == R.id.cares) {
                    navController.navigate(R.id.cares);
                    return true;
                }

                return false;
            }
        });

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void findViews() {

        chat_BTN_chat = findViewById(R.id.chat_BTN_chat);
        bottomNavigationView = findViewById(R.id.bottomNavigationView1);
    }


}
