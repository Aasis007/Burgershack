package com.example.laptop.burgershack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnsignin,btnsignup;
    TextView slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsignup = (Button)findViewById(R.id.signup);
        btnsignin = (Button)findViewById(R.id.signin);
        slogan = (TextView)findViewById(R.id.slogan);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup =new Intent(MainActivity.this,Signup.class );
                startActivity(signup);

            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Signin = new Intent(MainActivity.this,Signin.class);
                startActivity(Signin);
            }
        });


    }
}
