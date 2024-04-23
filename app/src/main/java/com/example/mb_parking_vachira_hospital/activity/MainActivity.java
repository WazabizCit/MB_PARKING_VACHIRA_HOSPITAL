package com.example.mb_parking_vachira_hospital.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;

public class MainActivity extends ImportantMethod implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    private DrawerLayout drawer;
    CardView btn_gate_in;
    CardView btn_gate_out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Parking Mobile");


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.txt_name);
        navUsername.setText("User Admin");
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        inintInstances();

    }

    private void inintInstances() {


        btn_gate_in = findViewById(R.id.btn_gate_in);
        btn_gate_out = findViewById(R.id.btn_gate_out);


        btn_gate_in.setOnClickListener(this);
        btn_gate_out.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // handle arrow click here


        switch (id) {



        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, MainSplashActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item_history_in:
                Intent intent_in = new Intent(MainActivity.this, HistoryCarInMainActivity.class);
                startActivity(intent_in);
                finish();

                break;

            case R.id.item_history_out:
                Intent intent_out = new Intent(MainActivity.this, HistoryCarOutMainActivity.class);
                startActivity(intent_out);
                finish();

                break;



            case R.id.item_logout:
                Intent intent = new Intent(MainActivity.this, MainSplashActivity.class);
                startActivity(intent);
                finish();
                break;




        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {

        if(v == btn_gate_in){

            startActivity(new Intent(MainActivity.this, InCarMainActivity.class));
            finish();

        }else if(v == btn_gate_out){

            startActivity(new Intent(MainActivity.this, OutCarMainActivity.class));
            finish();
        }

    }







}
