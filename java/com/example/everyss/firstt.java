package com.example.everyss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firstt extends AppCompatActivity {

    private Button helper;
    private Button dest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstt);
        dest = findViewById(R.id.loginBtn);
        helper = findViewById(R.id.registerBtn);


        dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(firstt.this, logindestt.class);
                startActivity(i);
            }
        });

        helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(firstt.this, login.class);
                startActivity(i);
            }
        });
    }
 }

