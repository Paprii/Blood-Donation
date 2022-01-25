package com.example.updatedonardetails;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This is MainActivity class
 * From this class all the method are called through button
 * context is passed in myDatabaseHelper object
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName,etAge,etWeight,etBMI,etBldGrp,etNumber,etLocation,etGender,etId;
    private Button btnADD,btnDisplay,btnUpdate,btnDelete;
    MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(this);

    /**
     * IN onCreate method all buttton and editTet are being type casting
     * @param savedInstanceState  inside onCreate() on line 30 is the declaration of Bundle class.
     *On line 31 calling the onCreate() method from the super class.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();

        etName=(EditText)findViewById(R.id.Name);
        etAge=(EditText)findViewById(R.id.Age);
        etWeight=(EditText)findViewById(R.id.Weight);
        etBMI=(EditText)findViewById(R.id.bmi);
        etBldGrp=(EditText)findViewById(R.id.bldgrp);
        etNumber=(EditText)findViewById(R.id.number);
        etLocation=(EditText)findViewById(R.id.location);
        etGender=(EditText)findViewById(R.id.gender);
        etId=(EditText) findViewById(R.id.id);
        btnADD=(Button)findViewById(R.id.btnadd);
        btnDisplay=(Button)findViewById(R.id.btnview);
        btnUpdate=(Button)findViewById(R.id.btnupdate);
        btnDelete=(Button)findViewById(R.id.btndelete);
        btnADD.setOnClickListener(this);
        btnDisplay.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

    }

    /**
     * In this method all data are converted to string
     * @param view is the element previously clicked
     */

    @Override
    public void onClick(View view) {
        String name=etName.getText().toString();
        String age=etAge.getText().toString();
        String gender=etGender.getText().toString();
        String bmi=etBMI.getText().toString();
        String weight=etWeight.getText().toString();
        String bldgrp=etBldGrp.getText().toString();
        String contact=etNumber.getText().toString();
        String location=etLocation.getText().toString();
        String ID=etId.getText().toString();
        /**
         * When view get id of btnadd,then insertData method is called
         * when rowId produce any negative value,Data aren't successfully inserted
         * else,data are inserted successfully in table
         * Any message is shown for short time bt toast method
         */

        if(view.getId()==R.id.btnadd); {
           long rowId= myDatabaseHelper.insertData(name,age,weight,bmi,bldgrp,contact,location,gender);
           if (rowId==-1) {
               Toast.makeText(getApplicationContext(),"unsuccessful",Toast.LENGTH_LONG).show();
           }

           else {
               Toast.makeText(getApplicationContext(),"row"+rowId+"is successfully inserted",Toast.LENGTH_LONG).show();
           }
        }
        /**
         *  When view get id of btnview,then displayData method is called
         * if there is no row,no data is found seen
         * else, all data are stored in stringBuffer and display it
         */
        if(view.getId()==R.id.btnview) {
            Cursor Display=myDatabaseHelper.displayData();
            if(Display.getCount()==0) {
                showData("ERROR","No Data Found");
                return;
            }
            StringBuffer stringBuffer=new StringBuffer();
            while (Display.moveToNext()){
                stringBuffer.append("ID :"+Display.getString(0)+"\n");
                stringBuffer.append("NAME :"+Display.getString(1)+"\n");
                stringBuffer.append("AGE :"+Display.getString(2)+"\n");
                stringBuffer.append("WEIGHT :"+Display.getString(3)+"\n");
                stringBuffer.append("BMI :"+Display.getString(4)+"\n");
                stringBuffer.append("BLOODGROUP :"+Display.getString(5)+"\n");
                stringBuffer.append("CONTACTNUMBER :"+Display.getString(6)+"\n");
                stringBuffer.append("LOCATION :"+Display.getString(7)+"\n");
                stringBuffer.append("GENDER :"+Display.getString(8)+"\n\n");
            }
            showData("ALL DATA",stringBuffer.toString());

        }
        /**
         *  When view get id of btnupdate,then updateData method is called
         *  if isUpdated is true data are updated
         *  else not updated
         */
        if(view.getId()==R.id.btnupdate) {
            boolean isUpdated=myDatabaseHelper.updateData(ID,name,age,weight,bmi,bldgrp,contact,location,gender);
            if(isUpdated==true) {
                Toast.makeText(getApplicationContext(),"Data is updated",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Data is not updated",Toast.LENGTH_LONG).show();
            }

        }
        /**
         * When view get id of btndepete,then deleteData method is called
         * if deleted variable produce possitive value ,data is deleted successfully
         * else, not deleted
         */
        if(view.getId()==R.id.btndelete) {
            int deleted=myDatabaseHelper.deleteData(ID);
            if(deleted>0) {
                Toast.makeText(getApplicationContext(),"Data is deleted successfully",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Data is not deleted",Toast.LENGTH_LONG).show();
            }

        }
    }

    /**
     * This method show the resultset
     * @param title reprent the title which is being passed
     * @param data represent the aata which are being shown
     *
     */
    public void showData(String title,String data) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.show();

    }
}