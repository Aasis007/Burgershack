package com.example.laptop.burgershack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laptop.burgershack.Commom.Common;
import com.example.laptop.burgershack.Interface.Itemclicklistner;
import com.example.laptop.burgershack.Model.Category;
import com.example.laptop.burgershack.Viewholder.Menuviewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static android.R.attr.category;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    FirebaseDatabase database;
    DatabaseReference Category;
    TextView Fullname;
    RecyclerView recyler_menu;
    RecyclerView.LayoutManager Layoutmanager;
    FirebaseRecyclerAdapter<Category,Menuviewholder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);
        Fullname = (TextView)findViewById(R.id.Fullname);


        //Initialize firebase

            database = FirebaseDatabase.getInstance();
            Category = database.getReference("Category");



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(Home.this,Cart.class);
                startActivity(cartIntent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Set name for user

       TextView Fullname = navigationView.getHeaderView(0).findViewById(R.id.Fullname);
        Fullname.setText(Common.currentUser.getName());




        //Load menu

            recyler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
            recyler_menu.setHasFixedSize(true);
            Layoutmanager = new LinearLayoutManager(this);
            recyler_menu.setLayoutManager(Layoutmanager);
            loadMenu();

    }

    private void loadMenu() {
        adapter = new FirebaseRecyclerAdapter<Category,Menuviewholder>(Category.class,R.layout.menu_item,Menuviewholder.class,Category) {
            @Override
            protected void populateViewHolder(Menuviewholder viewHolder, Category model, int position) {

                viewHolder.txtMenuName.setText(model.getName());

                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                final Category clickitem = model;
                viewHolder.setItemclicklistner(new Itemclicklistner() {
                    @Override
                    public void onclick(View view, int position, boolean isLongClick) {
                      //Get categoryID and send to new activity

                        Intent foods = new Intent(Home.this,Foods.class);
                        foods.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foods);




                    }
                });
            }
        };
                recyler_menu.setAdapter(adapter);


            }


            @Override
            public void onBackPressed() {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    super.onBackPressed();
                }
            }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            // Handle the camera action
        } else if (id == R.id.cart) {

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
