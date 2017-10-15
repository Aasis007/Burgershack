package com.example.laptop.burgershack.Viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptop.burgershack.Interface.Itemclicklistner;
import com.example.laptop.burgershack.R;

/**
 * Created by Laptop on 10/13/2017.
 */

public class Menuviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imageView;
    private Itemclicklistner itemclicklistner;


    public Menuviewholder(View itemView) {
        super(itemView);

        txtMenuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

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
