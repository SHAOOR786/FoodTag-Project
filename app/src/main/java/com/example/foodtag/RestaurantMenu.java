package com.example.foodtag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RestaurantMenu extends AppCompatActivity {
    TextView title;
    ImageView imgRestaurant;

    String Name;
    String Photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);
        title=findViewById(R.id.tvRestaurantName);
        imgRestaurant=findViewById(R.id.imgRestaurant);
        Name=getIntent().getStringExtra("name");
        Photo=getIntent().getStringExtra("photo");

        title.setText(Name.toString());
        Picasso.get().load(Photo).into(imgRestaurant);

    }
}