package com.example.foodtag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.User;

import static androidx.core.os.LocaleListCompat.create;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextUserId,editTextFirstName, editTextLastName, editTextEmail, editTextPhone, editTextPassword;
    Button btnCreate;
    DatabaseReference createUserDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initialise();
    }

    private void initialise() {
        editTextUserId=findViewById(R.id.editTextUserId);
        editTextFirstName=findViewById(R.id.editTextFname);
        editTextLastName=findViewById(R.id.editTextLname);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPhone=findViewById(R.id.editTextPhoneNumber);
        editTextPassword=findViewById(R.id.editTextPassword);
        btnCreate=findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        createUserDatabase = FirebaseDatabase.getInstance().getReference("user");

    }

    @Override
    public void onClick(View view) {
        int id= view.getId();

        switch (id) {
            case R.id.btnCreate:
                createAccount(); break;
        }
    }

    private void createAccount() {
        try {
            String userId = editTextUserId.getText().toString();
            String firstName = editTextFirstName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            String phoneNo = editTextPhone.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            if(userId.isEmpty())
            {
                if(firstName.isEmpty())
                {
                    if(lastName.isEmpty())
                    {
                        if(email.isEmpty())
                        {
                            if(phoneNo.isEmpty())
                            {
                                if(password.isEmpty())
                                {
                                    Toast.makeText(this,"Fill the required fields.",Toast.LENGTH_LONG).show();
                                    editTextUserId.requestFocus();
                                }
                            }
                        }
                    }
                }
            }
            else {
                User user = new User(userId, firstName, lastName, email, phoneNo, password);

                 createUserDatabase.child(String.valueOf(userId)).setValue(user);
                //specifying the unique key for the person to be added which is userId in this case
                Toast.makeText(this,
                        "User with id " + userId + " is added successfully into the system.",
                        Toast.LENGTH_LONG).show();


                editTextUserId.setText(null);
                editTextFirstName.setText(null);
                editTextLastName.setText(null);
                editTextPhone.setText(null);
                editTextEmail.setText(null);
                editTextPassword.setText(null);
                editTextUserId.requestFocus();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this,e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

    }


}