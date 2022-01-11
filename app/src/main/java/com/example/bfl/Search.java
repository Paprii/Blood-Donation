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

    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> names=new ArrayList<>();
        if(snapshot.exists()){
          for(DataSnapshot ds:snapshot.getChildren())
          {
              String name=ds.child("name").getValue(String.class);
              names.add(name);
          }
            ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,names);
          textSearch.setAdapter(adapter);
          textSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  
              }
          });
        }
        else {
            Log.d("Donors","No Data Found");
        }

    }
}