package com.example.mb_parking_vachira_hospital.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;

public class SettingMainActivity extends ImportantMethod implements View.OnClickListener {



    CardView btn_setting_ip_address;
    CardView btn_setting_device;
    CardView btn_setting_ip_print;
    CardView btn_setting_nfc;
    CardView btn_setting_other;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ตั้งค่าเครื่อง");


        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        inintInstances();


    }

    private void inintInstances() {


        btn_setting_ip_address = findViewById(R.id.btn_setting_ip_address);
        btn_setting_device = findViewById(R.id.btn_setting_device);
        btn_setting_ip_print = findViewById(R.id.btn_setting_ip_print);
        btn_setting_nfc = findViewById(R.id.btn_setting_nfc);
        btn_setting_other = findViewById(R.id.btn_setting_other);



        btn_setting_ip_address.setOnClickListener(this);
        btn_setting_device.setOnClickListener(this);
        btn_setting_ip_print.setOnClickListener(this);
        btn_setting_nfc.setOnClickListener(this);
        btn_setting_other.setOnClickListener(this);




    }



    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SettingMainActivity.this, MainSplashActivity.class);
        startActivity(intent);
        finish();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(SettingMainActivity.this, MainSplashActivity.class);
                startActivity(intent);
                finish();

                break;




            default:

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        if(view == btn_setting_ip_address){

            Intent intent = new Intent(SettingMainActivity.this, SubSettingIpaddressMainActivity.class);
            startActivity(intent);
            finish();

        }else   if(view == btn_setting_device){

            Intent intent = new Intent(SettingMainActivity.this, SubSettingDeviceMainActivity.class);
            startActivity(intent);
            finish();

        }else if(view == btn_setting_ip_print){

            Intent intent = new Intent(SettingMainActivity.this, SubSettingPrinterMainActivity.class);
            startActivity(intent);
            finish();


        }else if(view == btn_setting_nfc){

            Intent intent = new Intent(SettingMainActivity.this, SubSettingNFCMainActivity.class);
            startActivity(intent);
            finish();


        }else if(view == btn_setting_other){

            Intent intent = new Intent(SettingMainActivity.this, SubSettingOtherMainActivity.class);
            startActivity(intent);
            finish();


        }



    }
}