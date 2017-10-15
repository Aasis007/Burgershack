package com.example.laptop.burgershack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laptop.burgershack.Commom.Common;
import com.example.laptop.burgershack.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Signin extends AppCompatActivity {
    EditText editphone,editpass;
    Button btnsignin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editpass = (MaterialEditText) findViewById(R.id.editpass);
        editphone = (MaterialEditText) findViewById(R.id.editphone);
        btnsignin1 = (Button) findViewById(R.id.signin1);

        //Initialize firebase database

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnsignin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(Signin.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Check User exists or not
                        mDialog.dismiss();
                        if (dataSnapshot.child(editphone.getText().toString()).exists()) {

                            //Get user data
                            mDialog.dismiss();
                            User user = dataSnapshot.child(editphone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(editpass.getText().toString())){


                                Intent homeIntent = new Intent(Signin.this,Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();


                            }



                            else
                                Toast.makeText(Signin.this, "Signin Failed!!", Toast.LENGTH_SHORT).show();
                        } else
                            mDialog.dismiss();
                            Toast.makeText(Signin.this, "U have to signUp first", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}


