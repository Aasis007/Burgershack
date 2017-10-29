package com.example.laptop.burgershack;

import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.laptop.burgershack.Model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Food_desc extends AppCompatActivity {

    TextView food_name,food_price,food_descr;
    ImageView food_desc;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btn_cart;
    ElegantNumberButton numberButton;

    String FoodId="";

    FirebaseDatabase database;
    DatabaseReference foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_desc);

        //Initiliaze Firebase

        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Foods");

        //Initialize Views

        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btn_cart = (FloatingActionButton) findViewById(R.id.btn_Cart);
        food_descr = (TextView)findViewById(R.id.food_description);
        food_name = (TextView)findViewById(R.id.food_name);
        food_price =(TextView)findViewById(R.id.food_price);
        collapsingToolbarLayout =(CollapsingToolbarLayout)findViewById(R.id.colapse);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        food_desc =(ImageView)findViewById(R.id.food_desc);

        //Get foddID from Intent

        if (getIntent() != null)
            FoodId = getIntent().getStringExtra("FoodId");
        if (FoodId!=null && !FoodId.isEmpty()){

            getDetailFood(FoodId);
        }




    }

    private void getDetailFood(String FoodId) {

        foods.child(FoodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);

                //set Image
                Picasso.with(getBaseContext()).load(food.getImage())
                        .into(food_desc);


                collapsingToolbarLayout.setTitle(food.getName());
                food_price.setText(food.getPrice());
                food_name.setText(food.getName());
                food_descr.setText(food.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
