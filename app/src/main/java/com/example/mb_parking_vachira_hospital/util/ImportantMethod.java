package com.example.mb_parking_vachira_hospital.util;



import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mb_parking_vachira_hospital.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ImportantMethod   extends AppCompatActivity {

    public void showToastLog(String text1, String text2) {
        Log.d(text1, text2);
    }


    public void showToastWarning(String msg, Context context) {


        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.custom_toast_container));


        TextView text = (TextView) layout.findViewById(R.id.text);
        layout.setBackgroundColor(Color.parseColor("#ffc000"));
        text.setText(msg);
        text.setTextSize(18);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();



    }



    public void showToastSuccess(String msg, Context context) {



        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.custom_toast_container));


        TextView text = (TextView) layout.findViewById(R.id.text);
        layout.setBackgroundColor(Color.parseColor("#28a953"));
        text.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();





    }



    public void showToastDanger(String msg, Context context) {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.custom_toast_container));


        TextView text = (TextView) layout.findViewById(R.id.text);
        layout.setBackgroundColor(Color.parseColor("#e60000"));
        text.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();



    }




    public static String getCurrentTimeStamp() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    public static String getCurrentDate() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

}

