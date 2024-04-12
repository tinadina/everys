package com.example.everyss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class UserPosts extends AppCompatActivity {
    TextView tvName, tvPur, tvEarn;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    String login,earn,pur,name,id, city;
    Button btnAdd;
    List<News> data=new ArrayList<>();
    TextView tvname,tvcity,tvearned,textt;

    private RecyclerView recyclerView;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if(getIntent().hasExtra("id")&& getIntent().hasExtra("name")){
            Toast.makeText(UserPosts.this,"yes",Toast.LENGTH_SHORT).show();
         id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
            city=getIntent().getStringExtra("city");

        }
        else Toast.makeText(UserPosts.this,"no",Toast.LENGTH_SHORT).show();

        tvname=findViewById(R.id.tv_name);
        tvcity=findViewById(R.id.tv_name);
        tvearned=findViewById(R.id.tv_earned);
        textt=findViewById(R.id.text);
        tvname.setText(name);
        tvcity.setText(city);
        tvearned.setText("");
        textt.setText("");
        recyclerView = (RecyclerView)findViewById(R.id.recyc);

        new AsyncFetch().execute();

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
                url = new URL("https://truffel.000webhostapp.com/news.php?id="+id);

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



            try {


                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    News helpData = new News();
                    helpData.name= name;
                    helpData.title= json_data.getString("title");
                    helpData.description= json_data.getString("description");


                    data.add(helpData);}

                recyclerView.setLayoutManager(new LinearLayoutManager(UserPosts.this));

                adapter = new NewsAdapter(UserPosts.this, data);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


                // Setup and Handover data to recyclerview



            } catch (JSONException e) {
                Toast.makeText(UserPosts.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
