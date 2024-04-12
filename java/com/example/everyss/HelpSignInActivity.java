package com.example.everyss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HelpSignInActivity extends AsyncTask<String, Void, String> {
    private Context context;
    String login;
    public HelpSignInActivity(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        login = strings[0];
        String password = strings[1];
        String data;
        String link;
        String result;
        BufferedReader bufferedReader;
        try {
            data = "?login=" + URLEncoder.encode(login, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");

            link = "http://truffel.000webhostapp.com/signinhelpers.php"+data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch(Exception e){
            return new String("Execution: " + e.getMessage());
        }
    }
    @Override
    protected void onPostExecute(String result){
        String jsonStr = result;
        if(jsonStr!=null){
            try{
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result =   jsonObj.getString("query_result");

                if(query_result.equals("SUCCESS")){

                    Intent intent= new Intent(context, Main2Activity.class);
                    intent.putExtra("username",login);
                    context.startActivity(intent);
                }
                else if(query_result.equals("FAILURE"))
                    Toast.makeText(context, "incorrect username or password", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "can not connect to database", Toast.LENGTH_SHORT).show();
            } catch(JSONException e){
                e.printStackTrace();
                Toast.makeText(context, "server returnes mistake", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Сервер не отвечает", Toast.LENGTH_SHORT).show();
        }
    }


}
