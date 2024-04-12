package com.example.everyss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AddNews extends AppCompatActivity {
String id;
EditText edTitle,edDesc;
String title,desc;
    String title1,desc1,id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        id= getIntent().getStringExtra("id");
        /*TextView txt=(TextView)findViewById(R.id.idtext);
        txt.setText(id);*/
        edTitle= (EditText) findViewById(R.id.title);
        edDesc= (EditText) findViewById(R.id.desc);
        Button btn=(Button)findViewById(R.id.senddata);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=edTitle.getText().toString();
                desc=edDesc.getText().toString();
                new SendNews(AddNews.this).execute(id,title,desc);
            }
        });
    }

    }
