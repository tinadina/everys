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


public class HelpSignUpActivity extends AsyncTask<String, Void, String> {

    private Context context;
String login, password, age, number, email, name, address;
    public HelpSignUpActivity(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... arg0) {
         login = arg0[0];
         password = arg0[1];
         age = arg0[2];
         number = arg0[3];
         email = arg0[4];
        address = arg0[5];
        name = arg0[6];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?login=" + URLEncoder.encode(login, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");
            data += "&age=" + URLEncoder.encode(age, "UTF-8");
            data += "&telephone=" + URLEncoder.encode(number, "UTF-8");
            data += "&email=" + URLEncoder.encode(email, "UTF-8");
            data += "&address=" + URLEncoder.encode(number, "UTF-8");
            data += "&name=" + URLEncoder.encode(email, "UTF-8");


            link = "https://truffel.000webhostapp.com/signuphelpers.php" + data;
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
                    Toast.makeText(context,      "Вы зарегистрированны", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(context, login.class);
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