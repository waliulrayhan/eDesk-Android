package com.example.edesk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAssignRoleActivity extends AppCompatActivity {
    TextInputEditText textInputEditText_UserID;
    Button AssignRoleButton;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    String SelectedRole, Faculty, Department, SelectPosition, UserID;
    AutoCompleteTextView autoCompleteTextView_Role, autoCompleteTextView_Faculty, autoCompleteTextView_Department, autoCompleteTextView_SelectPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_role);
        setTitle("Assign Role");

        databaseReference = FirebaseDatabase.getInstance().getReference("Admin Assign Role");
        mAuth = FirebaseAuth.getInstance();

        //Set String to Declare the Adapter
        ArrayAdapter<String> adapterSelectRoleOffice = new ArrayAdapter<>(this, R.layout.drop_down_iteam, getResources().getStringArray(R.array.SelectRoleOffice));
        ArrayAdapter<String> adapterSelectFaculty = new ArrayAdapter<>(this, R.layout.drop_down_iteam, getResources().getStringArray(R.array.SelectFaculty));
        ArrayAdapter<String> adapterSelectDepartment = new ArrayAdapter<>(this, R.layout.drop_down_iteam, getResources().getStringArray(R.array.SelectDepartment));
        ArrayAdapter<String> adapterSelectPosition = new ArrayAdapter<>(this, R.layout.drop_down_iteam, getResources().getStringArray(R.array.SelectPosition));

        autoCompleteTextView_Role = findViewById(R.id.spinner_JnU_Offices_and_Roles);
        autoCompleteTextView_Faculty = findViewById(R.id.spinner_Faculty);
        autoCompleteTextView_Department = findViewById(R.id.spinner_Department);
        autoCompleteTextView_SelectPosition = findViewById(R.id.spinner_position);
        textInputEditText_UserID = findViewById(R.id.spinner_UserID);
        AssignRoleButton = findViewById(R.id.adminAssignRoleButton);

        //set Select role Adapter
        autoCompleteTextView_Role.setAdapter(adapterSelectRoleOffice);


        //Initially all are hide
        //////////////////////////////////////////////////////////////////////
        autoCompleteTextView_Faculty.setEnabled(false);                     //
        autoCompleteTextView_Faculty.setClickable(false);                   //
        autoCompleteTextView_Faculty.setVisibility(View.INVISIBLE);         //
                                                                            //
        autoCompleteTextView_Department.setEnabled(false);                  //
        autoCompleteTextView_Department.setClickable(false);                //
        autoCompleteTextView_Department.setVisibility(View.INVISIBLE);      //
                                                                            //
        autoCompleteTextView_SelectPosition.setEnabled(false);              //
        autoCompleteTextView_SelectPosition.setClickable(false);            //
        autoCompleteTextView_SelectPosition.setVisibility(View.INVISIBLE);  //
        //////////////////////////////////////////////////////////////////////

        autoCompleteTextView_Role.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SelectedRole = (String)adapterView.getItemAtPosition(i);

                if (i==0){

                }

                else if(i==1){
                    autoCompleteTextView_Faculty.setAdapter(adapterSelectFaculty);
                    autoCompleteTextView_Department.setAdapter(adapterSelectDepartment);

                    autoCompleteTextView_Faculty.setEnabled(true);
                    autoCompleteTextView_Faculty.setClickable(true);
                    autoCompleteTextView_Faculty.setVisibility(view.VISIBLE);

                    autoCompleteTextView_Department.setEnabled(true);
                    autoCompleteTextView_Department.setClickable(true);
                    autoCompleteTextView_Department.setVisibility(view.VISIBLE);

                    autoCompleteTextView_SelectPosition.setEnabled(false);
                    autoCompleteTextView_SelectPosition.setClickable(false);
                    autoCompleteTextView_SelectPosition.setVisibility(View.INVISIBLE);
                }

                else{
                    autoCompleteTextView_SelectPosition.setAdapter(adapterSelectPosition);

                    autoCompleteTextView_Faculty.setEnabled(false);
                    autoCompleteTextView_Faculty.setClickable(false);
                    autoCompleteTextView_Faculty.setVisibility(view.INVISIBLE);

                    autoCompleteTextView_Department.setEnabled(false);
                    autoCompleteTextView_Department.setClickable(false);
                    autoCompleteTextView_Department.setVisibility(view.INVISIBLE);

                    autoCompleteTextView_SelectPosition.setEnabled(true);
                    autoCompleteTextView_SelectPosition.setClickable(true);
                    autoCompleteTextView_SelectPosition.setVisibility(View.VISIBLE);
                }
            }
        });

        //Collect String
        autoCompleteTextView_Faculty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Faculty = (String)parent.getItemAtPosition(position);
            }
        });

        //Collect String
        autoCompleteTextView_Department.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Department = (String)parent.getItemAtPosition(position);
            }
        });

        //Collect String
        autoCompleteTextView_SelectPosition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectPosition = (String)parent.getItemAtPosition(position);
            }
        });

        //Button Clicked
        AssignRoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssignRoleFuction();
            }
        });
    }

    private void AssignRoleFuction(){

        UserID = textInputEditText_UserID.getText().toString();

        Toast.makeText(getApplicationContext(), "Result :   "+SelectedRole+"   "+Faculty+"   "+Department+"     "+SelectPosition+"    "+UserID, Toast.LENGTH_LONG).show();


//        String key = databaseReference.push().getKey();
        AdminAssignRoleHelparJava data = new AdminAssignRoleHelparJava(SelectedRole, Faculty, Department, SelectPosition, UserID);
        databaseReference.setValue(data);
    }
}