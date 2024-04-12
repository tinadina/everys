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


public class GetEarning extends AsyncTask<String, Void, String> {

    private Context context;
    private String fund,earning;
    public GetEarning(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
        fund = arg0[0];
        earning = arg0[1];
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?fund=" + URLEncoder.encode(fund, "UTF-8");
            data += "&earning=" + URLEncoder.encode(earning, "UTF-8");
            link = "http://truffel.000webhostapp.com/updateEarning.php" + data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("SUCCESS")) {
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();

                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Нет интернет соединения", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Сервер не отвечает", Toast.LENGTH_SHORT).show();
        }
    }
}