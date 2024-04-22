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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;

public class SubSettingNFCMainActivity extends ImportantMethod implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    String TAG = "SubSettingNFCMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_NFC_SECTOR_ID = "pref_nfc_sector_id";
    private static final String PREF_NFC_BLOCK_IN_SECTOR_ID = "pref_nfc_block_in_sector_id";


    private final int DefaultInt = 0;
    private int name_nfc_sector_id = 0;
    private int name_nfc_block_in_sector_id = 0;


    ////////////////////////////////////////////////////////////////


    CardView card_ok;
    Spinner sp_numofsector;
    Spinner sp_numofblockinsector;

    String[] sector_id;
    String[] block_in_sector_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting_nfcmain);


        loadPreferences();
        inintInstances();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ตั้งค่าการอ่าน NFC");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }




    private void inintInstances() {


        card_ok = findViewById(R.id.card_ok);
        sp_numofsector = findViewById(R.id.sp_numofsector);
        sp_numofblockinsector = findViewById(R.id.sp_numofblockinsector);


        card_ok.setOnClickListener(this);


        sp_numofsector.setOnItemSelectedListener(this);
        sp_numofblockinsector.setOnItemSelectedListener(this);


        sector_id = getResources().getStringArray(R.array.sp_id_sector);
        ArrayAdapter<String> adapterIdSector = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, sector_id);
        sp_numofsector.setAdapter(adapterIdSector);


        block_in_sector_id = getResources().getStringArray(R.array.sp_id_block_in_sector);
        ArrayAdapter<String> adapterIdBlockInSector = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, block_in_sector_id);
        sp_numofblockinsector.setAdapter(adapterIdBlockInSector);



        sp_numofsector.setSelection(name_nfc_sector_id);
        sp_numofblockinsector.setSelection(name_nfc_block_in_sector_id);



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(SubSettingNFCMainActivity.this, SettingMainActivity.class);
                startActivity(intent);
                finish();

                break;

            default:

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SubSettingNFCMainActivity.this, SettingMainActivity.class);
        startActivity(intent);
        finish();


    }



    @Override
    public void onClick(View v) {
        if (v == card_ok) {

            save_references();
            showToastSuccess("บันทึกสำเร็จ",getApplicationContext());


        }
    }

    private void save_references() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        editor.putInt(PREF_NFC_SECTOR_ID, name_nfc_sector_id);
        editor.putInt(PREF_NFC_BLOCK_IN_SECTOR_ID,name_nfc_block_in_sector_id);

        editor.commit();


    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        name_nfc_sector_id = settings.getInt(PREF_NFC_SECTOR_ID, DefaultInt);
        name_nfc_block_in_sector_id = settings.getInt(PREF_NFC_BLOCK_IN_SECTOR_ID, DefaultInt);


    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        if (adapterView == sp_numofsector) {
            showToastLog(TAG, sector_id[i] + "");
            name_nfc_sector_id = Integer.valueOf(sector_id[i]);

        } else if (adapterView == sp_numofblockinsector) {
            showToastLog(TAG, block_in_sector_id[i] + "");
            name_nfc_block_in_sector_id = Integer.valueOf(block_in_sector_id[i]);
        }

    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}
