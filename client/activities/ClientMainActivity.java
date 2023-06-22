package com.example.thefinalproject.client.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.thefinalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClientMainActivity extends AppCompatActivity {

    private ImageButton chat_BTN_chat;
    private String phoneNumText;

    // Define the constant resource IDs
    private static final int PROFILE_FRAGMENT_ID = R.id.profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_activity_menu);

        phoneNumText = getIntent().getStringExtra("phoneNumText");

        findViews();

        // Set a custom action bar
        setSupportActionBar(findViewById(R.id.toolbar));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.careFragment) {
                    navController.navigate(R.id.careFragment);
                    return true;
                }
                if (itemId == R.id.profileFragment) {
                    openProfileActivity();
                    return true;
                }

                if (itemId == R.id.choose) {
                    navController.navigate(R.id.choose);
                    return true;
                }

                return false;
            }
        });

        chat_BTN_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChat();
            }
        });
    }

    private void findViews() {
        chat_BTN_chat = findViewById(R.id.chat_BTN_chat);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void openProfileActivity() {
        Intent intent = new Intent(ClientMainActivity.this, ProfileActivity.class);
        intent.putExtra("phoneNumText" , phoneNumText);
        startActivity(intent);
        overridePendingTransition(0 , 0);
    }

    public void openChat() {
        Intent intent = new Intent(ClientMainActivity.this, ChatActivity.class);
        startActivity(intent);
        finish();
    }
}


