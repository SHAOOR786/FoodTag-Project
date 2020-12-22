package com.example.foodtag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

public class Scanner extends AppCompatActivity implements ValueEventListener {
    CodeScanner codeScanner;
    CodeScannerView  scanView;
    TextView resultView;
    DatabaseReference DatabaseRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        scanView =findViewById(R.id.scanner_view);
        codeScanner= new CodeScanner(this,scanView);
        resultView= findViewById(R.id.resultQr);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        find(result);
                    }
                });
            }
        });
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        codeScanner.startPreview();
    }

    private void find(Result result)
    {
        String id = result.getText().toString();
        DatabaseRestaurant = FirebaseDatabase.getInstance().getReference("restaurant").child(id);
        DatabaseRestaurant.addValueEventListener(this);

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists())
        {
            String address=snapshot.child("address").getValue().toString();
            String email=snapshot.child("email").getValue().toString();
            String name=snapshot.child("name").getValue().toString();
            String phone=snapshot.child("phone").getValue().toString();
            String photo= snapshot.child("photo").getValue().toString();
            Intent i = new Intent(this, RestaurantMenu.class);
            i.putExtra("address",address);
            i.putExtra("email",email);
            i.putExtra("name",name);
            i.putExtra("phone",phone);
            i.putExtra("photo",photo);
            startActivity(i);


        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }




}

