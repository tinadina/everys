package com.example.everyss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

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

public class DetailsActivity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    String tovar, desciption, login, price, phone, url;
    TextView tvtovar, tvprice, tvdescription, tvphone;
    Button btn;
    private String[] imageUrls= new String[100] ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ette);

        Intent intent = getIntent();
        //imageUrls=intent.getStringExtra("url");
        tovar = intent.getStringExtra("tovar");
        price = intent.getStringExtra("price");
        login = intent.getStringExtra("login");
        phone = intent.getStringExtra("phone");
        desciption = intent.getStringExtra("description");
        url = intent.getStringExtra("url");
        ImageView img = findViewById(R.id.viewPager);
        Picasso.get().load( url).into(img);
        Toast.makeText(DetailsActivity.this, login, Toast.LENGTH_SHORT).show();
        //tvcard = findViewById(R.id.card);
        tvtovar = findViewById(R.id.tv1);
        tvprice = findViewById(R.id.tv2);
        tvdescription = findViewById(R.id.tvdesc);
        tvphone = findViewById(R.id.tvnumb);
        //tvurl = findViewById(R.id.url);
        tvdescription.setText(desciption);
        //tvcard.setText(card);
        tvphone.setText(phone);
        tvprice.setText(price);
        tvtovar.setText(tovar);
        //tvurl.setText(url);
        btn = findViewById(R.id.btnlink);
        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.


       // new Async().execute();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this, userprofile.class);
                i.putExtra("login", login);
                startActivity(i);
            }
        });
    }

    private class Async extends AsyncTask<String, String, String> {

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
                url = new URL("https://truffel.000webhostapp.com/api2.php?login="+login);

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

            ArrayList<String> photourl=new ArrayList<>();
            try {


                JSONArray jArray = new JSONArray(result);
                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    String image = json_data.getString("image");
                    String url="https://truffel.000webhostapp.com/uploads/"+image;
                    imageUrls[i]=url;
                    //Photo photo = new Photo();
                    // photo.url=json_data.getString("url");
                    // data.add(photo);
                    //imageUrls[i]=json_data.getString("url");
                   // Toast.makeText(DetailsActivity.this,url, Toast.LENGTH_SHORT).show();
                    //data.add(photo);
                }
                 // Setup and Handover data to recyclerview



            } catch (JSONException e) {
                Toast.makeText(DetailsActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
