package com.example.mb_parking_vachira_hospital.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;

import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;

public class MainSplashActivity  extends ImportantMethod {

    Handler handler;
    Runnable runnable;
    long delay_time;
    long time = 1000L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);
        handler = new Handler();

        runnable = new Runnable() {
            public void run() {
                if (ActivityCompat.checkSelfPermission(MainSplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainSplashActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                    return;
                } else {

                    Intent intent = new Intent(MainSplashActivity.this, LoginMainActivity.class);
                    startActivity(intent);
                    finish();





                }
            }
        };
    }

    public void onResume() {
        super.onResume();
        //   loadPreferences();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }



}