package com.example.laptop.burgershack.Viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptop.burgershack.Interface.Itemclicklistner;
import com.example.laptop.burgershack.R;

/**
 * Created by Laptop on 10/14/2017.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public TextView food_name;
    public ImageView food_image;
    private Itemclicklistner itemclicklistner;



    public FoodViewHolder(View itemView) {
        super(itemView);


        food_name = (TextView)itemView.findViewById(R.id.food_name);
      food_image = (ImageView)itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);
    }


    public void setItemclicklistner(Itemclicklistner itemclicklistner) {
        this.itemclicklistner = itemclicklistner;
    }





    @Override
    public void onClick(View view) {

        itemclicklistner.onclick(view,getAdapterPosition(),false);
    }
}
