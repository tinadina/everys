package com.example.everyss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class first extends AppCompatActivity {

private Button helper;
private Button dest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        dest = findViewById(R.id.loginBtn);
        helper = findViewById(R.id.registerBtn);


        dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(first.this, logindest.class);
            startActivity(i);
            }
        });

        helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(first.this, login.class);
                startActivity(i);
            }
        });
    }
}
