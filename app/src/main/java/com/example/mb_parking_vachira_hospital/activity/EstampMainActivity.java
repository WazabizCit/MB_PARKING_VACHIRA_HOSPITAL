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
import android.graphics.BitmapFactory;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.mb_parking_vachira_hospital.dao.DataHistoryCarInDao;
import com.example.mb_parking_vachira_hospital.dao.DataHistoryEstampDao;
import com.example.mb_parking_vachira_hospital.manager.HttpManager;
import com.example.mb_parking_vachira_hospital.model.History_data_carin_dao;
import com.example.mb_parking_vachira_hospital.model.History_data_estamp_dao;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_checkcardin;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_checkestamp;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_get_promotion;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_get_visitorcartype;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_save_promotion;
import com.example.mb_parking_vachira_hospital.model.Sub_data_action_mobile_getpromotion;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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


public class EstampMainActivity extends ImportantMethod implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    String TAG = "EstampMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";
    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";
    private static final String PREF_USER_ID = "pref_user_id";
    private static final String PREF_USER_NAME = "pref_user_name";
    private static final String PREF_USER_LEVEL = "pref_user_level";
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
    private String user_level;
    private String name_posid;
    private String name_taxid;
    private String promotion_id;
    private String promotion_name;
    private String name_mac_address_print = "0";
    private boolean status_radio_type_estamp_default;
    private boolean status_radio_type_estamp_insert_code;


    //////////////////////////////////////////////


    private DrawerLayout drawer;
    CardView card_ok;
    EditText edit_insert_promotion;
    EditText edit_id_card;
    EditText edit_detail_promotion_card;
    Spinner sp_promotion;
    LinearLayout title_insert_promotion;
    LinearLayout title_select_promotion;
    private ProgressDialog progressDoalog;


    //////////////////////////  NFC  //////////////////////////
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private String tag_id_card = null;
    //////////////////////////  NFC  //////////////////////////


    //////////////////////////  SPINNER  //////////////////////////
    private ArrayList<String> mPromotion = new ArrayList<String>();
    private List<Sub_data_action_mobile_getpromotion> promotionArrayList = new ArrayList<>();


    //////////////////////////  SPINNER  //////////////////////////


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
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE);

        //////////////////////////  NFC //////////////////////////


    }

    private void inintInstances() {


        edit_detail_promotion_card = findViewById(R.id.edit_detail_promotion_card);
        edit_insert_promotion = findViewById(R.id.edit_insert_promotion);
        card_ok = findViewById(R.id.card_ok);
        edit_id_card = findViewById(R.id.edit_id_card);
        sp_promotion = findViewById(R.id.sp_promotion);
        title_insert_promotion = findViewById(R.id.title_insert_promotion);
        title_select_promotion = findViewById(R.id.title_select_promotion);


        edit_id_card.setText(tag_id_card);
        sp_promotion.setOnItemSelectedListener(this);

        if (status_radio_type_estamp_default == true) {

            title_insert_promotion.setVisibility(View.GONE);

        } else {

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
                edit_detail_promotion_card.setText("");
                tag_id_card = null;
                sp_promotion.setAdapter(null);
                edit_insert_promotion.setText("");


                break;


            default:

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        loadPreferences();
        if (mAdapter != null) {
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
    }


    @Override
    public void onNewIntent(Intent intent) {
        getTagInfo(intent);
    }

    private void getTagInfo(Intent intent) {
        progressDoalog = new ProgressDialog(this);
        progressDoalog.setTitle("ตรวจสอบข้อมูล Card");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setMessage("กรุณารอสักครู่...");
        progressDoalog.setCancelable(true);
        progressDoalog.show();


        edit_id_card.setText("");
        tag_id_card = null;


        try {

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String tagInfo = "";
            byte[] tagId = tag.getId();
            tag_id_card = String.valueOf(Long.parseLong(bytesToHexString(tagId), 16));


            showToastLog(TAG, "formattag_HEX: " + bytesToHexString(tagId));
            showToastLog(TAG, "formattag_DEC: " + tag_id_card);


            HashMap<String, String> SendData = new HashMap<>();
            SendData.put("card_id", tag_id_card);


            Call<Result_action_mobile_checkestamp> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_checkestamp(SendData);
            call.enqueue(new Callback<Result_action_mobile_checkestamp>() {
                @Override
                public void onResponse(Call<Result_action_mobile_checkestamp> call, Response<Result_action_mobile_checkestamp> response) {
                    if (response.body() != null) {

                        progressDoalog.dismiss();
                        if (response.body().getBoolStatus() == true) {

                            edit_id_card.setText(tag_id_card);
                            String recordin_no = response.body().getData().getPromotionName() + "";
                            edit_detail_promotion_card.setText("ชื่อโปรโมชั่นปัจจุบัน : " + "\n" + recordin_no + "\n");
                            get_promotion_estamp_by_level_user(user_level);


                        } else {

                            showToastWarning(response.body().getMessage() + "", EstampMainActivity.this);
                            progressDoalog.dismiss();

                        }


                    } else {

                        showToastWarning("ทำรายการผิดพลาด action_mobile_checkestamp", EstampMainActivity.this);
                        progressDoalog.dismiss();

                    }


                }

                @Override
                public void onFailure(Call<Result_action_mobile_checkestamp> call, Throwable t) {

                    showToastWarning("ผิดพลาด" + t.toString(), EstampMainActivity.this);
                    progressDoalog.dismiss();

                }
            });


        } catch (Exception e) {

            showToastWarning("RFID ไม่ตรงกัน", EstampMainActivity.this);
            tag_id_card = null;
            edit_id_card.setText("เลข Card");
            edit_detail_promotion_card.setText("");
            sp_promotion.setAdapter(null);

            showToastLog(TAG, "Error: " + e);
            progressDoalog.dismiss();

        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        promotion_id = promotionArrayList.get(i).getPromotionId() + "";
        promotion_name = promotionArrayList.get(i).getPromotionName() + "";

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
        user_level = settings.getString(PREF_USER_LEVEL, DefaultString);

        name_mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);
        status_radio_type_estamp_default = settings.getBoolean(PREF_STATUS_RADIO_TYPE_ESTAMP_DEFAULT, DefaultBoolean);
        status_radio_type_estamp_insert_code = settings.getBoolean(PREF_STATUS_RADIO_TYPE_ESTAMP_INSERT_CODE, DefaultBoolean);


    }


    @Override
    public void onClick(View v) {
        if (card_ok == v) {

            if (checkdata()) {

                if (status_radio_type_estamp_default == true) {


                    HashMap<String, String> SendData = new HashMap<>();
                    SendData.put("card_id", tag_id_card);
                    SendData.put("promotion_id", promotion_id);
                    SendData.put("promotion_name", promotion_name);
                    SendData.put("user_id", user_id);
                    SendData.put("level",   user_level);
                    SendData.put("user_name", user_name);


                    Call<Result_action_mobile_save_promotion> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_save_promotion(SendData);
                    call.enqueue(new Callback<Result_action_mobile_save_promotion>() {
                        @Override
                        public void onResponse(Call<Result_action_mobile_save_promotion> call, Response<Result_action_mobile_save_promotion> response) {
                            if (response.body() != null) {

                                if (response.body().getBoolStatus() == true) {


                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            progressDoalog.dismiss();
                                            showToastSuccess("บันทึกสำเร็จ", getApplicationContext());
                                            RecordHistoryEstampData(tag_id_card, promotion_id, promotion_name, user_id, user_no_record, user_name);

                                            edit_detail_promotion_card.setText("");
                                            edit_id_card.setText("");
                                            tag_id_card = null;
                                            sp_promotion.setAdapter(null);
                                            edit_insert_promotion.setText("");


                                        }
                                    }, 500);


                                } else {

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            progressDoalog.dismiss();
                                            String text_message = response.body().getMessage() + "";
                                            showToastWarning(text_message + "", getApplicationContext());
                                            edit_detail_promotion_card.setText("");
                                            edit_id_card.setText("");
                                            tag_id_card = null;
                                            sp_promotion.setAdapter(null);
                                            edit_insert_promotion.setText("");


                                        }
                                    }, 500);


                                }


                            } else {


                                showToastWarning("ทำรายการผิดพลาด action_mobile_save_promotion", EstampMainActivity.this);
                                progressDoalog.dismiss();
                                edit_detail_promotion_card.setText("");
                                edit_id_card.setText("");
                                tag_id_card = null;
                                edit_insert_promotion.setText("");
                                sp_promotion.setAdapter(null);


                            }


                        }

                        @Override
                        public void onFailure(Call<Result_action_mobile_save_promotion> call, Throwable t) {
                            showToastWarning("ผิดพลาด" + t.toString(), EstampMainActivity.this);
                            progressDoalog.dismiss();
                            edit_detail_promotion_card.setText("");
                            edit_id_card.setText("");
                            tag_id_card = null;
                            edit_insert_promotion.setText("");
                            sp_promotion.setAdapter(null);


                        }
                    });


                }else if(status_radio_type_estamp_insert_code == true){

                    HashMap<String, String> SendData = new HashMap<>();
                    SendData.put("card_id", tag_id_card);
                    SendData.put("promotion_id", edit_insert_promotion.getText()+"");
                    SendData.put("promotion_name", "");
                    SendData.put("user_id", user_id);
                    SendData.put("level",   user_level);
                    SendData.put("user_name", user_name);


                    Call<Result_action_mobile_save_promotion> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_save_promotion(SendData);
                    call.enqueue(new Callback<Result_action_mobile_save_promotion>() {
                        @Override
                        public void onResponse(Call<Result_action_mobile_save_promotion> call, Response<Result_action_mobile_save_promotion> response) {
                            if (response.body() != null) {

                                if (response.body().getBoolStatus() == true) {


                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            progressDoalog.dismiss();
                                            showToastSuccess("บันทึกสำเร็จ", getApplicationContext());
                                            RecordHistoryEstampData(tag_id_card, promotion_id, promotion_name, user_id, user_no_record, user_name);

                                            edit_detail_promotion_card.setText("");
                                            edit_id_card.setText("");
                                            tag_id_card = null;
                                            edit_insert_promotion.setText("");
                                            sp_promotion.setAdapter(null);


                                        }
                                    }, 500);


                                } else {

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            progressDoalog.dismiss();
                                            String text_message = response.body().getMessage() + "";
                                            showToastWarning(text_message + "", getApplicationContext());
                                            edit_detail_promotion_card.setText("");
                                            edit_id_card.setText("");
                                            tag_id_card = null;
                                            edit_insert_promotion.setText("");
                                            sp_promotion.setAdapter(null);


                                        }
                                    }, 500);


                                }


                            } else {


                                showToastWarning("ทำรายการผิดพลาด action_mobile_save_promotion", EstampMainActivity.this);
                                progressDoalog.dismiss();
                                edit_detail_promotion_card.setText("");
                                edit_id_card.setText("");
                                tag_id_card = null;
                                edit_insert_promotion.setText("");
                                sp_promotion.setAdapter(null);


                            }


                        }

                        @Override
                        public void onFailure(Call<Result_action_mobile_save_promotion> call, Throwable t) {
                            showToastWarning("ผิดพลาด" + t.toString(), EstampMainActivity.this);
                            progressDoalog.dismiss();
                            edit_detail_promotion_card.setText("");
                            edit_id_card.setText("");
                            tag_id_card = null;
                            edit_insert_promotion.setText("");
                            sp_promotion.setAdapter(null);


                        }
                    });





                }

            }


        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_clear, menu);
        return true;
    }


    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
            System.out.println(buffer);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }


    private void get_promotion_estamp_by_level_user(String level) {

        HashMap<String, String> SendData = new HashMap<>();
        SendData.put("level", level);


        Call<Result_action_mobile_get_promotion> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_get_promotion(SendData);
        call.enqueue(new Callback<Result_action_mobile_get_promotion>() {
            @Override
            public void onResponse(Call<Result_action_mobile_get_promotion> call, Response<Result_action_mobile_get_promotion> response) {
                if (response.body() != null) {


                    if (response.body().getBoolStatus() == true) {


                        promotionArrayList = response.body().getData();
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            mPromotion.add(response.body().getData().get(i).getPromotionName());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, mPromotion);
                            sp_promotion.setAdapter(adapter);
                        }


                    } else {

                        showToastWarning("ทำรายการผิดพลาด action_mobile_get_promotion 1", EstampMainActivity.this);

                    }


                } else {

                    showToastWarning("ทำรายการผิดพลาด action_mobile_get_promotion 2", EstampMainActivity.this);
                    progressDoalog.dismiss();

                }


            }

            @Override
            public void onFailure(Call<Result_action_mobile_get_promotion> call, Throwable t) {
                showToastWarning("ผิดพลาด" + t.toString(), EstampMainActivity.this);
                progressDoalog.dismiss();

            }
        });

    }

    private boolean checkdata() {


        boolean status = true;


        if (tag_id_card == null) {
            status = false;
            showToastWarning("กรุณาอ่าน Card ", getApplicationContext());

        }

        return status;

    }


    private void RecordHistoryEstampData(
            String tran_estamp_card_id,
            String tran_estamp_promotion_id,
            String tran_estamp_promotion_name,
            String tran_estamp_user_id,
            String tran_estamp_user_no_record,
            String tran_estamp_user_name

    ) {


        History_data_estamp_dao list = new History_data_estamp_dao();
        list.setTran_estamp_card_id(tran_estamp_card_id);
        list.setTran_estamp_promotion_id(tran_estamp_promotion_id);
        list.setTran_estamp_promotion_name(tran_estamp_promotion_name);
        list.setTran_estamp_user_id(tran_estamp_user_id);
        list.setTran_estamp_user_no_record(tran_estamp_user_no_record);
        list.setTran_estamp_user_name(tran_estamp_user_name);


        DataHistoryEstampDao dao = new DataHistoryEstampDao(getApplicationContext());
        dao.open();
        dao.add_tran_history_estamp(list);
        dao.close();


    }


}