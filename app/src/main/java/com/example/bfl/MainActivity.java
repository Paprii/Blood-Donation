package com.example.bfl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 *This is the starting activity of our app
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Name of a Donor
     */
    EditText etName;
    /**
     * The BloodGroup of a Donor
     */
    EditText etBloodGroup;
    /**
     * The Email of a Donor
     */
    EditText etEmail;
    /**
     * The Password of a Donor
     */
    EditText etPassword;
    /**
     * The Weight of a Donor
     */
    EditText etWeight;
    /**
     * The ContactNumber of a Donor
     */
    EditText etPhone;
    /**
     * Button For Confirming SignUp
     */
    Button btnRegister;
    /**
     * Button For Activating Login Page
     */
    Button btnSearch;
    /**
     * Field for getting instance of a Firebase Database
     */
    FirebaseDatabase rootNode;
    /**
     * Field for selecting Table to save Donor data in Firebase DB
     */
    DatabaseReference reference;
    /**
     *
     */
    AutoCompleteTextView acvBloodGroup;

    /**
     * Saves the given data by a Donor in DB
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.activity_main_et_name);
        acvBloodGroup= findViewById(R.id.activity_main_acv_bloodgroup);
        etWeight = findViewById(R.id.activity_main_et_weight);
        etEmail = findViewById(R.id.activity_main_et_email);
        etPassword = findViewById(R.id.activity_main_et_password);
        etPhone = findViewById(R.id.activity_main_et_phonenumber);
        btnSearch = findViewById(R.id.activity_main_btn_search);
        btnRegister = findViewById(R.id.activity_main_btn_register);

        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,BLOODGROUPS);
        acvBloodGroup.setAdapter(adapter);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            /**
             * This Function saves new donor's data into the database
             * When Register Button is clicked then  app will check if user's weight is above 50 Kgs
             * if User's weight is under 50 Kgs then a toast will appear that user is not suitable fot donating blood
             * if user is considered underweight then he/she can't be registered in our app as a donor
             * @param v represents view that has received the clicked event
             */
            @Override

            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Donors");
                String name = etName.getText().toString();
                String bloodGroup = acvBloodGroup.getText().toString();
                String weight = etWeight.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String phone = etPhone.getText().toString();
                Integer weightInteger = Integer.valueOf(weight);


                /*
                 *condition checking if user is of suitable weight( 50 Kgs in this context)
                 */
                if (weightInteger > 50) {
                    if (name.length() != 0 && password.length() != 0 && acvBloodGroup.length() != 0 && email.length() != 0) {

                        Profile profile = new Profile(name, email, bloodGroup, weight, password, phone);
                        reference.child(phone).setValue(profile);
                        Toast.makeText(MainActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    }
                    /*
                    Checking if any field is empty or not when user press the register button
                     */
                    else {
                        Toast.makeText(MainActivity.this, "Some Fields are Empty", Toast.LENGTH_SHORT).show();
                    }
                }
                /*
                if User is Under 50 Kgs in Weight then a toast will be shown
                 */
                else

                {
                    Toast.makeText(MainActivity.this, "Weight Must be Above 50 KG", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener()

        {
            /**
             * This function changes the display of our app to the activity where a user can search for a blood Donor<br>
             * @param v represents view that has received the clicked event
             */
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity.this, Search.class));
            }
        });
    }

    /**
     *This static array has been declared for the autocompletion field named Bloodgroup in our registration form
     * User can type A and there will be suggestions for autocompletion based on users given input
     */
    private static final String[] BLOODGROUPS = new String[] {
            "A+", "AB+", "B+", "O+","A-","B-","AB-","O-"
    };
}