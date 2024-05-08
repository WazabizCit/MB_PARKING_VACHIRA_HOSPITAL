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
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.adapter.History_CarOut_Data_Adapter;
import com.example.mb_parking_vachira_hospital.dao.DataHistoryCarOutDao;
import com.example.mb_parking_vachira_hospital.model.History_data_carin_dao;
import com.example.mb_parking_vachira_hospital.model.History_data_carout_dao;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;
import com.example.mb_parking_vachira_hospital.util.MiniThermal80MMv4;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class HistoryCarOutMainActivity  extends ImportantMethod {



    private static final String PREFS_NAME = "preferences";
    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";

    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";
    private static final String PREF_USER_ID = "pref_user_id";
    private static final String PREF_USER_NAME = "pref_user_name";
    private static final String PREF_USER_ADDRESS = "pref_user_address";
    private static final String PREF_USER_NO_RECORD = "pref_user_no_record";
    private static final String PREF_GUARDHOUSE_IN = "pref_guardhouse_in";
    private static final String PREF_COMPANYNAME  = "pref_companyname";


    private static final String PREF_GUARDHOUSE_OUT = "pref_guardhouse_out";
    private static final String PREF_MACNINEID = "pref_macnineid";
    private static final String PREF_MACHINENAME = "pref_machinename";
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
    private String name_company;
    private String name_mac_address_print = "0";
    private String name_guardhouse_out;
    private String name_macnineid;
    private String name_machinename;


    //////////////////////////////////////////////


    ListView listview;
    History_CarOut_Data_Adapter adapter;
    ArrayList<History_data_carout_dao> mlist;
    TextView txt_view_nodata;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDoalog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_car_out_main);


        loadPreferences();
        inintInstances();
        getListHistory();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ประวัติงานขาออก");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }


    private void inintInstances() {
        txt_view_nodata = findViewById(R.id.txt_view_nodata);
        listview = findViewById(R.id.listview);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(pullToRefreshListener);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryCarOutMainActivity.this);
                builder.setCancelable(false);
                builder.setMessage("คุณต้องการที่จะพิมพ์ข้อมูลบัตรใช่หรือไม่");
                builder.setPositiveButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("ใช่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something
                        String id_card = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_card_id();
                        String timestamp_out = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_date_out();
                        String timestamp_in = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_date_in();
                        String license_plate = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_license();
                        String price = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_price();
                        String receipt = ((History_data_carout_dao) adapter.getItem(position)).getTran_carout_receipt();
                        dialog.dismiss();

                        PrintOUT(name_company,name_mac_address_print,name_posid,name_taxid,id_card,timestamp_in,timestamp_out,license_plate,price,receipt);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });


    }




    SwipeRefreshLayout.OnRefreshListener pullToRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();
        }
    };

    private void refreshData() {
        getListHistory();
        swipeRefreshLayout.setRefreshing(false);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == android.R.id.home) {

            Intent intent = new Intent(HistoryCarOutMainActivity.this, MainActivity.class);
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

        Intent intent = new Intent(HistoryCarOutMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void getListHistory() {
        String datetime = getCurrentDate();

        DataHistoryCarOutDao dao = new DataHistoryCarOutDao(getApplicationContext());
        dao.open();
        ArrayList<History_data_carout_dao> mlist = dao.getDataHistoryCarout();
        dao.close();
        if (mlist.size() == 0) {
            listview.setVisibility(View.GONE);
            txt_view_nodata.setVisibility(View.VISIBLE);

        } else {
            adapter = new History_CarOut_Data_Adapter(HistoryCarOutMainActivity.this, mlist);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            txt_view_nodata.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
        }


    }





    private void PrintOUT(
            String companyname,String printerMacAddress,String name_posid,String name_taxid ,
            String cardCardId,String description_time_in,String description_time_out,
            String license_plate, String amount ,String receipt
     ) {




        //  VISITOR_OUT_CONTENT




        final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        final UUID PRINTER_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");



        BluetoothDevice bluetoothDevice = null;
        BluetoothSocket bluetoothSocket = null;



        try {

            //region    CHECK_BLUETOOTH_COMPATIBLE_AND_PERMISSION
            if(bluetoothAdapter == null){



                showToastWarning("Device does not support Bluetooth",this);

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                    showToastWarning("Bluetooth connect Permission required",this);

                }
            }
            if (!bluetoothAdapter.isEnabled()){

                showToastWarning("Bluetooth is turned off",this);

            }
            if(!BluetoothAdapter.checkBluetoothAddress(printerMacAddress)){

                showToastWarning("Invalid bluetooth address",this);

            }
            //endregion CHECK_BLUETOOTH_COMPATIBLE_AND_PERMISSION




            bluetoothDevice = bluetoothAdapter.getRemoteDevice(printerMacAddress);
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(PRINTER_UUID);

            bluetoothSocket.connect();
            InputStream inputStream   = bluetoothSocket.getInputStream();
            OutputStream outputStream = bluetoothSocket.getOutputStream();


            //region    SET_ALIGNMENT_TO_LEFT


            outputStream.write(MiniThermal80MMv4.Command.ESC_Init);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            MiniThermal80MMv4.Command.ESC_Align[2] = 0x01;
            outputStream.write(MiniThermal80MMv4.Command.ESC_Align);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text(companyname, "TIS-620",255,1,1,1));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("TAX INVOICE(ABB)", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("เลขประจำตัวเสียภาษี/TAX ID : "+name_taxid, "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ใบเสร็จรับเงิน/ใบกำกับภาษีอย่างย่อ", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("POS ID : "+name_posid, "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ค่าบริการที่จอดรถ (Mobile)", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("เลขที่/TAX INVOICE NO : "+receipt, "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("----------------------------------------", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);



            MiniThermal80MMv4.Command.ESC_Align[2] = 0x00;
            outputStream.write(MiniThermal80MMv4.Command.ESC_Align);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("Code : "+cardCardId, "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ทะเบียนรถ/License : "+license_plate, "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("เวลาเข้า/In : "+description_time_in, "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("เวลาออก/Out : "+description_time_out, "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ค่าบริการ/Amount : "+amount +" บาท", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);

            MiniThermal80MMv4.Command.ESC_Align[2] = 0x01;
            outputStream.write(MiniThermal80MMv4.Command.ESC_Align);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("----------------------------------------", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("รวมภาษีมูลค่าเพิ่มแล้ว (VAT INCLUDED)", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text("ขอบคุณที่ใช้บริการ/Thank you", "TIS-620",255,0,0,0));
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);
            outputStream.write(MiniThermal80MMv4.Command.LF);

            outputStream.flush();
            Thread.sleep(200);




        }catch (Exception exception){

            showToastWarning("Exception thrown : " + exception.getMessage(),this);


        }finally {
            if(bluetoothSocket != null){
                try {
                    bluetoothSocket.close();
                }catch (Exception ignored){

                }
            }
        }



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
        name_company = settings.getString(PREF_COMPANYNAME, DefaultString);
        name_guardhouse_out = settings.getString(PREF_GUARDHOUSE_OUT, DefaultString);
        name_macnineid = settings.getString(PREF_MACNINEID, DefaultString);
        name_machinename = settings.getString(PREF_MACHINENAME, DefaultString);


        name_mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);


    }








}

