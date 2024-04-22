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
import android.widget.EditText;


import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;

public class SubSettingDeviceMainActivity extends ImportantMethod implements View.OnClickListener {

    String TAG = "SubSettingDeviceMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_GUARDHOUSE = "pref_guardhouse";
    private static final String PREF_GUARDHOUSE_IN = "pref_guardhouse_in";
    private static final String PREF_GUARDHOUSE_OUT = "pref_guardhouse_out";
    private static final String PREF_GATE = "pref_gate";
    private static final String PREF_MACNINEID = "pref_macnineid";
    private static final String PREF_MACHINENAME = "pref_machinename";
    private static final String PREF_POSID = "pref_posid";
    private static final String PREF_TAXID = "pref_taxid";


    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String name_guardhouse;
    private String name_guardhouse_in;
    private String name_guardhouse_out;
    private String name_macnineid;
    private String name_gate;
    private String name_machinename;
    private String name_posid;
    private String name_taxid;


    EditText edit_guardhouse;
    EditText edit_guardhouse_in;
    EditText edit_guardhouse_out;
    EditText edit_gate;
    EditText edit_macnineid;
    EditText edit_machinename;
    EditText edit_posid;
    EditText edit_taxid;
    CardView card_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting_device_main);


        loadPreferences();
        inintInstances();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ตั้งค่าข้อมูล Parking");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }

    private void inintInstances() {


        edit_guardhouse_in = findViewById(R.id.edit_guardhouse_in);
        edit_guardhouse_out = findViewById(R.id.edit_guardhouse_out);
        edit_gate = findViewById(R.id.edit_gate);
        edit_macnineid = findViewById(R.id.edit_macnineid);
        edit_machinename = findViewById(R.id.edit_machinename);
        edit_posid = findViewById(R.id.edit_posid);
        edit_taxid = findViewById(R.id.edit_taxid);
        edit_guardhouse  = findViewById(R.id.edit_guardhouse);
        card_ok = findViewById(R.id.card_ok);

        edit_guardhouse.setText(name_guardhouse + "");
        edit_guardhouse.setSelection(name_guardhouse.length());

        edit_guardhouse_in.setText(name_guardhouse_in + "");
        edit_guardhouse_in.setSelection(name_guardhouse_in.length());

        edit_guardhouse_out.setText(name_guardhouse_out + "");
        edit_guardhouse_out.setSelection(name_guardhouse_out.length());

        edit_gate.setText(name_gate + "");
        edit_gate.setSelection(name_gate.length());

        edit_macnineid.setText(name_macnineid + "");
        edit_macnineid.setSelection(name_macnineid.length());

        edit_machinename.setText(name_machinename + "");
        edit_machinename.setSelection(name_machinename.length());


        edit_posid.setText(name_posid + "");
        edit_posid.setSelection(name_posid.length());

        edit_taxid.setText(name_taxid + "");
        edit_taxid.setSelection(name_taxid.length());


        card_ok.setOnClickListener(this);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(SubSettingDeviceMainActivity.this, SettingMainActivity.class);
                startActivity(intent);
                finish();
                break;

            default:

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SubSettingDeviceMainActivity.this, SettingMainActivity.class);
        startActivity(intent);
        finish();


    }



    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        name_guardhouse = settings.getString(PREF_GUARDHOUSE, DefaultString);
        name_guardhouse_in = settings.getString(PREF_GUARDHOUSE_IN, DefaultString);
        name_guardhouse_out = settings.getString(PREF_GUARDHOUSE_OUT, DefaultString);
        name_macnineid = settings.getString(PREF_MACNINEID, DefaultString);
        name_gate = settings.getString(PREF_GATE, DefaultString);
        name_machinename = settings.getString(PREF_MACHINENAME, DefaultString);
        name_posid = settings.getString(PREF_POSID, DefaultString);
        name_taxid = settings.getString(PREF_TAXID, DefaultString);


    }


    @Override
    public void onClick(View v) {
        if (v == card_ok) {


            save_references();
            showToastSuccess("บันทึกสำเร็จ", getApplicationContext());
        }
    }

    private void save_references() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        editor.putString(PREF_GUARDHOUSE, edit_guardhouse.getText() + "");
        editor.putString(PREF_GUARDHOUSE_IN, edit_guardhouse_in.getText() + "");
        editor.putString(PREF_GUARDHOUSE_OUT, edit_guardhouse_out.getText() + "");
        editor.putString(PREF_MACNINEID, edit_macnineid.getText() + "");
        editor.putString(PREF_GATE, edit_gate.getText() + "");
        editor.putString(PREF_MACHINENAME, edit_machinename.getText() + "");
        editor.putString(PREF_POSID, edit_posid.getText() + "");
        editor.putString(PREF_TAXID, edit_taxid.getText() + "");


        editor.commit();


    }




}