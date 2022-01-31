package com.example.activestatus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is MainActivity class
 *From this class all the method are called through button
 *context is passed in activeStatus object
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   private EditText etName, etId, etChoice;
   private Button btnSubmit, btnShow;
    ActiveStatus activeStatus;
    /**
     * IN onCreate method all buttton and editTet are being type casting
     * @param savedInstanceState  inside onCreate() on line 30 is the declaration of Bundle class.
     *On line 31 calling the onCreate() method from the super class.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activeStatus = new ActiveStatus(this);
        SQLiteDatabase sqLiteDatabase = activeStatus.getWritableDatabase();

       etName=(EditText)findViewById(R.id.Name);
        etId=(EditText)findViewById(R.id.id);
        etChoice=(EditText)findViewById(R.id.choice);
        btnSubmit=(Button) findViewById(R.id.submit);
        btnShow=(Button) findViewById(R.id.show);
        btnSubmit.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }


    /**
     * In this method all data are converted to string
     * @param view is the element previously clicked
     */
    @Override
    public void onClick(View view) {
        String name=etName.getText().toString();
        String id=etId.getText().toString();
        String choice=etChoice.getText().toString();
        /**
         * When view get id of submit,then insertData method is called
         * when rowId produce any negative value,Data aren't successfully inserted
         * else,data are inserted successfully in table
         * Any message is shown for short time bt toast method
         */

        if (view.getId()==R.id.submit) {
            long rowId= activeStatus.insertData(id,name,choice);
            if (rowId==-1) {
                Toast.makeText(getApplicationContext(),"unsuccessful",Toast.LENGTH_LONG).show();
            }

            else {
                Toast.makeText(getApplicationContext(),"row"+rowId+"is successfully inserted",Toast.LENGTH_LONG).show();
            }
        }

        /**
         *  When view get id of show,then displayData method is called
         * if there is no row,no data is found seen
         * else,data are stored in stringBuffer where choice is yes and display it
         */

        if(view.getId()==R.id.show) {
            Cursor Display = activeStatus.displayData();
            if (Display.getCount() == 0) {
                showData("ERROR", "No Active Member");
                return;
            }
                StringBuffer stringBuffer = new StringBuffer();
                while (Display.moveToNext()) {
                    stringBuffer.append("ID :" + Display.getString(0) + "\n");
                    stringBuffer.append("NAME :" + Display.getString(1) + "\n");
                    stringBuffer.append("Choice :" + Display.getString(2) + "\n\n");

                }
                showData("ACTIVES ARE:", stringBuffer.toString());

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