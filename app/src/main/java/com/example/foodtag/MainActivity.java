package com.example.foodtag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {
    EditText editTextPassword,editTextUserId;
    Button btnLogin,btnRestaurantLogin;
    TextView textViewCreateAccount;
    int flag=0;



    DatabaseReference UserDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialise();
    }

    private void initialise() {
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextUserId=findViewById(R.id.editTextUserId);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        textViewCreateAccount=findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setOnClickListener(this);
        btnRestaurantLogin=findViewById(R.id.btnRestaurantLogin);
        btnRestaurantLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        
        int id= view.getId();
        
        switch (id)
        {
            case R.id.btnLogin:
                login();break;

            case R.id.textViewCreateAccount:
                signUp(); break;
            case R.id.btnRestaurantLogin:
                RestaurantLogin();break;
        }
    }

    private void RestaurantLogin() {
        String id = editTextUserId.getText().toString();
        UserDatabase= FirebaseDatabase.getInstance().getReference("restaurant").child(id);
        UserDatabase.addValueEventListener(this);
        flag=2;
    }

    private void signUp() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void login() {
        String id = editTextUserId.getText().toString();
        UserDatabase= FirebaseDatabase.getInstance().getReference("user").child(id);
        UserDatabase.addValueEventListener(this);
        flag=1;

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String id=editTextUserId.getText().toString();
        String pass=editTextPassword.getText().toString();
        switch (flag)
        {
            case 1:

                if (snapshot.exists())
                {
                    try
                    {
                        if (snapshot.child("uId").getValue().toString().equalsIgnoreCase(id) )
                        {
                            if (snapshot.child("password").getValue().toString().equalsIgnoreCase(pass))
                            {
                                Intent i = new Intent(this, secondActivity.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(this, "Wrong password for the id " + id ,
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(this, "Wrong id" ,
                                    Toast.LENGTH_LONG).show();
                        }
                    }catch(Exception e)
                    {
                        Toast.makeText(this, "One document  with the id :"+id+ " is not available ",
                                Toast.LENGTH_LONG).show();
                    }
                } break;
            case 2:

                if (snapshot.exists())
                {
                    try
                    {
                        if (snapshot.child("rId").getValue().toString().equalsIgnoreCase(id) )
                        {
                            if (snapshot.child("password").getValue().toString().equalsIgnoreCase(pass)) {
                                Intent i = new Intent(this, secondActivity.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(this, "Wrong password for the id " + id,
                                        Toast.LENGTH_LONG).show();

                            }
                        }
                    }catch(Exception e)
                    {
                        Toast.makeText(this, "One document  with the id :"+id+ " is not available ",
                                Toast.LENGTH_LONG).show();
                    }
                } break;
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
