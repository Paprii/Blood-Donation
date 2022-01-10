package com.example.bloodforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * This is the main activity
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 3 EditText variables
     */
    private EditText email,subject,message;

    /**
     *button type variable
     */

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * set varible value from xml file
         */

        email=findViewById(R.id.email);
        subject=findViewById(R.id.subject);
        message=findViewById(R.id.message);
        button=findViewById(R.id.btn);

        button.setOnContextClickListener(new View.OnContextClickListener() {
                                             @Override
                                             public boolean onContextClick(View view) {
                                                 senEmail();
                                             }
                                         }

        );
    }
    /**
     * This method is to convert user input for email to send ,subject and message and through javaMailAPI convert it to actual email,subject and body
     */
    private void senEmail(){
        String mEmail=email.getText().toString();
        String mSubject=subject.getText().toString();
        String mMessage=message.getText().toString();

        javaMailAPI javaMailAPI=new javaMailAPI(Context: this ,mEmail ,mSubject,mMessage);
        javaMailAPI.execute();
    }
}