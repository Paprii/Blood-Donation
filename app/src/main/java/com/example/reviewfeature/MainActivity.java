package com.example.reviewfeature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etreview;
    Button btnSubmit;
    FirebaseDatabase rootNode;
    DatabaseReference profileDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.activity_main_et_name);
        etreview=findViewById(R.id.activity_main_et_review);
        btnSubmit=findViewById(R.id.activity_main_et_submit);
        rootNode=FirebaseDatabase.getInstance();
        profileDbRef= rootNode.getReference().child("Profile");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertReview();
            }
        });



    }

    private void insertReview(){
        String name=etName.getText().toString();//store name that we get from user in the edit text names'pname'
        String review= etreview.getText().toString(); //store name that we get from user in the edit text names'reviews'



        profile profile=new profile(name,review);//Sending Data throw object

        profileDbRef.push().setValue(profile);// Using push method to pass the value of object'profile' to the firebase
        // database,,it helps to create unique id,,otherwise data would overwritrd
        Toast.makeText(this,"Data Inserted!",Toast.LENGTH_SHORT).show();

    }


}