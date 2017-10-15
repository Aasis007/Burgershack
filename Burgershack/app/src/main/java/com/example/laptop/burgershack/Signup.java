package com.example.laptop.burgershack;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laptop.burgershack.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Signup extends AppCompatActivity {


    MaterialEditText editphon,editnam,editpwd,editcpwd;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editphon =(MaterialEditText)findViewById(R.id.editphone1);
        editnam = (MaterialEditText)findViewById(R.id.editname);
        editpwd =(MaterialEditText)findViewById(R.id.editpwd);
        editcpwd = (MaterialEditText)findViewById(R.id.editcpwd);

        signup = (Button)findViewById(R.id.signinup1);

        //Initiliaze firebase


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Signup.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Check if already a User
                        if (dataSnapshot.child(editphon.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(Signup.this, "Phone number already registered", Toast.LENGTH_SHORT).show();
                        } else
                            mDialog.dismiss();
                        User user = new User(editnam.getText().toString(), editpwd.getText().toString());
                        table_user.child(editphon.getText().toString()).setValue(user);
                        Toast.makeText(Signup.this, "SignUp successful", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
