package com.example.everyss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class userprofile extends AppCompatActivity {
    String name, login, age, email, earned, phone, city, card;
    TextView tvname, tvlogin, tvage, tvemail, tvphone, tvcity, tvearned, tvcard, tvdetail;
    Button btnAddPost, btnViewPost,btnViewTovar, btnAddTovar, btnAddSharing, btnViewSharing;
    LinearLayout adding;
    String id;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profdest);
        //login = "akgul";
        tvname=findViewById(R.id.tv_name);
        tvlogin=findViewById(R.id.tv_login);
        tvage=findViewById(R.id.tv_age);
        tvemail=findViewById(R.id.tv_email);
        tvphone=findViewById(R.id.tv_phone);
        tvcity=findViewById(R.id.tv_address);
        tvearned=findViewById(R.id.tv_earned);
        tvcard=findViewById(R.id.tv_card);
        tvdetail=findViewById(R.id.tv_detail);
        btnAddPost=(Button)findViewById(R.id.button_addnews);
        btnViewPost=(Button)findViewById(R.id.button_posts);
        btnAddTovar=(Button)findViewById(R.id.button_addtovar);
        btnViewTovar=(Button)findViewById(R.id.button_tovar);
        btnAddSharing=(Button)findViewById(R.id.button_addshare);
        btnViewSharing=(Button)findViewById(R.id.button_sharing);
        adding=findViewById(R.id.adding);

        if(getIntent().hasExtra("id")&& getIntent().hasExtra("name")){
            adding.setVisibility(View.GONE);

            Intent intent = getIntent();
            id=intent.getStringExtra("id");
            name=intent.getStringExtra("name");


            btnViewPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(userprofile.this,UserPosts.class);
                    intent.putExtra("id",id);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }
            });

            btnViewTovar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(userprofile.this,UserTovar.class);
                    intent.putExtra("login",name);
                    startActivity(intent);
                }
            });
            btnViewSharing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
               /* Intent intent = new Intent(userprofile.this,UserTovar.class);
                intent.putExtra("login",login);
                startActivity(intent);*/
                }
            });
        } else {
            Intent intent = getIntent();
            login = intent.getStringExtra("login");

        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userprofile.this,AddNews.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        btnViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userprofile.this,UserPosts.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("city",tvcity.getText().toString());
                startActivity(intent);
            }
        });
        btnAddTovar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userprofile.this,addrynok.class);
                intent.putExtra("login",login);
                startActivity(intent);
            }
        });
        btnViewTovar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userprofile.this,UserTovar.class);
                intent.putExtra("login",login);
                startActivity(intent);
            }
        });}
        btnAddSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userprofile.this,addsharing.class);
                intent.putExtra("login",login);
                startActivity(intent);
            }
        });
        btnViewSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userprofile.this,user_sharing.class);
                intent.putExtra("login",login);
                startActivity(intent);
            }
        });
        getJSON("https://truffel.000webhostapp.com/prof.php?login="+login+"&id="+id);
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
           city = obj.getString("detail");
            earned= obj.getString("earned");
            email = obj.getString("email");
            card = obj.getString("card");
            id=obj.getString("id");
            tvname.setText(name);
            tvlogin.setText(login);
            tvage.setText(age);
            tvemail.setText(email);
            tvphone.setText(phone);
            tvcity.setText("Aktobe, Kazakhstan");
            tvdetail.setText(city);
            tvearned.setText(earned);
            tvcard.setText(card);

        }

    }

}