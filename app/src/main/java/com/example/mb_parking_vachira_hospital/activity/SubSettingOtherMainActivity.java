package com.example.mb_parking_vachira_hospital.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;

public class SubSettingOtherMainActivity extends ImportantMethod implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    String TAG = "SubSettingOtherMainActivity";


    private static final String PREFS_NAME = "preferences";


    // PARKING
    private static final String PREF_STATUS_RADIO_CARIN_CAPTURE_IMG = "pref_status_radio_carin_capture_img ";
    private static final String PREF_STATUS_RADIO_CARIN_NOT_CAPTURE_IMG = "pref_status_radio_carin_not_capture_img ";
    private static final String PREF_STATUS_RADIO_CAROUT_CAPTURE_IMG = "pref_status_radio_carout_capture_img ";
    private static final String PREF_STATUS_RADIO_CAROUT_NOT_CAPTURE_IMG = "pref_status_radio_carout_not_capture_img ";


    // ESTAMP
    private static final String PREF_STATUS_RADIO_TYPE_ESTAMP_DEFAULT = "pref_status_radio_type_estamp_default";
    private static final String PREF_STATUS_RADIO_TYPE_ESTAMP_INSERT_CODE = "pref_status_radio_type_estamp_insert_code";



    private final int DefaultInt = 0;
    private final boolean DefaultBoolean = false;
    private final String DefaultString = "null";

    private boolean status_radio_carin_capture_img;
    private boolean status_radio_carin_not_capture_img;
    private boolean status_radio_carout_capture_img;
    private boolean status_radio_carout_not_capture_img;

    private boolean status_radio_type_estamp_default;
    private boolean status_radio_type_estamp_insert_code;




    CardView card_ok;



    RadioButton radio_carin_capture_img;
    RadioButton radio_carin_not_capture_img;
    RadioButton radio_carout_capture_img;
    RadioButton radio_carout_not_capture_img;
    RadioButton radio_type_estamp_default;
    RadioButton radio_type_estamp_insert_code;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting_other_main);


        loadPreferences();
        inintInstances();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ตั้งค่าอื่นๆ");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


    }

    private void inintInstances() {



        card_ok = findViewById(R.id.card_ok);


        radio_carin_capture_img = findViewById(R.id.radio_carin_capture_img);
        radio_carin_not_capture_img = findViewById(R.id.radio_carin_not_capture_img);



        radio_carout_capture_img = findViewById(R.id.radio_carout_capture_img);
        radio_carout_not_capture_img = findViewById(R.id.radio_carout_not_capture_img);

        radio_type_estamp_default = findViewById(R.id.radio_type_estamp_default);
        radio_type_estamp_insert_code = findViewById(R.id.radio_type_estamp_insert_code);





        card_ok.setOnClickListener(this);


        radio_carin_capture_img.setChecked(status_radio_carin_capture_img);
        radio_carin_not_capture_img.setChecked(status_radio_carin_not_capture_img);

        radio_carout_capture_img.setChecked(status_radio_carout_capture_img);
        radio_carout_not_capture_img.setChecked(status_radio_carout_not_capture_img);

        radio_type_estamp_default.setChecked(status_radio_type_estamp_default);
        radio_type_estamp_insert_code.setChecked(status_radio_type_estamp_insert_code);





        radio_carin_capture_img.setOnCheckedChangeListener(this);
        radio_carin_not_capture_img.setOnCheckedChangeListener(this);

        radio_carout_capture_img.setOnCheckedChangeListener(this);
        radio_carout_not_capture_img.setOnCheckedChangeListener(this);

        radio_type_estamp_default.setOnCheckedChangeListener(this);
        radio_type_estamp_insert_code.setOnCheckedChangeListener(this);



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(SubSettingOtherMainActivity.this, SettingMainActivity.class);
                startActivity(intent);
                finish();
                break;

            default:

        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SubSettingOtherMainActivity.this, SettingMainActivity.class);
        startActivity(intent);
        finish();


    }



    @Override
    public void onClick(View view) {

        if (view == card_ok) {


                save_references();
                showToastSuccess("บันทึกสำเร็จ",getApplicationContext());


        }

    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (compoundButton == radio_carin_capture_img) {
            status_radio_carin_capture_img = b;

        } else if (compoundButton == radio_carin_not_capture_img) {
            status_radio_carin_not_capture_img = b;

        } else if (compoundButton == radio_carout_capture_img) {
            status_radio_carout_capture_img = b;

        } else if (compoundButton == radio_carout_not_capture_img) {
            status_radio_carout_not_capture_img = b;

        }
        else if (compoundButton == radio_type_estamp_default) {
            status_radio_type_estamp_default = b;

        } else if (compoundButton == radio_type_estamp_insert_code) {
            status_radio_type_estamp_insert_code = b;

        }






    }





    private void save_references() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit


        editor.putBoolean(PREF_STATUS_RADIO_CARIN_CAPTURE_IMG , status_radio_carin_capture_img);
        editor.putBoolean(PREF_STATUS_RADIO_CARIN_NOT_CAPTURE_IMG , status_radio_carin_not_capture_img);
        editor.putBoolean(PREF_STATUS_RADIO_CAROUT_CAPTURE_IMG, status_radio_carout_capture_img);
        editor.putBoolean(PREF_STATUS_RADIO_CAROUT_NOT_CAPTURE_IMG , status_radio_carout_not_capture_img);
        editor.putBoolean(PREF_STATUS_RADIO_TYPE_ESTAMP_DEFAULT, status_radio_type_estamp_default);
        editor.putBoolean(PREF_STATUS_RADIO_TYPE_ESTAMP_INSERT_CODE , status_radio_type_estamp_insert_code);

        editor.commit();


    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        // Get value

        status_radio_carin_capture_img = settings.getBoolean(PREF_STATUS_RADIO_CARIN_CAPTURE_IMG , DefaultBoolean);
        status_radio_carin_not_capture_img = settings.getBoolean(PREF_STATUS_RADIO_CARIN_NOT_CAPTURE_IMG , DefaultBoolean);

        status_radio_carout_capture_img = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_CAPTURE_IMG , DefaultBoolean);
        status_radio_carout_not_capture_img = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_NOT_CAPTURE_IMG , DefaultBoolean);

        status_radio_type_estamp_default = settings.getBoolean(PREF_STATUS_RADIO_TYPE_ESTAMP_DEFAULT , DefaultBoolean);
        status_radio_type_estamp_insert_code = settings.getBoolean(PREF_STATUS_RADIO_TYPE_ESTAMP_INSERT_CODE , DefaultBoolean);


    }


}