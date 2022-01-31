package com.example.activestatus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 *This is ActiveStatus class which extends SQLiteOpenHelper class
 *Here all table colums are decleared in variable
 * sCreateTable is a String type variable which represents the comand for creating table Actives
 *sDropTable is a String type variable which represents the comand for drop table Actives
 *  sSelect is a String type variable which represents the comand for showing values from table Actives
 */
public class ActiveStatus extends SQLiteOpenHelper {
    private static final String sDatabaseName = "ActiveStatus.db";
    public static final String sTableName = "Actives";
    public static final String sId = "_id";
    public static final String sName = "Name";
    public static final String sChoice = "Choiche";
    public static final int sVersionNumber = 2;
    public static final String sDropTable = "DROP TABLE IF EXISTS " + sTableName;
    public static final String sCreateTable = "CREATE TABLE " + sTableName + " ( " + sId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + sName + " VARCHAR(255),"+sChoice+" VARCHAR(15)); ";
    public static final String sSelect = "SELECT * FROM " + sTableName+ " WHERE sChoice = 'yes'";


    private Context context;

    /**
     *
     * @param context is Context type variable which is stored in this class context veriable
     * super is a constructor method
     */

    public ActiveStatus(@Nullable Context context) {
        super(context, sDatabaseName, null, sVersionNumber);
        this.context = context;
    }

    @Override
    /**
     *When first databse is created onCreate method is called
     * Any message is shown for short time bt toast method
     *try catch is for exeption handling
     *execSQL is a build in method which start query
     *catch block print if there is any exception occur
     * @param sCreateTable  is a String type variable which reprents the comand for creating table Actives
     */
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {

            sqLiteDatabase.execSQL(sCreateTable);
            Toast.makeText(context, "onCreate is called", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(context, "Exception :" + e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
/**
 *  When database is droped and again created,onUpgrate method is called
 *execSQL is a build in method which start query
 * @param sDropTable is a String type variable which reprents the comand for drop table Actives
 *onCreate method recreate the databse
 *@param sqLiteDatabase is SQLiteDatabase type variable
 */
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(sDropTable);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            Toast.makeText(context, "Exception :" + e, Toast.LENGTH_LONG).show();
        }

    }

    /**
     *This method insert values in Actives table
     *contentValues is a ContentValues type object in which data are stored
     *insert is a built-in method
     * @param id represent donar id which is passed
     * @param name represent donar name which is passed
     * @param choice represent donar choice  which is passed,ActiveS When choice is yes
     * @return rowId return long type row number if the row is validly inserted
     */

   public long insertData(String id,String name,String choice) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues;
        contentValues = new ContentValues();
        contentValues.put(sId, id);
        contentValues.put(sName,name);
        contentValues.put(sChoice,choice);
        long rowId=sqLiteDatabase.insert(sTableName,null,contentValues);
        return rowId;
    }

    /**
     * This method show data where choice is yes
     *  for read and write from database,have to take help from getWriteableDatabase
     *query is implemented by rawQuery method
     * @return Cursor is an interface where all the resultset are stored
     */
    public Cursor displayData() {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(sSelect,null);
        return cursor;
    }


}
