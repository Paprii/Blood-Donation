package com.example.demo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is our update doner class
 */

public class UpdateDonerActivity extends AppCompatActivity {

    /**
     * variables for our edit text, button, strings and dbhandler class.
     */
    private EditText nameEdt, emailEdt, bloodGroupEdt, locationEdt ;
    private Button updateDonerBtn ;
    private DBHandler dbHandler;
    String name, location, bloodGroup, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doner);

        /**
         * initializing all our variables.
         */
        nameEdt = findViewById(R.id.idDonerName);
        emailEdt = findViewById(R.id.idEmailAddress);
        bloodGroupEdt = findViewById(R.id.idBloodGroup);
        locationEdt = findViewById(R.id.idLocation);
        updateDonerBtn = findViewById(R.id.idBtnAddDonor);

        /**
         * on below line we are initialing our dbhandler class.
         */
        dbHandler = new DBHandler(UpdateDonerActivity.this);

        /**
         * on below lines we are getting data which,
         * we passed in our adapter class.
         */

        name = getIntent().getStringExtra("name");
        location = getIntent().getStringExtra("description");
        bloodGroup = getIntent().getStringExtra("duration");
        email = getIntent().getStringExtra("tracks");

        /**
         * setting data to edit text,
         * of our update activity.
         */

        nameEdt.setText(name);
        locationEdt.setText(location);
        emailEdt.setText(email);
        bloodGroupEdt.setText(bloodGroup);

        /**
         * adding on click listener to our update Doner button.
         */

        updateDonerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * inside this method we are calling an update doner.
                 * method and passing all our edit text values.
                 */

                dbHandler.updateDoner(nameEdt.getText().toString(), location,locationEdt.getText().toString(), emailEdt.getText().toString(), bloodGroupEdt.getText().toString());

                /**
                 * displaying a toast message that our doner location has been updated.
                 */

                Toast.makeText(UpdateDonerActivity.this, "Doner location Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateDonerActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
