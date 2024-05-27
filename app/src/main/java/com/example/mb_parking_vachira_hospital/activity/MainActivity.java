package com.example.mb_parking_vachira_hospital.activity;



import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.dao.DataHistoryCarInDao;
import com.example.mb_parking_vachira_hospital.dao.DataHistoryCutShiftDao;
import com.example.mb_parking_vachira_hospital.manager.HttpManager;
import com.example.mb_parking_vachira_hospital.model.History_data_carin_dao;
import com.example.mb_parking_vachira_hospital.model.History_data_cutshift_dao;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_login;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_logout;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;
import com.example.mb_parking_vachira_hospital.util.MiniThermal80MMv4;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends ImportantMethod implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    String TAG = "MainActivity";

    private ProgressDialog progressDoalog;
    private DrawerLayout drawer;
    CardView btn_gate_in;
    CardView btn_gate_out;
    CardView btn_estamp;

    private static final String PREFS_NAME = "preferences";
    private static final String PREF_USER_NAME = "pref_user_name";
    private static final String PREF_USER_ID = "pref_user_id";
    private static final String PREF_USER_NO_RECORD = "pref_user_no_record";


    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";


    private static final String PREF_COMPANYNAME  = "pref_companyname";


    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";



    private final String DefaultString = "null";
    private String user_name = "Name - Admin";
    private String user_id;
    private String user_no_record;
    private String ip_address;
    private String port;
    private String name_company;
    private String name_mac_address_print = "0";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadPreferences();
        inintInstances();

    }

    private void inintInstances() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Parking Mobile");


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.txt_name);
        navUsername.setText("ชื่อ : " + user_name);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        btn_gate_in = findViewById(R.id.btn_gate_in);
        btn_gate_out = findViewById(R.id.btn_gate_out);
        btn_estamp = findViewById(R.id.btn_estamp);


        btn_gate_in.setOnClickListener(this);
        btn_gate_out.setOnClickListener(this);
        btn_estamp.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // handle arrow click here


        switch (id) {


        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, MainSplashActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {


            case R.id.item_detail_user_login:
                Intent intent_detail_user_login = new Intent(MainActivity.this, DetailUserLoginMainActivity.class);
                startActivity(intent_detail_user_login);
                finish();
                break;


//            case R.id.item_history_in:
//                Intent history_in = new Intent(MainActivity.this, HistoryCarInMainActivity.class);
//                startActivity(history_in);
//                finish();
//                break;


            case R.id.item_history_out:
                Intent intent_out = new Intent(MainActivity.this, HistoryCarOutMainActivity.class);
                startActivity(intent_out);
                finish();
                break;


            case R.id.item_history_estamp:
                Intent history_estamp = new Intent(MainActivity.this, HistoryEstampMainActivity.class);
                startActivity(history_estamp);
                finish();
                break;

            case R.id.item_history_cutshift:
                Intent history_cutshift = new Intent(MainActivity.this, HistoryCutShiftMainActivity.class);
                startActivity(history_cutshift);
                finish();
                break;




            case R.id.item_logout:
                fun_logout();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {

        if (v == btn_gate_in) {

            startActivity(new Intent(MainActivity.this, InCarMainActivity.class));
            finish();

        } else if (v == btn_estamp) {

            startActivity(new Intent(MainActivity.this, EstampMainActivity.class));
            finish();
        } else if (v == btn_gate_out) {

            startActivity(new Intent(MainActivity.this, OutCarMainActivity.class));
            finish();
        }

    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // Get value


        ip_address = settings.getString(PREF_IP_ADDRESS, DefaultString);
        port = settings.getString(PREF_PORT, DefaultString);
        user_name = settings.getString(PREF_USER_NAME, DefaultString);
        user_id = settings.getString(PREF_USER_ID, DefaultString);
        user_no_record = settings.getString(PREF_USER_NO_RECORD, DefaultString);
        name_company = settings.getString(PREF_COMPANYNAME, DefaultString);
        name_mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);


    }


    private void fun_logout() {




        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("คุณต้องการ ตัดกะ ใช่หรือไม่ ?");


        alert.setPositiveButton("ไม่ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {


                Intent intent = new Intent(MainActivity.this, MainSplashActivity.class);
                startActivity(intent);
                finish();


            }
        });

        alert.setNegativeButton("ใช่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        progressDoalog = new ProgressDialog(MainActivity.this);
                        progressDoalog.setTitle("กำลังส่งข้อมูล Service");
                        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDoalog.setMessage("กรุณารอสักครู่...");
                        progressDoalog.setCancelable(false);
                        progressDoalog.show();



                        HashMap<String, String> SendData = new HashMap<>();
                        SendData.put("user_id", user_id);
                        SendData.put("user_no", user_no_record);


                        Call<Result_action_mobile_logout> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_logout(SendData);
                        call.enqueue(new Callback<Result_action_mobile_logout>() {
                            @Override
                            public void onResponse(Call<Result_action_mobile_logout> call, Response<Result_action_mobile_logout> response) {
                                if (response.body() != null) {

                                    if (response.body().getBoolStatus() == true) {


                                        String user_name = response.body().getData().getUserName() + "";
                                        String date_start = response.body().getData().getDateStart() + "";
                                        String date_end = response.body().getData().getDateEnd() + "";
                                        String price = response.body().getData().getPrice() + "";
                                        String discount = response.body().getData().getDiscount() + "";



                                        PrintCutShift(name_company,name_mac_address_print,user_name,date_start,date_end,price,discount);
                                        RecordHistoryCutShiftData(user_name,date_start,date_end,price,discount);




                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {

                                                progressDoalog.dismiss();
                                                Intent intent = new Intent(MainActivity.this, MainSplashActivity.class);
                                                startActivity(intent);
                                                finish();


                                            }
                                        }, 300);


                                    } else {

                                        progressDoalog.dismiss();
                                        showToastWarning(response.body().getMessage() + "", MainActivity.this);

                                    }


                                } else {
                                    progressDoalog.dismiss();
                                    showToastWarning("ทำรายการผิดพลาด", MainActivity.this);

                                }


                            }

                            @Override
                            public void onFailure(Call<Result_action_mobile_logout> call, Throwable t) {
                                showToastWarning("ผิดพลาด" + t.toString(), MainActivity.this);
                                progressDoalog.dismiss();

                            }
                        });




                    }
                });

        alert.show();

    }



    private void RecordHistoryCutShiftData(String tran_cutshift_user_name,
                                                              String tran_cutshift_date_start,
                                                              String tran_cutshift_date_end,
                                                              String tran_cutshift_price,
                                                              String tran_cutshift_discount
    ) {


        History_data_cutshift_dao list = new History_data_cutshift_dao();
        list.setTran_cutshift_user_name(tran_cutshift_user_name);
        list.setTran_cutshift_date_start(tran_cutshift_date_start);
        list.setTran_cutshift_date_end(tran_cutshift_date_end);
        list.setTran_cutshift_price(tran_cutshift_price);
        list.setTran_cutshift_discount(tran_cutshift_discount);



        DataHistoryCutShiftDao dao = new DataHistoryCutShiftDao(getApplicationContext());
        dao.open();
        dao.add_tran_history_cutshift(list);
        dao.close();


    }



    private void PrintCutShift(
            String companyname,
            String printerMacAddress,
            String cutshift_user_name,
            String cutshift_date_start,
            String cutshift_date_end,
            String cutshift_price,
            String cutshift_discount

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
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text(companyname, "TIS-620",255,1,1,1));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text(" - ข้อมูลการตัดกะ - ", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);


            MiniThermal80MMv4.Command.ESC_Align[2] = 0x00;
            outputStream.write(MiniThermal80MMv4.Command.ESC_Align);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ชื่อ : "+cutshift_user_name, "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("วันเวลาเริ่ม : "+cutshift_date_start, "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("วันเวลาสิ้นสุด : "+cutshift_date_end, "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("รายได้ : "+cutshift_price + " บาท", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ส่วนลด : "+cutshift_discount + " บาท", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
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
