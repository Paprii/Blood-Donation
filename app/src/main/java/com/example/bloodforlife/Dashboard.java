package com.example.bloodforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * A Class Representing Dashboard Page on the Screen
 */
public class Dashboard extends AppCompatActivity {
    /**
     * Displays Dashboard for successfully Logged in User
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
}