package com.example.everyss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class destprofile  extends AppCompatActivity {
    String name, login, age, email, earned, phone, city;
    TextView tvname, tvlogin, tvage, tvemail, tvphone, tvcity, tvearned;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileuser);

        Intent intent = getIntent();
        login = intent.getStringExtra("username");
        //login = "akgul";
        tvname=findViewById(R.id.tv_name);
        tvlogin=findViewById(R.id.tv_login);
        tvage=findViewById(R.id.tv_age);
        tvemail=findViewById(R.id.tv_email);
        tvphone=findViewById(R.id.tv_phone);
        tvcity=findViewById(R.id.tv_address);
        tvearned=findViewById(R.id.tv_earned);

        getJSON("https://truffel.000webhostapp.com/profilehelper.php?login="+login);

    }

    /*public void buttonclick(View v) {

         String enLogin = (String) tvlogin.getText();
         String enPassword = (String) tvPassword.getText();
         String enFullname = (String) tvFullname.getText();
         String enAge = (String) tvAge.getText();
         String enTelephone = (String) tvTelephone.getText();

         Intent intent = new Intent(profile.this, updatevisual.class);
         intent.putExtra("login", login);
         intent.putExtra("fullname", fullname);
         intent.putExtra("password", password);
         intent.putExtra("age", age);
         intent.putExtra("telephone", telephone);
         startActivity(intent);
         finish();
     }*/
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }


    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            // Add a marker in Sydney and move the camera
            name = obj.getString("name");
            age= obj.getString("age");
            phone = obj.getString("telephone");
            city = obj.getString("city");
            earned= obj.getString("earned");
            email = obj.getString("email");

            tvname.setText(name);
            tvlogin.setText(login);
            tvage.setText(age);
            tvemail.setText(email);
            tvphone.setText(phone);
            tvcity.setText(city);
            tvearned.setText(earned);;

        }
    }
}
