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
import android.widget.Spinner;
import android.widget.Toast;


import android.nfc.Tag;
import android.view.View;
import android.widget.AdapterView;

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


import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.dao.DataHistoryCarInDao;
import com.example.mb_parking_vachira_hospital.manager.HttpManager;
import com.example.mb_parking_vachira_hospital.model.History_data_carin_dao;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_checkcardin;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_get_membercartype;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_get_visitorcartype;
import com.example.mb_parking_vachira_hospital.model.Result_action_save_in;
import com.example.mb_parking_vachira_hospital.model.Sub_data_action_mobile_get_cartype;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;

public class InCarMainActivity  extends ImportantMethod implements View.OnClickListener, AdapterView.OnItemSelectedListener {



    String TAG = "InCarMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";
    private static final String PREF_STATUS_RADIO_CARIN_NOT_PRINT = "pref_status_radio_carin_not_print";
    private static final String PREF_STATUS_RADIO_CARIN_PRINT_ALL = "pref_status_radio_carin_print_all";
    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";
    private static final String PREF_USER_ID = "pref_user_id";
    private static final String PREF_USER_NAME = "pref_user_name";
    private static final String PREF_USER_ADDRESS = "pref_user_address";
    private static final String PREF_USER_NO_RECORD = "pref_user_no_record";
    private static final String PREF_GUARDHOUSE_IN = "pref_guardhouse_in";

    private static final String PREF_POSID = "pref_posid";
    private static final String PREF_TAXID = "pref_taxid";

    private final boolean DefaultBoolean = false;
    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String ip_address;
    private String port;
    private String user_id;
    private String user_address;
    private String user_no_record;
    private String name_guardhouse_in;
    private String cartype_id = "";
    private String cartype_name = "";
    private String user_name;
    private String name_posid;
    private String name_taxid;
    private String name_mac_address_print = "0";
    private boolean status_radio_carin_not_print;
    private boolean status_radio_carin_print_all;




    //////////////////////////////////////////////


    private DrawerLayout drawer;
    CardView card_ok;
    EditText edit_license_plate;
    EditText edit_id_card;
    Button btn_camera;
    ImageView imageView;
    Spinner sp_typecar;
    private ProgressDialog progressDoalog;


    //////////////////////////  NFC  //////////////////////////
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private String tag_id_card = null;
    //////////////////////////  NFC  //////////////////////////


    //////////////////////////  IMAGE  //////////////////////////
    public static final int REQUEST_IMAGE_CAPTURE = 2;
    String path_image = "";
    //////////////////////////  IMAGE  //////////////////////////


    //////////////////////////  SPINNER  //////////////////////////
    private ArrayList<String> mVisitorcartype = new ArrayList<String>();
    private ArrayList<String> mMembercartype = new ArrayList<String>();
    private List<Sub_data_action_mobile_get_cartype> cartypeArrayList = new ArrayList<>();
    //////////////////////////  SPINNER  //////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_car_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ทำรายการขาเข้า");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

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
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_IMMUTABLE);

        //////////////////////////  NFC //////////////////////////



    }

    private void inintInstances() {

        edit_license_plate = findViewById(R.id.edit_license_plate);
        card_ok = findViewById(R.id.card_ok);
        edit_id_card = findViewById(R.id.edit_id_card);
        btn_camera = findViewById(R.id.btn_camera);
        imageView = findViewById(R.id.imageView);
        sp_typecar = findViewById(R.id.sp_typecar);


        edit_id_card.setText(tag_id_card);
        card_ok.setOnClickListener(this);
        btn_camera.setOnClickListener(this);

        sp_typecar.setOnItemSelectedListener(this);


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


        try {

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String tagInfo = "";
            byte[] tagId = tag.getId();
            tag_id_card = String.valueOf(Long.parseLong(bytesToHexString(tagId), 16));


            showToastLog(TAG, "formattag_HEX: " + bytesToHexString(tagId));
            showToastLog(TAG, "formattag_DEC: " + tag_id_card);


            HashMap<String, String> SendData = new HashMap<>();
            SendData.put("id", tag_id_card);


            Call<Result_action_mobile_checkcardin> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_checkcardin(SendData);
            call.enqueue(new Callback<Result_action_mobile_checkcardin>() {
                @Override
                public void onResponse(Call<Result_action_mobile_checkcardin> call, Response<Result_action_mobile_checkcardin> response) {
                    if (response.body() != null) {


                        if (response.body().getBoolStatus() == true) {

                            edit_id_card.setText(tag_id_card);

                            if (response.body().getIsMember() == false) {

                                get_visitor_cartype();

                            } else {

                                get_cartype_member();

                            }


                        } else {


                            showToastWarning(response.body().getStrMessage() + "", InCarMainActivity.this);
                            progressDoalog.dismiss();

                        }


                    } else {

                        showToastWarning("ทำรายการผิดพลาด action_mobile_checkcardin", InCarMainActivity.this);
                        progressDoalog.dismiss();

                    }


                }

                @Override
                public void onFailure(Call<Result_action_mobile_checkcardin> call, Throwable t) {
                    showToastWarning("ผิดพลาด" + t.toString(), InCarMainActivity.this);
                    progressDoalog.dismiss();

                }
            });


        } catch (Exception e) {

            showToastSuccess("RFID ไม่ตรงกัน", InCarMainActivity.this);
            tag_id_card = null;
            edit_id_card.setText("เลข Card");
            showToastLog(TAG, "Error: " + e);
            progressDoalog.dismiss();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_clear, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(InCarMainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                break;


            case R.id.action_clear:

                edit_license_plate.setText("");
                edit_id_card.setText("");
                tag_id_card = null;
                path_image = "";
                imageView.setImageResource(R.drawable.ic_image_48);
                sp_typecar.setAdapter(null);

                break;


            default:

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InCarMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        imageView.setImageBitmap(thumbnail);
        imageView.setDrawingCacheEnabled(true);
        Bitmap b = imageView.getDrawingCache();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp + ".jpg";
        path_image = imageFileName;
        MediaStore.Images.Media.insertImage(getContentResolver(), b, imageFileName, "Task5");


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


    private boolean checkdata() {

        boolean status = true;

        if (tag_id_card == null) {
            status = false;
            showToastWarning("กรุณาอ่าน Card ", getApplicationContext());

        } else if (edit_license_plate.getText().toString().replaceAll(" ", "").equals("")) {
            status = false;
            showToastWarning("กรุณาใส่ทะเบียนรถ", getApplicationContext());
        } else if (path_image.equals("")) {
            status = false;
            showToastWarning("กรุณาถ่ายรูป", getApplicationContext());
        }
        return status;

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
        status_radio_carin_print_all = settings.getBoolean(PREF_STATUS_RADIO_CARIN_PRINT_ALL, DefaultBoolean);
        status_radio_carin_not_print = settings.getBoolean(PREF_STATUS_RADIO_CARIN_NOT_PRINT, DefaultBoolean);


    }


    private void get_visitor_cartype() {

        HashMap<String, String> SendData = new HashMap<>();


        Call<Result_action_mobile_get_visitorcartype> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_get_visitorcartype(SendData);
        call.enqueue(new Callback<Result_action_mobile_get_visitorcartype>() {
            @Override
            public void onResponse(Call<Result_action_mobile_get_visitorcartype> call, Response<Result_action_mobile_get_visitorcartype> response) {
                if (response.body() != null) {
                    progressDoalog.dismiss();
                    if (response.body().getBoolStatus() == true) {

                        cartypeArrayList = response.body().getData();
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            mVisitorcartype.add(response.body().getData().get(i).getCartypeName());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, mVisitorcartype);
                            sp_typecar.setAdapter(adapter);
                        }


                    } else {
                        showToastWarning("ทำรายการผิดพลาด Result_action_mobile_get_visitorcartype", InCarMainActivity.this);
                    }


                } else {

                    showToastWarning("ทำรายการผิดพลาด Result_action_mobile_get_visitorcartype", InCarMainActivity.this);
                    progressDoalog.dismiss();

                }


            }

            @Override
            public void onFailure(Call<Result_action_mobile_get_visitorcartype> call, Throwable t) {
                showToastWarning("ผิดพลาด" + t.toString(), InCarMainActivity.this);
                progressDoalog.dismiss();

            }
        });


    }


    private void get_cartype_member() {

        HashMap<String, String> SendData = new HashMap<>();


        Call<Result_action_mobile_get_membercartype> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_get_membercartype(SendData);
        call.enqueue(new Callback<Result_action_mobile_get_membercartype>() {
            @Override
            public void onResponse(Call<Result_action_mobile_get_membercartype> call, Response<Result_action_mobile_get_membercartype> response) {
                if (response.body() != null) {
                    progressDoalog.dismiss();
                    if (response.body().getBoolStatus() == true) {


                        cartypeArrayList = response.body().getData();
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            mMembercartype.add(response.body().getData().get(i).getCartypeName());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, mMembercartype);
                            sp_typecar.setAdapter(adapter);
                        }


                    } else {
                        showToastWarning("ทำรายการผิดพลาด Result_action_mobile_get_membercartype", InCarMainActivity.this);
                    }


                } else {

                    showToastWarning("ทำรายการผิดพลาด Result_action_mobile_get_membercartype", InCarMainActivity.this);
                    progressDoalog.dismiss();

                }


            }

            @Override
            public void onFailure(Call<Result_action_mobile_get_membercartype> call, Throwable t) {
                showToastWarning("ผิดพลาด" + t.toString(), InCarMainActivity.this);
                progressDoalog.dismiss();

            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        cartype_id = cartypeArrayList.get(i).getCartypeId() + "";
        cartype_name = cartypeArrayList.get(i).getCartypeName() + "";

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    @Override
    public void onClick(View v) {
        if (card_ok == v) {

            if (checkdata()) {

                File file1 = new File("/sdcard/Pictures/" + path_image);

                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
                MultipartBody.Part body1 = MultipartBody.Part.createFormData("file_img_license", file1.getName(), requestFile);
                MultipartBody.Part body2 = MultipartBody.Part.createFormData("file_img_driver", file1.getName(), requestFile);

                String license_plate = edit_license_plate.getText().toString() + "";
                progressDoalog.show();

                final String currentdate = getCurrentDate();
                final String timestamp = getCurrentTimeStamp();

//
                Call<Result_action_save_in> call = HttpManager.getInstance(ip_address, port).getService().action_save_in(tag_id_card, license_plate, cartype_id, user_id, user_no_record, name_guardhouse_in, user_address, body1, body2);
                call.enqueue(new Callback<Result_action_save_in>() {
                    @Override
                    public void onResponse(Call<Result_action_save_in> call, Response<Result_action_save_in> response) {
                        if (response.body() != null) {
                            progressDoalog.dismiss();
                            if (response.body().getBoolStatus() == true) {


                                String cardid = response.body().getData().getCardid()+"";
                                String license_plate = response.body().getData().getLicensePlate()+"";
                                String str_cartype = response.body().getData().getStrCartype()+"";
                                String str_cashiername = response.body().getData().getStrCashiername()+"";
                                String datetime_in = response.body().getData().getDatetimeIn()+"";


                                RecordHistoryCarInData(cardid,license_plate,cartype_id,user_id,user_no_record,user_address,name_posid,name_taxid,datetime_in,name_guardhouse_in,path_image,str_cartype,str_cashiername);

                                if(status_radio_carin_not_print == true){

                                    showToastLog(TAG,"status_radio_carin_not_print");


                                }else if(status_radio_carin_print_all == true){
                                    //TODO Print Car IN

                                    showToastLog(TAG,"status_radio_carin_print_all");


                                }

                                showToastSuccess("บันทึกสำเร็จ", getApplicationContext());
                                edit_license_plate.setText("");
                                edit_id_card.setText("");
                                tag_id_card = null;
                                path_image = "";
                                imageView.setImageResource(R.drawable.ic_image_48);
                                sp_typecar.setAdapter(null);



                            } else {
                                String text_message = response.body().getStrMessage()+"";
                                showToastSuccess(text_message + "", getApplicationContext());
                                edit_license_plate.setText("");
                                edit_id_card.setText("");
                                tag_id_card = null;
                                path_image = "";
                                imageView.setImageResource(R.drawable.ic_image_48);
                                sp_typecar.setAdapter(null);
                            }


                        } else {

                            showToastWarning("ทำรายการผิดพลาด Result_action_save_in", InCarMainActivity.this);
                            progressDoalog.dismiss();

                        }


                    }

                    @Override
                    public void onFailure(Call<Result_action_save_in> call, Throwable t) {

                        showToastWarning("InCarMainActivity ผิดพลาด" + t.toString(), getApplicationContext());
                        showToastLog(TAG, t.toString());
                        progressDoalog.dismiss();

                    }


                });


            }


        } else if (btn_camera == v) {

            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
            }else{

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    // display error state to the user
                }

            }


        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    // display error state to the user
                }
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void RecordHistoryCarInData(String tran_carin_card_id,
                                        String tran_carin_license_plate,
                                        String tran_carin_cartype_id,
                                        String tran_carin_user_id,
                                        String tran_carin_user_no_record,
                                        String tran_carin_address,
                                        String tran_carin_posid,
                                        String tran_carin_taxid,
                                        String tran_carin_timestamp_carin,
                                        String tran_carin_guardhouse_in,
                                        String tran_carin_path_img,
                                        String tran_carin_cartype_name,
                                        String tran_carin_user_name
    ) {


        History_data_carin_dao list = new History_data_carin_dao();
        list.setTran_carin_card_id(tran_carin_card_id);
        list.setTran_carin_license_plate(tran_carin_license_plate);
        list.setTran_carin_cartype_id(tran_carin_cartype_id);
        list.setTran_carin_user_id(tran_carin_user_id);
        list.setTran_carin_user_no_record(tran_carin_user_no_record);
        list.setTran_carin_address(tran_carin_address);
        list.setTran_carin_posid(tran_carin_posid);
        list.setTran_carin_taxid(tran_carin_taxid);
        list.setTran_carin_timestamp_carin(tran_carin_timestamp_carin);
        list.setTran_carin_guardhouse_in(tran_carin_guardhouse_in);
        list.setTran_carin_path_img(tran_carin_path_img);
        list.setTran_carin_cartype_name(tran_carin_cartype_name);
        list.setTran_carin_user_name(tran_carin_user_name);

        DataHistoryCarInDao dao = new DataHistoryCarInDao(getApplicationContext());
        dao.open();
        dao.add_tran_history_car_in(list);
        dao.close();


    }
}

