package com.example.everyss;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class login extends AppCompatActivity {

    private Switch aSwitch;
    private EditText etUsername;
    private EditText etPassword;
    private String username;
    private String password;
    private Button loginBtn;
    private TextView noAccountTv;
    private ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loginn);
        etUsername= findViewById(R.id.loginEt);
        etPassword = findViewById(R.id.passwordEt);
        noAccountTv = findViewById(R.id.noAccountTv);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString().toLowerCase().trim();
                password = etPassword.getText().toString().trim();

                        new HelpSignInActivity(login.this).execute(username, password);

            }

        });

        noAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
               /* username = etUsername.getText().toString().toLowerCase().trim();
                password = etPassword.getText().toString().trim();*/
                Intent intent = new Intent(login.this, RegisterHelper.class);
                startActivity(intent);
                /*if (validateInputs()) {
                    if(aSwitch.isChecked()){
                        rel3.setVisibility(View.GONE);
                       // new DonSignInActivity(login.this).execute(username,password);
                    }
                    else{

                       // new HelpSignInActivity(login.this).execute(username,password);
                        }
                    /*Intent intent = new Intent(login.this, Main2Activity.class);
                    startActivity(intent);

                }*/
            }
        });
}

    /**
     * Launch Dashboard Activity on Successful Login
     */


    /**
     *
     * intent i = new Intent
     * Display Progress bar while Logging in
     */



    /**
     * Validates inputs and shows error if any
     * @return
     */
    private boolean validateInputs() {
        if(username==""){
            etUsername.setError("Username cannot be empty");
            etUsername.requestFocus();
            return false;
        }
        if(password==""){
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }
}