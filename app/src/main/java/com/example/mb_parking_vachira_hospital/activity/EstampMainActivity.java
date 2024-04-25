package com.example.mb_parking_vachira_hospital.activity;


import android.Manifest;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EstampMainActivity extends ImportantMethod implements View.OnClickListener {

    String TAG = "EstampMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";
    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";
    private static final String PREF_USER_ID = "pref_user_id";
    private static final String PREF_USER_NAME = "pref_user_name";
    private static final String PREF_USER_ADDRESS = "pref_user_address";
    private static final String PREF_USER_NO_RECORD = "pref_user_no_record";
    private static final String PREF_GUARDHOUSE_IN = "pref_guardhouse_in";
    private static final String PREF_POSID = "pref_posid";
    private static final String PREF_TAXID = "pref_taxid";


    // ESTAMP
    private static final String PREF_STATUS_RADIO_TYPE_ESTAMP_DEFAULT = "pref_status_radio_type_estamp_default";
    private static final String PREF_STATUS_RADIO_TYPE_ESTAMP_INSERT_CODE = "pref_status_radio_type_estamp_insert_code";



    private final boolean DefaultBoolean = false;
    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String ip_address;
    private String port;
    private String user_id;
    private String user_address;
    private String user_no_record;
    private String name_guardhouse_in;
    private String user_name;
    private String name_posid;
    private String name_taxid;
    private String name_mac_address_print = "0";
    private boolean status_radio_type_estamp_default;
    private boolean status_radio_type_estamp_insert_code;




    //////////////////////////////////////////////


    private DrawerLayout drawer;
    CardView card_ok;
    EditText edit_insert_promotion;
    EditText edit_id_card;
    Spinner sp_typecar;
    LinearLayout title_insert_promotion;
    LinearLayout title_select_promotion;
    private ProgressDialog progressDoalog;


    //////////////////////////  NFC  //////////////////////////
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private String tag_id_card = null;
    //////////////////////////  NFC  //////////////////////////




//    //////////////////////////  SPINNER  //////////////////////////
//    private ArrayList<String> mVisitorcartype = new ArrayList<String>();
//    private ArrayList<String> mMembercartype = new ArrayList<String>();
//    private List<Sub_data_action_mobile_get_cartype> cartypeArrayList = new ArrayList<>();
//    //////////////////////////  SPINNER  //////////////////////////
//




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estamp_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ประทับตรา");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        loadPreferences();
        inintInstances();

        //////////////////////////  NFC  //////////////////////////
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (!mAdapter.isEnabled()) {
            showToastWarning("กรุณาเปิด NFC", this);
        }


        if (mAdapter == null) {
            //nfc not support your device.
            showToastWarning("nfc not support your device", this);
            return;
        }
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),  PendingIntent.FLAG_MUTABLE);

        //////////////////////////  NFC //////////////////////////





    }

    private void inintInstances() {

        edit_insert_promotion = findViewById(R.id.edit_license_plate);
        card_ok = findViewById(R.id.card_ok);
        edit_id_card = findViewById(R.id.edit_id_card);
        sp_typecar = findViewById(R.id.sp_typecar);
        title_insert_promotion = findViewById(R.id.title_insert_promotion);
        title_select_promotion = findViewById(R.id.title_select_promotion);



        edit_id_card.setText(tag_id_card);

        if (status_radio_type_estamp_default == true) {

            title_insert_promotion.setVisibility(View.GONE);

        }else{

            title_select_promotion.setVisibility(View.GONE);


        }

        card_ok.setOnClickListener(this);



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(EstampMainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                break;


            case R.id.action_clear:

                edit_insert_promotion.setText("");
                edit_id_card.setText("");
                tag_id_card = null;
                sp_typecar.setAdapter(null);

                break;


            default:

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EstampMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }




    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // Get value

        ip_address = settings.getString(PREF_IP_ADDRESS, DefaultString);
        port = settings.getString(PREF_PORT, DefaultString);
        user_id = settings.getString(PREF_USER_ID, DefaultString);
        user_address = settings.getString(PREF_USER_ADDRESS, DefaultString);
        user_no_record = settings.getString(PREF_USER_NO_RECORD, DefaultString);
        user_name = settings.getString(PREF_USER_NAME, DefaultString);
        name_guardhouse_in = settings.getString(PREF_GUARDHOUSE_IN, DefaultString);
        name_taxid = settings.getString(PREF_TAXID, DefaultString);
        name_posid = settings.getString(PREF_POSID, DefaultString);

        name_mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);
        status_radio_type_estamp_default = settings.getBoolean(PREF_STATUS_RADIO_TYPE_ESTAMP_DEFAULT, DefaultBoolean);
        status_radio_type_estamp_insert_code = settings.getBoolean(PREF_STATUS_RADIO_TYPE_ESTAMP_INSERT_CODE, DefaultBoolean);







    }


    @Override
    public void onClick(View view) {

    }
}