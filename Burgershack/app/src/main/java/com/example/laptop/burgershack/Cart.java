package com.example.laptop.burgershack;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laptop.burgershack.Commom.Common;
import com.example.laptop.burgershack.Database.Database;
import com.example.laptop.burgershack.Model.Order;
import com.example.laptop.burgershack.Model.Request;
import com.example.laptop.burgershack.Model.Request;
import com.example.laptop.burgershack.Viewholder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference requests;
    TextView Total;
    FButton placeorder;
    Database db;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Firebase

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //Inintilialize
        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Total = (TextView)findViewById(R.id.totalprice);
        placeorder = (FButton)findViewById(R.id.Placeorder);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //Aleart dialog for address popup
                    showAleartDialog();
            }
        });
        loadListFood();
    }

    private void showAleartDialog() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(Cart.this);
        alertdialog.setTitle("One more Step");
        alertdialog.setMessage("Enter your Address");

        final EditText edtAddress = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAddress.setLayoutParams(lp);
        alertdialog.setView(edtAddress);
        alertdialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        alertdialog.setPositiveButton("GO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //create new request
                Request request = new Request(
                        Common.currentUser.getName(),
                        Common.currentUser.getPhone(),
                        edtAddress.getText().toString(),
                        Total.getText().toString(),
                        cart

                );

                //submit to firebase
                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);
                //clear cart
                new Database(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this,"Your Order Has Been Placed", Toast.LENGTH_SHORT).show();
                finish();



            }
        });
        alertdialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertdialog.show();
    }

    private void loadListFood() {

        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);


        //Calculate Total
        int total = 0;
        for (Order order:cart)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        Total.setText(fmt.format(total));



    }
}
