package com.example.everyss;


import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RegisterHelper extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etName;
    private EditText etNumber;
    private EditText etAge, etEmail;
    private EditText etAddress;
    private String username;
    private String number;
    private String login,password,age,telephone,email,name,lastname,detail,card,purpose, fundid,problemid;
    private String address;
    TextView text1;
    //  List<String> fund;
    // List<String> problem;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = findViewById(R.id.loginEt);
        etPassword = findViewById(R.id.passwordEt);
        etName = findViewById(R.id.nameEt);
        etNumber = findViewById(R.id.phoneEt);
        etAge = findViewById(R.id.ageEt);
        etAddress = findViewById(R.id.addressEt);
        etEmail = findViewById(R.id.emailEt);
        Button register = findViewById(R.id.regBtn);
        text1 = (TextView)findViewById(R.id.haveAccountTv);


        //Launch Login screen when Login Button is clicked
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts



                login = etUsername.getText().toString().toLowerCase().trim();
                password = etPassword.getText().toString().trim();
                age = etAge.getText().toString().trim();
                telephone = etNumber.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                address = etAddress.getText().toString().trim();
                name = etName.getText().toString().trim();

                new HelpSignUpActivity(RegisterHelper.this).execute(login,password,age,telephone,email, name, address);



            }
        });

    }
}