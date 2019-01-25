package com.example.joe.brainiapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Create button objects and set listeners
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.BtnLogin);
        button1.setOnClickListener(new View.OnClickListener() { // If Clicked
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        button2 = findViewById(R.id.btnSignup);
        button2.setOnClickListener(new View.OnClickListener() { // If Clicked
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
    }


    // go to activity methods
    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}


