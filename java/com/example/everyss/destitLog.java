package com.example.everyss;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

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

public class destitLog extends AsyncTask<String, String, String> {
    private Context context;
    HttpURLConnection conn;
    URL url = null;
    public destitLog(Context context){
        this.context = context;
    }
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
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
            url = new URL("https://truffel.000webhostapp.com/signin.php?login=dinwolt&password=0689lol");

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
            String result;
            int response_code = conn.getResponseCode();

            // Check if successful connection made
            if (response_code == HttpURLConnection.HTTP_OK) {

                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                 result = reader.readLine();
                return (result.toString());
              /* StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Pass data to onPostExecute method
                return (result.toString());*/

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

                String query_result=json_data.getString("query_result");
                if (query_result.equals("SUCCESS")) {
                    Toast.makeText(context, "Добро пожаловать !", Toast.LENGTH_SHORT).show();
                    Intent intent_name = new Intent(context.getApplicationContext(),Main2Activity.class);
                    //intent_name.putExtra("username",login);
                    context.startActivity(intent_name);

                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "Неправильный логин или пароль ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Не удается подключится к базе данных", Toast.LENGTH_SHORT).show();
                }

               }





        } catch (JSONException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

}

