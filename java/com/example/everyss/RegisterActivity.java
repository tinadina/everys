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

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etName, etLastName, etDetail;
    private EditText etNumber, etCard, etPurpose;
    private EditText etAge, etEmail;
    private EditText etAddress;
    private EditText etField;
    private String username;
    private String number;
    private String login,password,age,telephone,email,name,lastname,detail,card,purpose, fundid,problemid;
    private String confirmPassword;
    private String fullName;
    private String address;
    private String field;
    private Spinner spinFund;
    private Spinner spinProblem;
    RelativeLayout donn;
    TextView text1;
    TextView text2;
    int intfund, intproblem;

    //  List<String> fund;
    // List<String> problem;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        etUsername = findViewById(R.id.loginEt);
        etPassword = findViewById(R.id.passwordEt);
        etName = findViewById(R.id.nameEt);
        etNumber = findViewById(R.id.numberEt);
        etAge = findViewById(R.id.ageEt);
        etEmail = findViewById(R.id.emailEt);
        etDetail = findViewById(R.id.detailEt);
        etCard = findViewById(R.id.cardEt);
        etPurpose = findViewById(R.id.purposeEt);
        spinFund = findViewById(R.id.spinner);
        spinProblem = findViewById(R.id.spinner2);
        Button register = findViewById(R.id.registerBtn);
        text1 = (TextView)findViewById(R.id.haveAccountTv);



        spinFund.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                intfund = spinFund.getSelectedItemPosition()+1;

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinProblem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                intproblem=spinProblem.getSelectedItemPosition()+1;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        new AsyncFetch().execute();
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
                name = etName.getText().toString().trim();
                fundid = Integer.toString(intfund).trim();
                problemid = Integer.toString(intproblem).trim();;
                card = etCard.getText().toString().trim();
                detail = etDetail.getText().toString().trim();
                purpose = etPurpose.getText().toString().trim();


                new DonSignUpActivity(RegisterActivity.this).execute(login,password,age,telephone,email,name,lastname,fundid,problemid,detail,card,purpose);

            }
        });
    }



    private class AsyncFetch extends AsyncTask<String, String, String> {

        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread


        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("https://truffel.000webhostapp.com/forspinner.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            List<String> fund=new ArrayList<>();
            List<String> problem=new ArrayList<>();





            try {


                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    String f = json_data.getString("fund");
                    String p = json_data.getString("problem");
                    fund.add(f);
                    problem.add(p);
                }

                ArrayAdapter<String> forfund = new ArrayAdapter<String>
                        (RegisterActivity.this, android.R.layout.simple_spinner_item,fund);

                forfund.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinFund.setAdapter(forfund);

                ArrayAdapter<String> forproblem= new ArrayAdapter<String>
                        (RegisterActivity.this, android.R.layout.simple_spinner_item,problem);

                spinProblem.setAdapter(forproblem);


                // Setup and Handover data to recyclerview



            } catch (JSONException e) {
                Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}