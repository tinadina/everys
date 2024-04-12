package com.example.everyss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class DetailsActivity2 extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    String tovar="flowers", desciption="need money", login="arina", price="5000", phone="554", card;
    TextView tvtovar, tvprice, tvdescription, tvphone, tvcard;
    Button btn;
    private String[] imageUrls = new String[10];
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ette2);
        ViewPager viewPager = findViewById(R.id.viewPager);
        Intent intent = getIntent();
        tovar = intent.getStringExtra("tovar");
        price = intent.getStringExtra("price");
        login = intent.getStringExtra("login");
        card = intent.getStringExtra("card");
        phone = intent.getStringExtra("phone");
        desciption = intent.getStringExtra("description");
        Toast.makeText(DetailsActivity2.this, desciption, Toast.LENGTH_SHORT).show();
        tvcard = findViewById(R.id.tvcard);
        tvtovar = findViewById(R.id.tv1);
        tvprice = findViewById(R.id.tv2);
        tvdescription = findViewById(R.id.tvdesc);
        tvphone = findViewById(R.id.tvnumb);
        //tvurl = findViewById(R.id.url);
        tvdescription.setText(desciption);
        tvcard.setText(card);
        tvphone.setText(phone);
        tvprice.setText(price);
        tvtovar.setText(tovar);
        //tvurl.setText(url);
        btn = findViewById(R.id.btnlink);
        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity2.this, userprofile.class);
                i.putExtra("login", login);
                startActivity(i);
            }
        });
    }

    }

