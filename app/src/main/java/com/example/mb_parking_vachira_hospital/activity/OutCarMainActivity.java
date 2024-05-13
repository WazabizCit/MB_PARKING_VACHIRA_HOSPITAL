package com.example.mb_parking_vachira_hospital.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.nfc.tech.MifareClassic;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.dao.DataHistoryCarOutDao;
import com.example.mb_parking_vachira_hospital.manager.HttpManager;
import com.example.mb_parking_vachira_hospital.model.History_data_carout_dao;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_calculationparking;
import com.example.mb_parking_vachira_hospital.model.Result_action_save_out;
import com.example.mb_parking_vachira_hospital.model.Sub_data_action_mobile_get_cartype;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;
import com.example.mb_parking_vachira_hospital.util.MiniThermal80MMv4;

public class OutCarMainActivity  extends ImportantMethod implements View.OnClickListener {


    String TAG = "OutCarMainActivity";


    private static final String PREFS_NAME = "preferences";

    private static final String PREF_USER_ID = "pref_user_id";
    private static final String PREF_USER_ADDRESS = "pref_user_address";
    private static final String PREF_USER_NO_RECORD = "pref_user_no_record";
    private static final String PREF_USER_NAME = "pref_user_name";
    private static final String PREF_COMPANYNAME = "pref_companyname";

    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";
    private static final String PREF_STATUS_RADIO_CAROUT_NOT_PRINT = "pref_status_radio_carout_not_print";
    private static final String PREF_STATUS_RADIO_CAROUT_PRINT_ALL = "pref_status_radio_carout_print_all";
    private static final String PREF_STATUS_RADIO_CAROUT_PRINT_PRICE_ONLY = "pref_status_radio_carout_print_price_only";

    private static final String PREF_NFC_SECTOR_ID = "pref_nfc_sector_id";
    private static final String PREF_NFC_BLOCK_IN_SECTOR_ID = "pref_nfc_block_in_sector_id";


    private static final String PREF_STATUS_RADIO_CAROUT_CAPTURE_IMG = "pref_status_radio_carout_capture_img ";
    private static final String PREF_STATUS_RADIO_CAROUT_NOT_CAPTURE_IMG = "pref_status_radio_carout_not_capture_img ";


    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";

    private static final String PREF_GUARDHOUSE_OUT = "pref_guardhouse_out";
    private static final String PREF_MACNINEID = "pref_macnineid";
    private static final String PREF_MACHINENAME = "pref_machinename";
    private static final String PREF_POSID = "pref_posid";
    private static final String PREF_TAXID = "pref_taxid";

    ////////////////////////////   PREFS   ////////////////////////////

    private final boolean DefaultBoolean = false;
    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String ip_address;
    private String port;

    private String user_id;
    private String user_address;
    private String user_no_record;
    private String user_name;

    private String name_guardhouse_out;
    private String cartype_id = "";
    private String name_macnineid;
    private String name_machinename;
    private String name_posid;
    private String name_taxid;
    private String name_companyname;

    private int name_nfc_sector_id = 0;
    private int name_nfc_block_in_sector_id = 0;


    private boolean status_radio_carout_not_print;
    private boolean status_radio_carout_print_all;
    private boolean status_radio_carout_print_price_only;


    private boolean status_radio_carout_capture_img;
    private boolean status_radio_carout_not_capture_img;


    private String name_mac_address_print = "0";


    //////////////////////////////////////////////


    ////////////////////Params Action Save Out////////////////////////////
    private String recordin_no = "0";
    private String license = "0";
    private String date_in = "0";
    private String date_out = "0";
    private String promotion_id = "0";
    private String price = "0";
    private String discount = "0";
    private String losscard = "0";
    private String hours = "0";
    private String minute = "0";
    private String isMember = "false";
    private String member_cash_balance = "0";
    private String member_decreaseCashOrHour = "0";
    private String member_pro_minute_balance_use = "0";
    private String overdate = "0";
    private String cartype_name = "";
    private String pro_name = "";
    ////////////////////Params Action Save Out////////////////////////////


    private DrawerLayout drawer;
    CardView card_ok;
    EditText edit_info;
    EditText edit_id_card;
    Button btn_camera;
    ImageView imageView;
    private ProgressDialog progressDoalog;
    LinearLayout linearLayout_capture_image;
    TextView title_edit_id_card;


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
        setContentView(R.layout.activity_out_car_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ทำรายการขาออก");

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

        edit_info = findViewById(R.id.edit_info);
        card_ok = findViewById(R.id.card_ok);
        edit_id_card = findViewById(R.id.edit_id_card);
        btn_camera = findViewById(R.id.btn_camera);
        imageView = findViewById(R.id.imageView);
        title_edit_id_card = findViewById(R.id.title_edit_id_card);
        linearLayout_capture_image = findViewById(R.id.linearLayout_capture_image);


        edit_id_card.setText(tag_id_card);
        card_ok.setOnClickListener(this);
        btn_camera.setOnClickListener(this);


        if (status_radio_carout_not_capture_img == true) {

            linearLayout_capture_image.setVisibility(View.GONE);
            title_edit_id_card.setText("1");


        }


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
    public void onBackPressed() {
        Intent intent = new Intent(OutCarMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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
                Intent intent = new Intent(OutCarMainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                break;


            case R.id.action_clear:

                edit_info.setText("");
                edit_id_card.setText("");
                tag_id_card = null;
                path_image = "";
                imageView.setImageResource(R.drawable.ic_image_96);


                break;


            default:

        }
        return super.onOptionsItemSelected(item);
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


        edit_info.setText("");
        edit_id_card.setText("");
        tag_id_card = null;

        try {

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String tagInfo = "";
            byte[] tagId = tag.getId();
            tag_id_card = String.valueOf(Long.parseLong(bytesToHexString(tagId), 16));


            showToastLog(TAG, "formattag_HEX: " + bytesToHexString(tagId));
            showToastLog(TAG, "formattag_DEC: " + tag_id_card);


            readMifareClassic(tag);

        } catch (Exception e) {

            showToastSuccess("RFID ไม่ตรงกัน", OutCarMainActivity.this);
            tag_id_card = null;
            edit_id_card.setText("เลข Card");
            showToastLog(TAG, "Error: " + e);
            progressDoalog.dismiss();

        }

    }


    public void readMifareClassic(Tag tag) {
        MifareClassic mifareClassicTag = MifareClassic.get(tag);
        new OutCarMainActivity.ReadMifareClassicTask(mifareClassicTag).execute();

    }


    private class ReadMifareClassicTask extends AsyncTask<Void, Void, Void> {

        /*
        MIFARE Classic tags are divided into sectors, and each sector is sub-divided into blocks.
        Block size is always 16 bytes (BLOCK_SIZE). Sector size varies.
        MIFARE Classic 1k are 1024 bytes (SIZE_1K), with 16 sectors each of 4 blocks.
        */

        MifareClassic taskTag;
        int numOfBlock;
        final int FIX_SECTOR_COUNT = 16;
        boolean success;
        final int numOfSector = 16;
        final int numOfBlockInSector = 4;
        byte[][][] buffer = new byte[numOfSector][numOfBlockInSector][MifareClassic.BLOCK_SIZE];

        ReadMifareClassicTask(MifareClassic tag) {
            taskTag = tag;
            success = false;
        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                taskTag.connect();

                for (int s = 0; s < numOfSector; s++) {
                    if (taskTag.authenticateSectorWithKeyA(s, MifareClassic.KEY_DEFAULT)) {
                        for (int b = 0; b < numOfBlockInSector; b++) {
                            int blockIndex = (s * numOfBlockInSector) + b;
                            buffer[s][b] = taskTag.readBlock(blockIndex);
                        }
                    }
                }

                success = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (taskTag != null) {
                    try {
                        taskTag.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //display block
            if (success) {
                String stringBlock = "";

                for (int k = 0; k < MifareClassic.BLOCK_SIZE; k++) {
                    stringBlock += String.format("%02X", buffer[name_nfc_sector_id][name_nfc_block_in_sector_id][k] & 0xff) + " ";
                }
                stringBlock += "\n";


                // showToastLog(TAG, stringBlock + "");

                fun_calculationparking(stringBlock + "");


            } else {
                progressDoalog.dismiss();
                showToastWarning("อ่าน Promotion ผิดพลาด กรุณาทำรายการใหม่", getApplicationContext());
            }


        }
    }


    private void fun_calculationparking(String nfc_promotion) {

        showToastLog(TAG, nfc_promotion);


        HashMap<String, String> SendData = new HashMap<>();
        SendData.put("cardID", tag_id_card);
        SendData.put("proID", nfc_promotion);
        SendData.put("macnineID", name_macnineid);
        SendData.put("machineName", name_machinename);


        Call<Result_action_mobile_calculationparking> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_calculationparking(SendData);
        call.enqueue(new Callback<Result_action_mobile_calculationparking>() {
            @Override
            public void onResponse(Call<Result_action_mobile_calculationparking> call, Response<Result_action_mobile_calculationparking> response) {
                if (response.body() != null) {


                    progressDoalog.dismiss();
                    if (response.body().getBoolStatus() == true) {

                        edit_id_card.setText(tag_id_card);

                        if (response.body().getIsMember() == false) {

                            recordin_no = response.body().getVisitor().getRecordinNo() + "";
                            license = response.body().getVisitor().getLicense() + "";
                            date_in = response.body().getVisitor().getDateIn() + "";
                            date_out = response.body().getVisitor().getDateOut() + "";
                            promotion_id = response.body().getVisitor().getProID() + "";
                            price = response.body().getVisitor().getPrice() + "";
                            discount = response.body().getVisitor().getDiscount() + "";
                            losscard = response.body().getVisitor().getLosscard() + "";
                            hours = response.body().getVisitor().getHours() + "";
                            minute = response.body().getVisitor().getMinute() + "";
                            isMember = response.body().getIsMember() + "";
                            overdate = response.body().getVisitor().getPriceOverdate() + "";
                            cartype_name = response.body().getVisitor().getCartypeName() + "";
                            pro_name = response.body().getVisitor().getProName() + "";


                            showToastLog(TAG, "visitor");
                            String txt_lic_plate = response.body().getVisitor().getLicense() + "";
                            String txt_cartypeName = response.body().getVisitor().getCartypeName() + "";
                            String txt_date_out = response.body().getVisitor().getDateOut() + "";
                            String txt_date_in = response.body().getVisitor().getDateIn() + "";
                            String txt_proName = response.body().getVisitor().getProName() + "";
                            String txt_price = response.body().getVisitor().getPrice() + "";
                            String txt_price_overdate = response.body().getVisitor().getPriceOverdate() + "";
                            String txt_hours = response.body().getVisitor().getHours() + "";
                            String txt_minute = response.body().getVisitor().getMinute() + "";


                            edit_info.setText("ทะเบียนรถ : " + txt_lic_plate + "\n" +
                                    "ประเภท : " + txt_cartypeName + "\n" +
                                    "เวลาเข้า : " + txt_date_in + "\n" +
                                    "เวลาออก : " + txt_date_out + "\n" +
                                    "เวลาจอด : " + txt_hours + " ชั่วโมง " + txt_minute + " นาที\n" +
                                    "ชื่อโปร : " + txt_proName + "\n" +
                                    "ค่าปรับ : " + txt_price_overdate + " บาท\n" +
                                    "ค่าบริการสุทธิ : " + txt_price + " บาท\n "
                            );


                        } else {

                            recordin_no = response.body().getMember().getRecordNo() + "";
                            license = response.body().getMember().getLicense() + "";
                            date_in = response.body().getMember().getDateIn() + "";
                            date_out = response.body().getMember().getDateOut() + "";
                            promotion_id = response.body().getMember().getIntProIDLast() + "";
                            price = response.body().getMember().getIntPrice() + "";
                            discount = response.body().getMember().getIntDiscount() + "";
                            losscard = response.body().getMember().getIntLosscard() + "";
                            hours = response.body().getMember().getHours() + "";
                            minute = response.body().getMember().getMinutes() + "";
                            isMember = response.body().getIsMember() + "";
                            member_cash_balance = response.body().getMember().getMemberCashBalance() + "";
                            member_decreaseCashOrHour = response.body().getMember().getMemberDecreaseCashOrHour() + "";
                            member_pro_minute_balance_use = response.body().getMember().getMemberProMinuteBalanceUse() + "";
                            overdate = response.body().getMember().getIntPriceOverDate() + "";
                            cartype_name = "Member";


                            showToastLog(TAG, "member");
                            String txt_lic_plate = response.body().getMember().getLicense() + "";
                            String txt_date_out = response.body().getMember().getDateOut() + "";
                            String txt_date_in = response.body().getMember().getDateIn() + "";
                            String txt_hours = response.body().getMember().getHours() + "";
                            String txt_minute = response.body().getMember().getMinutes() + "";
                            String txt_intPrice = response.body().getMember().getIntPrice() + "";
                            String txt_intPriceOverDate = response.body().getMember().getIntPriceOverDate() + "";
                            String txt_cartypeName = "Member";

                            edit_info.setText("ทะเบียนรถ : " + txt_lic_plate + "\n" +
                                    "ประเภท : " + txt_cartypeName + "\n" +
                                    "เวลาเข้า : " + txt_date_in + "\n" +
                                    "เวลาออก : " + txt_date_out + "\n" +
                                    "เวลาจอด : " + txt_hours + " ชั่วโมง " + txt_minute + " นาที\n" +
                                    "ค่าปรับ : " + txt_intPriceOverDate + " บาท\n" +
                                    "ค่าบริการสุทธิ : " + txt_intPrice + " บาท\n "
                            );


                        }


                    } else {


                        showToastWarning(response.body().getMessage() + "", OutCarMainActivity.this);
                        progressDoalog.dismiss();

                    }


                } else {

                    showToastWarning("ทำรายการผิดพลาด action_mobile_calculationparking", OutCarMainActivity.this);
                    progressDoalog.dismiss();

                }


            }

            @Override
            public void onFailure(Call<Result_action_mobile_calculationparking> call, Throwable t) {
                showToastWarning("ผิดพลาด" + t.toString(), OutCarMainActivity.this);
                progressDoalog.dismiss();

            }
        });


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

        } else if (status_radio_carout_capture_img == true) {

            if (path_image.equals("")) {
                status = false;
                showToastWarning("กรุณาถ่ายรูป", getApplicationContext());
            }


        } else if (status_radio_carout_not_capture_img == true) {


            File myDir = new File("/sdcard/Pictures/");
            Bitmap finalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_no_image50);
            String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String imageFileName = "IMG_" + timeStamp + ".jpg";
            path_image = imageFileName;
            File file = new File(myDir, imageFileName);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();


            } catch (Exception e) {
                e.printStackTrace();

            }

            status = true;

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
        name_guardhouse_out = settings.getString(PREF_GUARDHOUSE_OUT, DefaultString);
        name_macnineid = settings.getString(PREF_MACNINEID, DefaultString);
        name_machinename = settings.getString(PREF_MACHINENAME, DefaultString);
        name_posid = settings.getString(PREF_POSID, DefaultString);
        name_taxid = settings.getString(PREF_TAXID, DefaultString);
        name_nfc_sector_id = settings.getInt(PREF_NFC_SECTOR_ID, DefaultInt);
        name_nfc_block_in_sector_id = settings.getInt(PREF_NFC_BLOCK_IN_SECTOR_ID, DefaultInt);
        name_companyname = settings.getString(PREF_COMPANYNAME, DefaultString);


        name_mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);
        status_radio_carout_print_all = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_ALL, DefaultBoolean);
        status_radio_carout_not_print = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_NOT_PRINT, DefaultBoolean);
        status_radio_carout_print_price_only = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_PRICE_ONLY, DefaultBoolean);
        status_radio_carout_capture_img = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_CAPTURE_IMG, DefaultBoolean);
        status_radio_carout_not_capture_img = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_NOT_CAPTURE_IMG, DefaultBoolean);


    }


    private void RecordHistoryCarOutData(
            String tran_carout_user_id,
            String tran_carout_user_name,
            String tran_carout_user_no_record,
            String tran_carout_address,
            String tran_carout_posid,
            String tran_carout_taxid,
            String tran_carout_cartype_name,
            String tran_carout_path_img,
            String tran_carout_guardhouse_out,
            String tran_carout_receipt,
            String tran_carout_date_in,
            String tran_carout_date_out,
            String tran_carout_card_id,
            String tran_carout_recordin_no,
            String tran_carout_license,
            String tran_carout_promotion_id,
            String tran_carout_price,
            String tran_carout_losscard,
            String tran_carout_discount,
            String tran_carout_hours,
            String tran_carout_minute,
            String tran_carout_isMember,
            String tran_carout_member_cash_balance,
            String tran_carout_member_decreaseCashOrHour,
            String tran_carout_member_pro_minute_balance_use,
            String tran_carout_overdate,
            String tran_carout_proname


    ) {


        History_data_carout_dao list = new History_data_carout_dao();
        list.setTran_carout_user_id(tran_carout_user_id);
        list.setTran_carout_user_name(tran_carout_user_name);
        list.setTran_carout_user_no_record(tran_carout_user_no_record);
        list.setTran_carout_address(tran_carout_address);
        list.setTran_carout_posid(tran_carout_posid);
        list.setTran_carout_taxid(tran_carout_taxid);
        list.setTran_carout_cartype_name(tran_carout_cartype_name);
        list.setTran_carout_path_img(tran_carout_path_img);
        list.setTran_carout_guardhouse_out(tran_carout_guardhouse_out);
        list.setTran_carout_receipt(tran_carout_receipt);
        list.setTran_carout_date_in(tran_carout_date_in);
        list.setTran_carout_date_out(tran_carout_date_out);
        list.setTran_carout_card_id(tran_carout_card_id);
        list.setTran_carout_recordin_no(tran_carout_recordin_no);
        list.setTran_carout_license(tran_carout_license);
        list.setTran_carout_promotion_id(tran_carout_promotion_id);
        list.setTran_carout_price(tran_carout_price);
        list.setTran_carout_losscard(tran_carout_losscard);
        list.setTran_carout_discount(tran_carout_discount);
        list.setTran_carout_hours(tran_carout_hours);
        list.setTran_carout_minute(tran_carout_minute);
        list.setTran_carout_isMember(tran_carout_isMember);
        list.setTran_carout_member_cash_balance(tran_carout_member_cash_balance);
        list.setTran_carout_member_decreaseCashOrHour(tran_carout_member_decreaseCashOrHour);
        list.setTran_carout_member_pro_minute_balance_use(tran_carout_member_pro_minute_balance_use);
        list.setTran_carout_overdate(tran_carout_overdate);
        list.setTran_carout_proname(tran_carout_proname);
        DataHistoryCarOutDao dao = new DataHistoryCarOutDao(getApplicationContext());
        dao.open();
        dao.add_tran_history_car_out(list);
        dao.close();


    }


    @Override
    public void onClick(View v) {


        if (v == card_ok) {

            if (checkdata()) {

                progressDoalog.show();

                File file1 = new File("/sdcard/Pictures/" + path_image);

                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
                MultipartBody.Part body1 = MultipartBody.Part.createFormData("file_img_license", file1.getName(), requestFile);
                MultipartBody.Part body2 = MultipartBody.Part.createFormData("file_img_driver", file1.getName(), requestFile);


                RequestBody tag_id_card1 = RequestBody.create(MediaType.parse("text/plain"), tag_id_card + "");
                RequestBody recordin_no1 = RequestBody.create(MediaType.parse("text/plain"), recordin_no + "");
                RequestBody license1 = RequestBody.create(MediaType.parse("text/plain"), license + "");
                RequestBody date_in1 = RequestBody.create(MediaType.parse("text/plain"), date_in + "");
                RequestBody date_out1 = RequestBody.create(MediaType.parse("text/plain"), date_out + "");
                RequestBody promotion_id1 = RequestBody.create(MediaType.parse("text/plain"), promotion_id + "");
                RequestBody price1 = RequestBody.create(MediaType.parse("text/plain"), price + "");
                RequestBody discount1 = RequestBody.create(MediaType.parse("text/plain"), discount + "");
                RequestBody losscard1 = RequestBody.create(MediaType.parse("text/plain"), losscard + "");
                RequestBody hours1 = RequestBody.create(MediaType.parse("text/plain"), hours + "");
                RequestBody minute1 = RequestBody.create(MediaType.parse("text/plain"), minute + "");
                RequestBody user_id1 = RequestBody.create(MediaType.parse("text/plain"), user_id + "");
                RequestBody user_no_record1 = RequestBody.create(MediaType.parse("text/plain"), user_no_record + "");
                RequestBody user_name1 = RequestBody.create(MediaType.parse("text/plain"), user_name + "");
                RequestBody name_guardhouse_out1 = RequestBody.create(MediaType.parse("text/plain"), name_guardhouse_out + "");
                RequestBody name_posid1 = RequestBody.create(MediaType.parse("text/plain"), name_posid + "");
                RequestBody isMember1 = RequestBody.create(MediaType.parse("text/plain"), isMember + "");
                RequestBody member_cash_balance1 = RequestBody.create(MediaType.parse("text/plain"), member_cash_balance + "");
                RequestBody member_decreaseCashOrHour1 = RequestBody.create(MediaType.parse("text/plain"), member_decreaseCashOrHour + "");
                RequestBody member_pro_minute_balance_use1 = RequestBody.create(MediaType.parse("text/plain"), member_pro_minute_balance_use + "");
                RequestBody overdate1 = RequestBody.create(MediaType.parse("text/plain"), overdate + "");

                Call<Result_action_save_out> call = HttpManager.getInstance(ip_address, port).getService().action_save_out(
                        tag_id_card1,
                        recordin_no1,
                        license1, date_in1, date_out1, promotion_id1, price1, discount1, losscard1, hours1, minute1, user_id1, user_no_record1,
                        user_name1, name_guardhouse_out1, name_posid1, name_guardhouse_out1, isMember1, member_cash_balance1, member_decreaseCashOrHour1,
                        member_pro_minute_balance_use1, overdate1, body1, body2);


                call.enqueue(new Callback<Result_action_save_out>() {
                    @Override
                    public void onResponse(Call<Result_action_save_out> call, Response<Result_action_save_out> response) {

                        if (response.body() != null) {

                            if (response.body().getBoolStatus() == true) {

                                String receipt_no = response.body().getData().getReceiptNo() + "";
                                String timestamp_in = response.body().getData().getTimeIn() + "";
                                String timestamp_out = response.body().getData().getTimeOut() + "";
                                String amount = response.body().getData().getAmount() + "";
//

                                RecordHistoryCarOutData(user_id, user_name, user_no_record, user_address, name_posid, name_taxid, cartype_name, path_image, name_guardhouse_out,
                                        receipt_no, date_in, date_out, tag_id_card, recordin_no, license, promotion_id, price, losscard, discount, hours, minute, isMember, member_cash_balance, member_decreaseCashOrHour, member_pro_minute_balance_use, overdate,pro_name);


                                if (status_radio_carout_not_print == true) {

                                    showToastLog(TAG, "status_radio_carout_not_print");


                                } else if (status_radio_carout_print_all == true) {
                                    //TODO Print Car OUT

                                    showToastLog(TAG, "status_radio_carout_print_all");

                                    PrintOUT(name_companyname, name_mac_address_print, name_posid, name_taxid, tag_id_card,
                                            timestamp_in, timestamp_out, license, price, receipt_no,hours,minute,pro_name);


                                } else if (status_radio_carout_print_price_only == true) {
                                    //TODO Print Car OUT

                                    if(amount.equals("0")){

                                        showToastLog(TAG, "status_radio_carout_print_price_only");

                                    }else{

                                        PrintOUT(name_companyname, name_mac_address_print, name_posid, name_taxid, tag_id_card,
                                                timestamp_in, timestamp_out, license, price, receipt_no,hours,minute,pro_name);

                                    }




                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressDoalog.dismiss();
                                        String text_message = "บันทึกสำเร็จ";
                                        showToastSuccess(text_message + "", getApplicationContext());
                                        edit_info.setText("");
                                        edit_id_card.setText("");
                                        tag_id_card = null;
                                        path_image = "";
                                        imageView.setImageResource(R.drawable.ic_image_96);

                                    }
                                }, 500);


                                //TODO Print Car OUT

                            } else {

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressDoalog.dismiss();
                                        String text_message = response.body().getMessage();
                                        showToastWarning(text_message + "", getApplicationContext());
                                        edit_info.setText("");
                                        edit_id_card.setText("");
                                        tag_id_card = null;
                                        path_image = "";
                                        imageView.setImageResource(R.drawable.ic_image_96);

                                    }
                                }, 500);


                            }


                        } else {

                            showToastWarning("ทำรายการผิดพลาด Result_action_save_out", OutCarMainActivity.this);
                            progressDoalog.dismiss();

                        }

                    }

                    @Override
                    public void onFailure(Call<Result_action_save_out> call, Throwable t) {

                        showToastWarning("OutCarMainActivity ผิดพลาด" + t.toString(), getApplicationContext());
                        showToastLog(TAG, t.toString());
                        progressDoalog.dismiss();

                    }


                });


            }


        } else if (btn_camera == v) {

            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
            } else {

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


    private void PrintOUT(
            String companyname, String printerMacAddress, String name_posid, String name_taxid,
            String cardCardId, String description_time_in, String description_time_out,
            String license_plate, String amount, String receipt , String hours, String  minutes,
            String  proname
    ) {


        //  VISITOR_OUT_CONTENT


        final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        final UUID PRINTER_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


        BluetoothDevice bluetoothDevice = null;
        BluetoothSocket bluetoothSocket = null;


        try {

            //region    CHECK_BLUETOOTH_COMPATIBLE_AND_PERMISSION
            if (bluetoothAdapter == null) {


                showToastWarning("Device does not support Bluetooth", this);

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                   // showToastWarning("Bluetooth connect Permission required", this);

                }
            }
            if (!bluetoothAdapter.isEnabled()) {

                showToastWarning("Bluetooth is turned off", this);

            }
            if (!BluetoothAdapter.checkBluetoothAddress(printerMacAddress)) {

                showToastWarning("Invalid bluetooth address", this);

            }
            //endregion CHECK_BLUETOOTH_COMPATIBLE_AND_PERMISSION


            bluetoothDevice = bluetoothAdapter.getRemoteDevice(printerMacAddress);
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(PRINTER_UUID);

            bluetoothSocket.connect();
            InputStream inputStream = bluetoothSocket.getInputStream();
            OutputStream outputStream = bluetoothSocket.getOutputStream();


            //region    SET_ALIGNMENT_TO_LEFT


            outputStream.write(MiniThermal80MMv4.Command.ESC_Init);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            MiniThermal80MMv4.Command.ESC_Align[2] = 0x01;
            outputStream.write(MiniThermal80MMv4.Command.ESC_Align);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text(companyname, "TIS-620", 255, 1, 1, 1));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("TAX INVOICE(ABB)", "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("เลขประจำตัวเสียภาษี/TAX ID : " + name_taxid, "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ใบเสร็จรับเงิน/ใบกำกับภาษีอย่างย่อ", "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("POS ID : " + name_posid, "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ค่าบริการที่จอดรถ (Mobile)", "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("เลขที่/TAX INVOICE NO : " + receipt, "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("----------------------------------------", "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);


            MiniThermal80MMv4.Command.ESC_Align[2] = 0x00;
            outputStream.write(MiniThermal80MMv4.Command.ESC_Align);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("Code : " + cardCardId, "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ทะเบียนรถ/License : " + license_plate, "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("เวลาเข้า/In : " + description_time_in, "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("เวลาออก/Out : " + description_time_out, "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("เวลาจอด : " + hours + " ชั่วโมง" +" " + minutes+" นาที", "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ชื่อโปร : " + proname , "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ค่าบริการ/Amount : " + amount + " บาท", "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);

            MiniThermal80MMv4.Command.ESC_Align[2] = 0x01;
            outputStream.write(MiniThermal80MMv4.Command.ESC_Align);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("----------------------------------------", "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("รวมภาษีมูลค่าเพิ่มแล้ว (VAT INCLUDED)", "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ขอบคุณที่ใช้บริการ/Thank you", "TIS-620", 255, 0, 0, 0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);

            outputStream.flush();
            Thread.sleep(200);


        } catch (Exception exception) {

            showToastWarning("Exception thrown : " + exception.getMessage(), this);


        } finally {
            if (bluetoothSocket != null) {
                try {
                    bluetoothSocket.close();
                } catch (Exception ignored) {

                }
            }
        }


    }
}