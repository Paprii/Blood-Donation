package com.example.bloodforlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText edtPhone,edtPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPhone=findViewById(R.id.activity_login_et_phonenumber);
        edtPassword=findViewById(R.id.activity_login_et_password);
        btnLogin=findViewById(R.id.activity_login_btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=edtPhone.getText().toString().trim();
                String password=edtPassword.getText().toString().trim();
                Query checkUser= FirebaseDatabase.getInstance().getReference("Donors").orderByChild("phone").equalTo(phone);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String sysPassword=snapshot.child(phone).child("password").getValue(String.class);
                            if(sysPassword.equals(password)){
                                startActivity(new Intent(Login.this,Dashboard.class));
                            }
                            else{
                                Toast.makeText(Login.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Login.this,"Invalid Account",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });













    }
}