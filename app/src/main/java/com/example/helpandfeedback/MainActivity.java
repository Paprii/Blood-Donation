package com.example.helpandfeedback;
/**
 * @author Rifat Ara
 * @version 1.0
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    /**
     * Edittext for user to input name
     */
    EditText etName;
    /**
     * Edittext for user to input help and feedback
     */
    EditText etHelp;
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
        etHelp=findViewById(R.id.activity_main_et_help);
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
     *    This method is for 'submit' Button<br>
     *      Showing Toast if users have not sign up and ask help to the system <br>
     *     If user have not log in to the system she or he can not be able to give feedback <br>
     *     User can search blood instead of not log in to the system but they cannot give any feedback unless they have created accout<br>
     */
    private void insertReview(){
        /**
         * store name that we get from user in the edit text names'pname'
         */
        String name=etName.getText().toString();


        /**
         * store name that we get from user in the edit text names'help'
         */
        String help= etHelp.getText().toString();


        /**
         * Sending Data throw  'name' and 'help' object
         */
        Profile profile=new Profile(name,help);

        /**
         * Using push method to pass the value of  'profile' object to the firebase database
         *  'Push' method helps to create unique id,,otherwise data would overwritted
         */

        profileDbRef.push().setValue(profile);
        /**
         *      Showing Toast "Please Insert Your Profile Name!!" for empty name input <br>
         *      Showing Toast if users have not sign up and ask help to the system <br>
         *     If user have not log in to the system she or he can not be able to give feedback <br>
         *     User can search blood instead of not log in to the system but they cannot give any feedback unless they have created accout<br>
         */
        if(profile.name==" "){
            Toast.makeText(this,"Please Insert Your Profile Name!!",Toast.LENGTH_SHORT).show();
        }
        /**
         * Showing Toast on submitting help and review
         */
        else {
        Toast.makeText(this,"Data Inserted!",Toast.LENGTH_SHORT).show();
        }

    }


}