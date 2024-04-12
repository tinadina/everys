package com.example.everyss;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class addsharing extends AppCompatActivity {


    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    EditText etovar, eprice, edescription, ephone, ecard;

    Button submit;
    String tovar, price, description, phone, login, card;


    //edittext for getting the tags input


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharingg);

        login = getIntent().getStringExtra("login");
        etovar=findViewById(R.id.etproduct);
        eprice =findViewById(R.id.etprice);
        edescription=findViewById(R.id.etdescription);
        ephone=findViewById(R.id.etphone);
        ecard=findViewById(R.id.etcard);
        submit=findViewById(R.id.submit);

        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tovar = etovar.getText().toString();
                price = eprice.getText().toString();
                description = edescription.getText().toString();
                phone = ephone.getText().toString();
                card = ecard.getText().toString();

                new submitsharing(addsharing.this).execute(tovar, price, description, phone, card, login);
            }
        });
    }

}
