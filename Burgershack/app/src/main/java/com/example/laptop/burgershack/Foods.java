package com.example.laptop.burgershack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.laptop.burgershack.Interface.Itemclicklistner;
import com.example.laptop.burgershack.Model.Food;
import com.example.laptop.burgershack.Viewholder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Foods extends AppCompatActivity {
    RecyclerView recyclerView_food;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference foods;
    String categoryId= "";
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);


        //Firebase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Foods");

        recyclerView_food = (RecyclerView) findViewById(R.id.recycler_food);
        recyclerView_food.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView_food.setLayoutManager(layoutManager);
        //Get Intent here

        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId != null) {
            loadListFoods(categoryId);
        }


    }

    private void loadListFoods(String categoryId) {

        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foods.orderByChild("MenuId:").equalTo(categoryId)

        ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, final Food model, int position) {

                viewHolder.food_name.setText(model.getName());
                System.out.println("CategoryId:"+model.getMenuId());


                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.food_image);

                final Food local = model;
                viewHolder.setItemclicklistner(new Itemclicklistner() {
                    @Override
                    public void onclick(View view, int position, boolean isLongClick) {
                        Toast.makeText(Foods.this, "" + local.getName(), Toast.LENGTH_SHORT).show();


                    }
                });
            }
        };


                //set adapter
                Log.d("TAG",""+adapter.getItemCount());
                recyclerView_food.setAdapter(adapter);



            }
        }










