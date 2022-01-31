package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    /**
     * creating a constant variables for our database.
     * below variable is for our database name.
     */

    private static final String DB_NAME = "Blooddb";

    /**
     * below int is our database version.
     */

    private static final int DB_VERSION = 1;

    /**
     * below variable is for our table name.
     */

    private static final String TABLE_NAME = "Doner";

    /**
     * below variable is for our id column.
     */

    private static final String ID_COL = "id";

    /**
     * below variable is for our doner name column.
     */

    private static final String NAME_COL = "name";

    /**
     * below variable id for our Blood group column.
     */

    private static final String DURATION_COL = "bloodGroup";

    /**
     * below variable for location column.
     */

    private static final String DESCRIPTION_COL = "location";

    /**
     * below variable is for our email column.
     */

    private static final String TRACKS_COL = "email";

    /**
     * creating a constructor for our database handler.
     * @param context
     */

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * below method is for creating a database by running a sqlite query.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * on below line we are creating
         * an sqlite query and we are
         * setting our column names
         * along with their data types.
         */
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + TRACKS_COL + " TEXT)";

        /**
         * at last we are calling a exec sql
         * method to execute above sql query.
         */
        db.execSQL(query);
    }

    /**
     * @param name
     * @param bloodGroup
     * @param location
     * @param email
     * this method is use to add new doner to our sqlite database.
     */
    public void addNewCourse(String name, String bloodGroup, String location, String email) {

        /**
         * on below line we are creating a variable for
         * our sqlite database and calling writable method
         * as we are writing data in our database.
         */

        SQLiteDatabase db = this.getWritableDatabase();

        /**
         * on below line we are creating a
         * variable for content values.
         */

        ContentValues values = new ContentValues();

        /**
         * on below line we are passing all values
         * along with its key and value pair.
         */

        values.put(NAME_COL, name);
        values.put(DURATION_COL, bloodGroup);
        values.put(DESCRIPTION_COL, location);
        values.put(TRACKS_COL, email);

        /**
         * after adding all values we are passing,
         * content values to our table.
         */

        db.insert(TABLE_NAME, null, values);

        /**
         * at last we are closing our,
         * database after adding database.
         */
        db.close();
    }

    /**
     * @return a new method for reading all the doner.
     */
    public ArrayList<DonerModal> readDoner() {
        /**
         * on below line we are creating a
         * database for reading our database.
         */

        SQLiteDatabase db = this.getReadableDatabase();

        /**
         *on below line we are creating a new Courser.
         */
        Cursor cursorDoner = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        /**
         * on below line we are creating a new array list.
         */

        ArrayList<DonerModal> donerModalArrayList = new ArrayList<>();

        /**
         * moving our cursor to first position.
         */
        if (cursorDoner.moveToFirst()) {
            do {
                /**
                 * on below line we are adding the data from cursor to our array list.
                 */
                donerModalArrayList.add(new DonerModal(cursorDoner.getString(1),
                        cursorDoner.getString(4),
                        cursorDoner.getString(2),
                        cursorDoner.getString(3)));
            } while (cursorDoner.moveToNext());
            /**
             * moving our cursor to next.
             */
        }
        /**
         * at last closing our cursor
         * and returning our array list.
         */
        cursorDoner.close();
        return donerModalArrayList;
    }

    /**
     * @param donerName
     * @param bloodGroup
     * @param newBloodGroup
     * @param email
     * @param location
     * below is the method for updating our Doner location.
     */
    public void updateCourse(String donerName,String bloodGroup,String newBloodGroup,
                             String email, String location) {

        /**
         * calling a method to get writable database.
         */

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        /**
         * on below line we are passing all values
         * along with its key and value pair.
         */

        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACKS_COL, courseTracks);

        /**
         * on below line we are calling a update method to update our database and passing our values.
         * and we are comparing it with name of our course which is stored in original name variable.
         */
        db.update(TABLE_NAME, values, "name=?", new String[]{originalCourseName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /**
         * this method is called to check if the table exists already.
         */

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
