package com.example.everyss;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.everyss.HelpAdapter;
import com.example.everyss.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

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

public class Ad extends Fragment implements RewardedVideoAdListener {
    private View view;
    private RecyclerView recyclerView;
    private HelpAdapter adapter;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RewardedVideoAd mAd;
    Context context = getContext();
    Button btn2, btnSend;
    TextView text, textfund;
    Spinner spin;
    String earning, fund;
    int i;
    String f;
    int count;

    public static Ad newInstance()
    {
        Ad recyc=new Ad();

        return recyc;}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ad_view, container, false);
        new AsyncFetch().execute();
        btn2=view.findViewById(R.id.button2);
        btnSend=view.findViewById(R.id.btnSend);
        text=view.findViewById(R.id.reward);
        textfund=view.findViewById(R.id.fundname);
        spin=view.findViewById(R.id.spinner);spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                fund = spin.getSelectedItem().toString();


            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        MobileAds.initialize(getActivity(), "ca-app-pub-6773511974022667~1867635071");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAd= MobileAds.getRewardedVideoAdInstance(getContext());
        mAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
        if (!mAd.isLoaded())i=0; else i=50;
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAd.isLoaded()){
                    mAd.show();
                    Toast.makeText(getActivity(),"wait for loading",Toast.LENGTH_SHORT).show();}
                else Toast.makeText(getActivity(),"loading failed",Toast.LENGTH_SHORT).show();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               earning = Integer.toString(i);
               fund = spin.getSelectedItem().toString();
               new UpdateEarning(getActivity()).execute(fund,earning);
                textfund.setText(Integer.toString(i));
            }
        });
          return view;
    }

    private void loadRewardedVideoAd() {
        if(!mAd.isLoaded()){
            Toast.makeText(getActivity(),"Wait for loading",Toast.LENGTH_SHORT).show();
            mAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
            if(!mAd.isLoaded()){
                Toast.makeText(getActivity(),"Ready",Toast.LENGTH_SHORT).show();

            } else Toast.makeText(getActivity(),"loading failed",Toast.LENGTH_SHORT).show();
        } else Toast.makeText(getActivity(),"not loading",Toast.LENGTH_SHORT).show();
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
                url = new URL("https://truffel.000webhostapp.com/forspinner.php");

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
            List<String> fund=new ArrayList<>();

            try {


                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    String f = json_data.getString("fund");

                    fund.add(f);

                }

                ArrayAdapter<String> forfund = new ArrayAdapter<String>
                        (getActivity(), android.R.layout.simple_spinner_item,fund);

                forfund.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(forfund);




                // Setup and Handover data to recyclerview



            } catch (JSONException e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        i=i+50;
        text.setText(Integer.toString(i));
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}
