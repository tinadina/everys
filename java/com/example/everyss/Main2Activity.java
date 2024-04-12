package com.example.everyss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
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

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView recycler;
    private HelpAdapter mAdapter;
    private TextView txt,txt2,txt3,txt1;
    public String username;
    DrawerLayout drawer;
    private Fragment fragment = null;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Main2Activity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, RecyclerViewFragment.newInstance()).commit();
        username=getIntent().getStringExtra("username");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
            /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

       /* ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.List) {
            Main2Activity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, RecyclerViewFragment.newInstance()).commit();


            // Handle the interteach action


        } else if (id == R.id.Ad) {
            Main2Activity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, Ad.newInstance()).commit();

          /*  Intent intent = new Intent(Main2Activity.this, PoliceActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);*/
        } else if (id == R.id.Profile) {
            Intent intent = new Intent(Main2Activity.this, destprofile.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else if (id == R.id.Market) {
            Intent intent = new Intent(Main2Activity.this, TovarActivity.class);
            // intent.putExtra("username", username);
            startActivity(intent);
        } else if (id == R.id.Sharing) {
            Intent intent = new Intent(Main2Activity.this, ShareActivity.class);
            // intent.putExtra("username", username);
            startActivity(intent);}/*else if (id == R.id.nav_share) {

        }  else if (id == R.id.nav_send) {

        }*/

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }
