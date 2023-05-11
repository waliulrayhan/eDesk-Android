package com.example.edesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

public class AdminHomeActivity extends AppCompatActivity {

    // For Store value login  or logout
    public static final String PREFS_NAME = "LoginPrefs";

    LinearLayout assignRoleCustomButton, viewDetailsCustomButton, signOutCustomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        setTitle("Admin Home");

        assignRoleCustomButton = findViewById(R.id.assignRoleCustomButton);
        viewDetailsCustomButton = findViewById(R.id.viewDetailsCustomButton);
        signOutCustomButton = findViewById(R.id.signOutCustomButton);


        assignRoleCustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAssignRoleActivity.class);
                startActivity(intent);
            }
        });
        viewDetailsCustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminViewDetailsActivity.class);
                startActivity(intent);
            }
        });
        signOutCustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              Delete or remove store value loggged
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.remove("logged");
                editor.commit();
                finish();

                Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}