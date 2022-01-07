package com.example.reviewfeature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 *The main activity for the application "review" feature
 * @author Rifat Ara
 * @version 1.0
 * This is the first screen for the user see
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Edittext for user to input name
     */
    EditText etName;
    /**
     * Edittext for user to input review
     */
    EditText etReview;
    /**
     * Button for submit
     */
    Button btnSubmit;
    /**
     * FirebaseDatabase object
     */
    FirebaseDatabase rootNode;

    /**
     * DatabaseReference object
     */
    DatabaseReference profileDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.activity_main_et_name);
        etReview=findViewById(R.id.activity_main_et_review);
        btnSubmit=findViewById(R.id.activity_main_et_submit);
        rootNode=FirebaseDatabase.getInstance();
        profileDbRef= rootNode.getReference().child("Profile");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            /**
             * Call Function "InsertReview()"
             * @param v
             */
            @Override
            public void onClick(View v) {
                insertReview();
            }
        });



    }

    /**
     * This method is called when user click "submit" Button
     */
    private void insertReview(){
        /**
         * store name that we get from user in the edit text names'pname'
         */
        String name=etName.getText().toString();
        /**
         * store name that we get from user in the edit text names'reviews'
         */
        String review= etReview.getText().toString();


        /**
         * Sending Data throw  'name' and 'review' object
         */
        profile profile=new profile(name,review);

        /**
         * Using push method to pass the value of  'profile' object to the firebase database
         *  'Push' method helps to create unique id,,otherwise data would overwritted
         */

        profileDbRef.push().setValue(profile);
        /**
         * Showing Toast on submitting data
          */

        Toast.makeText(this,"Data Inserted!",Toast.LENGTH_SHORT).show();

    }

}