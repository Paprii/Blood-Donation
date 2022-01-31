package com.example.activestatus;

import android.database.Cursor;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4 ::class)

/**
 * This class Test class
 *Here test whoose are active
 * if sChoice is no,it's not active
 */

public class ActiveStatusTest {
    @test
    String sChoice;
    public void isNotActive() {

        if (sChoice == "no") {
            ActiveStatus activeStatus = new ActiveStatus();
            Cursor output = activeStatus.displayData();
        }
    }

}