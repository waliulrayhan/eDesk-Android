package com.example.edesk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PhoneBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);
        setTitle(R.string.app_name);

    }
}