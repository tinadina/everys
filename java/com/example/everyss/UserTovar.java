package com.example.everyss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class UserTovar extends AppCompatActivity implements TovarAdapter.OnNoteListener{

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView recycler;
    private TovarAdapter mAdapter;
    private TextView txt,txt2,txt3,txt1;
    String login;
    private ArrayList data = new ArrayList<>();
    String[][] arr = new String[100][6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Make call to AsyncTask
        login=getIntent().getStringExtra("login");
        new AsyncFetch().execute();
    }

    @Override
    public void onNoteClick(int position) {
      /*  data.get(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        //intent.putExtra("some",position);
        startActivity(intent);
        Toast.makeText(HelpActivity.this,"id", Toast.LENGTH_SHORT).show();*/
    }

    private class AsyncFetch extends AsyncTask<String, String, String> implements TovarAdapter.OnNoteListener {
        ProgressDialog pdLoading = new ProgressDialog(UserTovar.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://truffel.000webhostapp.com/user_rynok.php?login="+login);

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

            pdLoading.dismiss();
            ArrayList<Tovar> data=new ArrayList<>();

            pdLoading.dismiss();
            try {


                JSONArray jArray = new JSONArray(result);


                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    Tovar helpData = new Tovar();
                    helpData.tovar= json_data.getString("tovar");
                    helpData.price= json_data.getString("price");
                    helpData.url= json_data.getString("login");
                    helpData.phone= json_data.getString("phonee");
                    helpData.description= json_data.getString("description");
                    arr[i][0]=json_data.getString("tovar");
                    arr[i][1]=json_data.getString("price");
                    arr[i][2]=json_data.getString("login");
                    arr[i][3]=json_data.getString("phonee");
                    arr[i][4]=json_data.getString("description");



                    data.add(helpData);

                }

                // Setup and Handover data to recyclerview
                recycler = (RecyclerView)findViewById(R.id.my_recycler_view);
                mAdapter = new TovarAdapter(UserTovar.this, data, this);
                recycler.setAdapter(mAdapter);
                recycler.setLayoutManager(new GridLayoutManager(UserTovar.this,2));

            } catch (JSONException e) {
                Toast.makeText(UserTovar.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onNoteClick(int position) {
            String pos = ""+position;
            Intent intent = new Intent(UserTovar.this, DetailsActivity.class);
            intent.putExtra("tovar",arr[position][0]);
            intent.putExtra("price",arr[position][1]);
            intent.putExtra("login",arr[position][2]);
            intent.putExtra("phone",arr[position][3]);
            intent.putExtra("description",arr[position][4]);
            intent.putExtra("url",arr[position][5]);




            //  Toast.makeText(HelpActivity.this,arr[position][0], Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }
}
