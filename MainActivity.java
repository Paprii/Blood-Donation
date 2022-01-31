package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is main class
 */

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText nameEdt, emailAddress, bloodGroup, location ;
    private Button addDonerbtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * initializing all our variables.
         */

        nameEdt = findViewById(R.id.idDonerName);
        emailAddress = findViewById(R.id.idEmailAddress);
        bloodGroup = findViewById(R.id.idBloodGroup);
        location = findViewById(R.id.idLocation);
        addDonerbtn = findViewById(R.id.idBtnAddDonor);

        /**
         * creating a new dbhandler class,
         * and passing our context to it.
         */

        dbHandler = new DBHandler(MainActivity.this);

        /**
         * below line is to add on click listener for our add doner button.
         */

        addDonerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * below line is to get data from all edit text fields.
                 */

                String courseName = nameEdt.getText().toString();
                String courseTracks = emailAddress.getText().toString();
                String courseDuration = bloodGroup.getText().toString();
                String courseDescription = location.getText().toString();

                /**
                 *validating if the text fields are empty or not.
                 */

                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                /**
                 * on below line we are calling a method to add new doner
                 *  to sqlite data and pass all our values to it.
                 */
                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);

                /**
                 * after adding the data we are displaying a toast message.
                 */

                Toast.makeText(MainActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                nameEdt.setText("");
                bloodGroup.setText("");
                emailAddress.setText("");
                location.setText("");
            }
        });
    }
}
