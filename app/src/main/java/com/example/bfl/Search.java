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
public class Search extends AppCompatActivity {
    DatabaseReference dbReference;
    private ListView listData;
    private AutoCompleteTextView textSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dbReference= FirebaseDatabase.getInstance().getReference("Donors");
        listData=(ListView)findViewById(R.id.activity_search_lv_search);
        textSearch=(AutoCompleteTextView)findViewById(R.id.activity_search_acv_autocomplete);

        ValueEventListener event=new ValueEventListener() {
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
    private static final String[] NAMES = new String[] {
            "A+", "AB+", "B+", "O+","A-","B-","AB-","O-"
    };
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
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=textSearch.getText().toString();
                searchUser(name);
              }
          });
        }
        else {
            Log.d("Donors","No Data Found");
        }

    }

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