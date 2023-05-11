package com.example.edesk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {
    // For Store value login  or logout
    public static final String PREFS_NAME = "LoginPrefs";
    Context context;
    TextInputEditText adminSignInID, adminSignInPassword;
    Button adminLoginSignInButton;
    String UserId, Password;
    private FirebaseAuth mAuth;
    ProgressBar linearProgressIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        setTitle(R.string.app_name);

//      Check login or logout
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
            startActivity(intent);
        }


        mAuth = FirebaseAuth.getInstance();

        adminSignInID = findViewById(R.id.adminSignInID);
        adminSignInPassword = findViewById(R.id.adminSignInPassword);
        adminLoginSignInButton = findViewById(R.id.adminLoginSignInButton);
        linearProgressIndicator = findViewById(R.id.linearProgressIndicator);

        adminLoginSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //        ***** to hide the keyboard after button click *****
                View focus = getCurrentFocus();
                if (focus != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(focus.getWindowToken(), 0);
                }

                if (!isConnected()) {
                    Toast.makeText(getApplicationContext(), "You are currently offline", Toast.LENGTH_LONG).show();
                } else {
                    signInPanel();
                }
            }
        });

    }

    private void signInPanel() {
        UserId = adminSignInID.getText().toString();
        Password = adminSignInPassword.getText().toString();

//        User Id IS EMPTY and Password IS EMPTY
        if (UserId.isEmpty() && Password.isEmpty()) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "User ID and Password are required", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint((getResources().getColor(R.color.dark_red)));
            snackbar.setTextColor((getResources().getColor(R.color.white)));
            snackbar.show();

//            Here Toast is used just in case the app doesn't crash. Because Snackbar can't handle exception handling but Toast can
            Toast toast = Toast.makeText(getApplicationContext(), "User ID and Password are required", Toast.LENGTH_SHORT);
//            toast.show();
            return;
        }

//        User Id IS EMPTY and Password IS NOT EMPTY
        if (UserId.isEmpty() && !Password.isEmpty()) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "User ID is required", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint((getResources().getColor(R.color.dark_red)));
            snackbar.setTextColor((getResources().getColor(R.color.white)));
            snackbar.show();

            Toast toast = Toast.makeText(getApplicationContext(), "User ID is required", Toast.LENGTH_SHORT);
            return;
        }

//        User Id IS NOT EMPTY and Password IS EMPTY
        if (!UserId.isEmpty() && Password.isEmpty()) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Password is required", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint((getResources().getColor(R.color.dark_red)));
            snackbar.setTextColor((getResources().getColor(R.color.white)));
            snackbar.show();

            Toast toast = Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT);
            return;
        }


//        User Id IS NOT EMPTY and Password IS NOT EMPTY
        if (!UserId.isEmpty() && !Password.isEmpty() && (Password.length() < 6)) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Password length must be greater than 6 digit", Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint((getResources().getColor(R.color.dark_red)));
            snackbar.setTextColor((getResources().getColor(R.color.white)));
            snackbar.show();

            Toast toast = Toast.makeText(getApplicationContext(), "Password length must be greater than 6 digit", Toast.LENGTH_SHORT);
            return;
        }

//      **********************************************

        UserId = UserId + "@gmail.com";

//      **********************************************

        linearProgressIndicator.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(UserId, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                linearProgressIndicator.setVisibility(View.GONE);

                if (task.isSuccessful()) {

//                  logged is equal to sigin
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("logged", "logged");
                    editor.commit();

                    Intent intent = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "User ID or Password didn't match", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    // Internet Permission Check
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}


//    ***** to hide the keyboard and to clear focus of editText on touch outside *****
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if ( v instanceof EditText) {
//                Rect outRect = new Rect();
//                v.getGlobalVisibleRect(outRect);
//                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
//                    v.clearFocus();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
//        }
//        return super.dispatchTouchEvent( event );
//    }


//    ***** to hide the keyboard while clicking outside of editText box but focus remain same *****
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (getCurrentFocus() != null) {
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        }
//        return super.dispatchTouchEvent(ev);
//    }