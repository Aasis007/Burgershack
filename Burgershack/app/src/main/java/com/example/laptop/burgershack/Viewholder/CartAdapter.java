package com.example.laptop.burgershack.Viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.laptop.burgershack.Interface.Itemclicklistner;
import com.example.laptop.burgershack.Model.Order;
import com.example.laptop.burgershack.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Laptop on 11/24/2017.
 */
class CartViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView tvcartname,tvcartprice,tvcartquant;
    private Itemclicklistner itemclicklistner;
    public void setText_cart_name (TextView tvcartname){
        this.tvcartname = tvcartname;

    }

    public CartViewholder(View itemView) {
        super(itemView);
        tvcartname = (TextView)itemView.findViewById(R.id.carditemname);
        tvcartquant =(TextView)itemView.findViewById(R.id.quantity);
        tvcartprice =(TextView)itemView.findViewById(R.id.price);
    }

    @Override
    public void onClick(View view) {

    }
}
public class CartAdapter extends RecyclerView.Adapter<CartViewholder>{


    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public CartViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout,parent,false);
        return new CartViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewholder holder, int position) {

        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getQuantity()));
        holder.tvcartprice.setText(fmt.format(price));
        holder.tvcartname.setText(listData.get(position).getProductName());
        holder.tvcartquant.setText(listData.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
