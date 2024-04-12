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


public class submitrynok extends AsyncTask<String, Void, String> {

    private Context context;
    String tovar,login;
    public submitrynok(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
         tovar = arg0[0];
        String price = arg0[1];
        String description = arg0[2];
        String phone = arg0[3];
         login = arg0[4];
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?tovar=" + URLEncoder.encode(tovar, "UTF-8");
            data += "&price=" + URLEncoder.encode(price, "UTF-8");
            data += "&description=" + URLEncoder.encode(description, "UTF-8");
            data += "&phone=" + URLEncoder.encode(phone, "UTF-8");
            data += "&login=" + URLEncoder.encode(login, "UTF-8");
            link = "http://truffel.000webhostapp.com/addrynok.php" + data;
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
                    Toast.makeText(context, "uplloaded succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(context, RynokImg.class);
                    intent.putExtra("login",login);
                    intent.putExtra("tovar",tovar);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "Ошибка при регистрации", Toast.LENGTH_SHORT).show();
                } else if (query_result.equals("HAVELOG")) {
                    Toast.makeText(context, "Логин уже занят", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Не удается подключится к базе данных", Toast.LENGTH_SHORT).show();
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