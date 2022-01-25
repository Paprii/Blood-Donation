package com.example.updatedonardetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * This is MyDatabaseHelper class which extends SQLiteOpenHelper class
 * Here all table colums are decleared in variable
 * sCreateTable is a String type variable which reprents the comand for creating table donardetails
 * sDropTable is a String type variable which reprents the comand for drop table donardetails
 * sSelect is a String type variable which reprents the comand for showing values from table donardetails
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String sDatabaseName="Donar.db";
    public static final String sTableName="donardetails";
    public static final String sId="_id";
    public static final String sName="Nmae";
    public static final String sAge="Age";
    public static final String sWeight="Weight";
    public static final String sBmi="BMI";
    public static final String sBloodGroup="BloodGroup";
    public static final String sContact="ContactNUmber";
    public static final String sLocation="Location";
    public static final String sGender="Gender";
    public static final int sVersionNumber=2;
    public static final String sCreateTable="CREATE TABLE "+sTableName+" ( "+sId+" INTEGER PRIMARY KEY AUTOINCREMENT, "+sName+" VARCHAR(255),"+sAge+" INTEGER,"+sWeight+" DOUBLE(5,3),"+sBmi+" INTEGER,"+sBloodGroup+" VARCHAR(20),"+sContact+" VARCHAR(30),"+sLocation+" VARCHAR(50),"+sGender+" VARCHAR(15)); ";
    public static final String sDropTable= "DROP TABLE IF EXISTS " +sTableName;
    public static final String sSelect="SELECT * FROM "+sTableName;

    private Context context;

    /**
     *
     * @param context is Context type variable which is stored in this class context veriable
     * super is a constructor method
     */
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, sDatabaseName, null, sVersionNumber);
        this.context=context;
    }

    @Override
    /**
     * When first databse is created onCreate method is called
     * Any message is shown for short time bt toast method
     * try catch is for exeption handling
     * execSQL is a build in method which start query
     * catch block print if there is any exception occur
     * @param sCreateTable  is a String type variable which reprents the comand for creating table donardetails
     */
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            Toast.makeText(context,"onCreate is called",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(sCreateTable);

        }catch (Exception e)
        {
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    /**
     * When database is droped and again created,onUpgrate method is called
     *  execSQL is a build in method which start query
     * @param sDropTable is a String type variable which reprents the comand for drop table donardetails
     * onCreate method recreate the databse
     * @param sqLiteDatabase is SQLiteDatabase type variable
     */
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Toast.makeText(context,"onUpgrade is called",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(sDropTable);
            onCreate(sqLiteDatabase);
        }catch (Exception e){
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
        }


    }

    /**
     * This method insert values in donardetails table
     * contentValues is a ContentValues type object in which data are stored
     * insert is a built-in method
     * @param name represent donar name which is passed
     * @param age represent donar age which is passed
     * @param weight represent donar weight which is passed
     * @param bmi represent donar bmi which is passed
     * @param bldgrp represent donar bloodgroup which is passed
     * @param contact represent donar phonenumber which is passed
     * @param location represent donar location which is passed
     * @param gender represent donar gender which is passed
     * @return rowId return long type row number if the row is validly inserted
     */
    public long insertData(String name,String age,String weight,String bmi,String bldgrp,String contact,String location,String gender) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues;
        contentValues= new ContentValues();
        contentValues.put(sName,name);
        contentValues.put(sAge,age);
        contentValues.put(sWeight,weight);
        contentValues.put(sBmi,bmi);
        contentValues.put(sBloodGroup,bldgrp);
        contentValues.put(sContact,contact);
        contentValues.put(sLocation,location);
        contentValues.put(sGender,gender);
        long rowId=sqLiteDatabase.insert(sTableName,null,contentValues);
        return rowId;
    }

    /**
     * This method show all data
     * for read and write from database,have to take help from getWriteableDatabase
     * query is implemented by rawQuery method
     * @return Cursor is an interface where all the resultset are stored
     */
   public Cursor displayData() {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(sSelect,null);
        return cursor;
    }

    /**
     * This method update any information of donar in basis of donar id
     *  for read and write from database,have to take help from getWriteableDatabase
     *  update is a built-in method
     * @param id represent donar id by which basis all data are updated
     * @param name represent the updated name which is passed
     * @param age represent the updated age which is passed
     * @param weight represent the updated weight which is passed
     * @param bmi represent the updated bmi which is passed
     * @param bldgrp represent the updated bloodgroup which is passed
     * @param contact represent the updated contactnumber which is passed
     * @param location represent the updated location which is passed
     * @param gender represent the updated gender which is passed
     * @return true if information is successfully upated
     */
    public boolean updateData(String id,String name,String age,String weight,String bmi,String bldgrp,String contact,String location,String gender) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues;
        contentValues= new ContentValues();
        contentValues.put(sId,id);
        contentValues.put(sName,name);
        contentValues.put(sAge,age);
        contentValues.put(sWeight,weight);
        contentValues.put(sBmi,bmi);
        contentValues.put(sBloodGroup,bldgrp);
        contentValues.put(sContact,contact);
        contentValues.put(sLocation,location);
        contentValues.put(sGender,gender);
        sqLiteDatabase.update(sTableName,contentValues,sId+ " = ? ",new String[]{id});
        return true;
    }

    /**
     * This method delete row of information of donar in basis of donar id
     * @param id represent donar id by which basis any row of information of donar is deleted
     * @return int number if any row is successfully deleted
     */
    public int deleteData(String id) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(sTableName,sId+" = ?",new String[]{id});

    }
}
