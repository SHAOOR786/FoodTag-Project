package com.example.foodtag;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Item;
import model.Restaurant;

public class secondActivity extends AppCompatActivity implements View.OnClickListener ,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    Button btnLogout, btnSearch;
    ImageView scanner;
    EditText editTextSearch;
    ListView lvItems;
    ArrayAdapter<Restaurant>cAdapter;
    DatabaseReference restaurantDatabaseCHILD;
    List<Restaurant> Restaurants;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialise();
    }

    private void initialise() {
        btnLogout=findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);
        scanner=findViewById(R.id.Img_Scanner);
        scanner.setOnClickListener(this);
        btnSearch=findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        editTextSearch=findViewById(R.id.editTextSearch);
        lvItems=findViewById(R.id.lvItems);
        lvItems.setOnItemClickListener(this);
        lvItems.setOnItemLongClickListener(this);

        restaurantDatabaseCHILD= FirebaseDatabase.getInstance().getReference().child("restaurant");


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnLogout:
                finish();
                break;
            case R.id.Img_Scanner:
                Intent i = new Intent(this, Scanner.class);
                startActivity(i);
            case R.id.btnSearch:
                find();

        }

    }
    private void find() {
        String search=editTextSearch.getText().toString();

        restaurantDatabaseCHILD.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Restaurants = new ArrayList<Restaurant>();

                for (DataSnapshot item : snapshot.getChildren())
                {
                    Log.d("FIREBASE",item.child("name").getValue().toString());
                    try
                    {
                        Restaurant res = item.getValue(Restaurant.class);
                       Restaurants.add(res);
                    }
                    catch (Exception e)
                    {
                        Log.d("FIREBASE",e.getMessage().toString());
                    }
                }
                 for(Restaurant name : Restaurants)
                {
                    if (name.getName().equalsIgnoreCase(search))
                    {
                        cAdapter = new ArrayAdapter<Restaurant>(secondActivity.this, android.R.layout.simple_list_item_1,Restaurants);
                        cAdapter.notifyDataSetChanged();
                        lvItems.setAdapter(cAdapter);

                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

        /*restaurantDatabaseCHILD.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Restaurants= new HashMap<String, Restaurant>();


                    String r = snapshot.child("name").getValue().toString();
                    Log.d("FIREBASE", "The name is " + r);

                    Restaurants.put(snapshot.getKey(),snapshot.getValue(Restaurant.class));
                    for (Restaurant nameR: Restaurants.values())
                    {
                        if (nameR.getName().equalsIgnoreCase(search))
                        {


                        cAdapter = new ArrayAdapter<Restaurant>(secondActivity.this, android.R.layout.simple_list_item_1, (List<Restaurant>) Restaurants.values());
                        cAdapter.notifyDataSetChanged();
                        lvItems.setAdapter(cAdapter);

                        }





                    }

                }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
}