package com.example.everyss;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.everyss.EndPoints;
import com.example.everyss.R;
import com.example.everyss.VolleyMultipartRequest;
import com.example.everyss.submitrynok;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class addrynok extends AppCompatActivity {


    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    EditText etovar, eprice, edescription, ephone;

    Button submit, upload;
    String tovar, price, description, phone, login;
    ImageView imageView;

    //edittext for getting the tags input


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.as);
        imageView = (ImageView) findViewById(R.id.imageView);
        login = getIntent().getStringExtra("login");
        etovar=findViewById(R.id.etproduct);
        eprice =findViewById(R.id.etprice);
        edescription=findViewById(R.id.etdescription);
        ephone=findViewById(R.id.etphone);
        submit=findViewById(R.id.submit);

        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tovar = etovar.getText().toString();
                price = eprice.getText().toString();
                description = edescription.getText().toString();
                phone = ephone.getText().toString();
                // uploadMultipart();

                new submitrynok(addrynok.this).execute(tovar, price, description, phone, login);

            }
        });
    }}
