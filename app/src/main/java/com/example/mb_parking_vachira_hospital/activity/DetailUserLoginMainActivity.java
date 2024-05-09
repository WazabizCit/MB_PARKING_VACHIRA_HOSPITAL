package com.example.mb_parking_vachira_hospital.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;

public class DetailUserLoginMainActivity extends ImportantMethod {


    private static final String PREFS_NAME = "preferences";
    private final boolean DefaultBoolean = false;
    private final int DefaultInt = 0;
    private final String DefaultString = "null";


    //////////////// USER  ////////////////

    private static final String PREF_USER_ID = "pref_user_id";
    private static final String PREF_USER_NAME = "pref_user_name";
    private static final String PREF_USER_ADDRESS = "pref_user_address";
    private static final String PREF_USER_NO_RECORD = "pref_user_no_record";
    private static final String PREF_USER_LEVEL = "pref_user_level";


    private String user_id;
    private String user_address;
    private String user_no_record;
    private String user_name;
    private String user_level;

    //////////////// Parking info ////////////////

    private static final String PREF_COMPANYNAME = "pref_companyname";
    private static final String PREF_GUARDHOUSE = "pref_guardhouse";
    private static final String PREF_GUARDHOUSE_IN = "pref_guardhouse_in";
    private static final String PREF_GUARDHOUSE_OUT = "pref_guardhouse_out";
    private static final String PREF_GATE = "pref_gate";
    private static final String PREF_MACNINEID = "pref_macnineid";
    private static final String PREF_MACHINENAME = "pref_machinename";
    private static final String PREF_POSID = "pref_posid";
    private static final String PREF_TAXID = "pref_taxid";

    private String name_companyname;
    private String name_guardhouse;
    private String name_guardhouse_in;
    private String name_guardhouse_out;
    private String name_macnineid;
    private String name_gate;
    private String name_machinename;
    private String name_posid;
    private String name_taxid;



    //////////////// Device info ////////////////
    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";
    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";

    private String name_mac_address_print = "0";
    private String ip_address ;
    private String port;



    //////////////////////////////////////////////

    EditText edit_info_user_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_login_main);


        loadPreferences();
        inintInstances();
        info_user_login();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ข้อมูลเครื่อง");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


    }


    private void inintInstances() {


        edit_info_user_login = findViewById(R.id.edit_info_user_login);


    }

    private void info_user_login() {


///////////////// USER /////////////////
        String txt_user_id = user_id + "";
        String txt_user_name = user_name + "";
        String txt_user_no_record = user_no_record + "";
        String txt_level = user_level + "";
        String txt_address = user_address + "";

///////////////// Parking info /////////////////


        String txt_name_companyname = name_companyname;
        String txt_name_guardhouse = name_guardhouse;
        String txt_name_guardhouse_in = name_guardhouse_in;
        String txt_name_guardhouse_out = name_guardhouse_out;
        String txt_name_macnineid = name_macnineid;
        String txt_name_gate = name_gate;
        String txt_name_machinename = name_machinename;
        String txt_name_posid = name_posid;
        String txt_name_taxid = name_taxid;

        ///////////////// Device info /////////////////
        String txt_ip_address = ip_address;
        String txt_port= port;
        String txt_name_mac_address_print = name_mac_address_print;


        edit_info_user_login.setText(
                " - ข้อมูลพนักงาน login - \n" +
                        "user_id : " + txt_user_id + "\n" +
                        "user_name : " + txt_user_name + "\n" +
                        "user_no_record : " + txt_user_no_record + "\n" +
                        "level : " + txt_level + "\n" +
                        "address : " + txt_address + "\n" +
                        "\n" +
                        " - ข้อมูล Parking - \n" +
                        "companyname : " + txt_name_companyname + "\n" +
                        "guardhouse : " + txt_name_guardhouse + "\n" +
                        "guardhouse_in : " + txt_name_guardhouse_in + "\n" +
                        "guardhouse_out : " + txt_name_guardhouse_out + "\n" +
                        "macnineid : " + txt_name_macnineid + "\n" +
                        "gate : " + txt_name_gate + "\n" +
                        "machinename : " + txt_name_machinename + "\n" +
                        "posid : " + txt_name_posid + "\n" +
                        "taxid : " + txt_name_taxid + "\n"+
                        "\n" +
                        " - ข้อมูล เครื่อง - \n" +
                        "ip_address_server : " + txt_ip_address + "\n" +
                        "port : " + txt_port + "\n" +
                        "mac_address_print : " + txt_name_mac_address_print + "\n"

        );


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == android.R.id.home) {

            Intent intent = new Intent(DetailUserLoginMainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(DetailUserLoginMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        ////////////// User //////////////

        user_id = settings.getString(PREF_USER_ID, DefaultString);
        user_address = settings.getString(PREF_USER_ADDRESS, DefaultString);
        user_no_record = settings.getString(PREF_USER_NO_RECORD, DefaultString);
        user_name = settings.getString(PREF_USER_NAME, DefaultString);
        user_level = settings.getString(PREF_USER_LEVEL, DefaultString);

        ////////////// Parking info //////////////


        name_companyname = settings.getString(PREF_COMPANYNAME, DefaultString);
        name_guardhouse = settings.getString(PREF_GUARDHOUSE, DefaultString);
        name_guardhouse_in = settings.getString(PREF_GUARDHOUSE_IN, DefaultString);
        name_guardhouse_out = settings.getString(PREF_GUARDHOUSE_OUT, DefaultString);
        name_macnineid = settings.getString(PREF_MACNINEID, DefaultString);
        name_gate = settings.getString(PREF_GATE, DefaultString);
        name_machinename = settings.getString(PREF_MACHINENAME, DefaultString);
        name_posid = settings.getString(PREF_POSID, DefaultString);
        name_taxid = settings.getString(PREF_TAXID, DefaultString);



        ////////////// Device info //////////////


        name_mac_address_print  = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);
        ip_address  = settings.getString(PREF_IP_ADDRESS, DefaultString);
        port = settings.getString(PREF_PORT, DefaultString);


    }


}