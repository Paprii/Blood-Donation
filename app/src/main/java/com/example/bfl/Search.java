package com.example.bfl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/**
A Class that enables a user to search for donor by BloodGroup.User Can search
by Bloodgroup and can retrieve donor's given data and thus can contact with the donor.

 */
public class Search extends AppCompatActivity {
   /**
   field for selecting Donors table to search from
    */
    DatabaseReference dbReference;
    /**
    field for to set a adapter for displaying the specific donor's data searched by user
     */
    private ListView listData;
    /**
    field for For autocompleting the search field
     */
    private AutoCompleteTextView textSearch;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        Sets the app to search Page Layout
         */
        setContentView(R.layout.activity_search);
        /*
        will get the reference of Donor from Firebase Database
         */
        dbReference= FirebaseDatabase.getInstance().getReference("Donors");
        listData=(ListView)findViewById(R.id.activity_search_lv_search);
        textSearch=(AutoCompleteTextView)findViewById(R.id.activity_search_acv_autocomplete);

        ValueEventListener event=new ValueEventListener() {
            /**
             *reads static snapshot of the contents at Donor data path in Firebase
             * @param snapshot represents a portion of data from a given path in firebase DB.Here it is from donor path.
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbReference.addListenerForSingleValueEvent(event);
    }

    /**
     *This static array has been declared for the autocompletion in the searchfield.
     * User can type A and there will be suggestions for autocompletion based on users given input for searching that data which matches the patterns given in this array.
     */
    private static final String[] NAMES = new String[] {
            "A+", "AB+", "B+", "O+","A-","B-","AB-","O-"
    };

    /**
     *initialize the search Activity
     *Puts every bloodgroup data in a a Arraylist
     * parses the bloodgroup user wants to search
     * and when user enter the bg to search then this function initialize search process by Calling Search function from this function.
     * @param snapshot represents a portion of data from a given path in firebase DB.Here it is from donor path.
     */
    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> names=new ArrayList<>();
        if(snapshot.exists()){
          for(DataSnapshot ds:snapshot.getChildren())
          {
              String name=ds.child("bloodGroup").getValue(String.class);
              names.add(name);
          }
            ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,NAMES);
          textSearch.setAdapter(adapter);
          textSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              /**
               * callback method will be invoked when user will click a BG from the autocompleted suggestion field in this adapterView.
               * @param parent The AdapterView where the call has happened
               * @param view The view within the adapter that was clicked
               * @param position the position of the view n the Adapter
               * @param id the row id of the item that was clicked
               */
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                puts the value given in searchbox in the name field
                 */
                  String name=textSearch.getText().toString();
                searchUser(name);
              }
          });
        }
        else {
            Log.d("Donors","No Data Found");
        }

    }

    /**
     *This functions search for the Donors in our DB by comparing it to the given search string by user.
     * Adds all records of the data matched by searched BG into an ArrayList.
     * It also displays the data added in the Arraylist by setting Adapter .
     * @param name Given Bloodgroup in the search field
     */
    private void searchUser(String name) {
        Query query=dbReference.orderByChild("bloodGroup").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                 ArrayList<String> listUser= new ArrayList<>();
                  for(DataSnapshot ds:snapshot.getChildren())  {
                      Profile user=new Profile(ds.child("name").getValue(String.class),ds.child("bloodGroup").getValue(String.class),ds.child("phone").getValue(String.class),ds.child("email").getValue(String.class),ds.child("weight").getValue(String.class));
                      listUser.add(user.getName()+"\n"+user.getBloodGroup()+"\n"+user.getPhone()+"\n"+user.getEmail()+"\n"+user.getWeight());
                  }
                  ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,listUser);
                  listData.setAdapter(adapter);
                }
                else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}