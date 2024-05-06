package com.example.mb_parking_vachira_hospital.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
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
import com.example.mb_parking_vachira_hospital.util.FuncPrinter;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;
import com.example.mb_parking_vachira_hospital.util.MiniThermal80MMv4;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class SubSettingPrinterMainActivity extends ImportantMethod implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    String TAG = "SubSettingPrinterMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_MAC_ADDRESS_PRINT = "pref_mac_address_print";

    private static final String PREF_STATUS_RADIO_CARIN_NOT_PRINT = "pref_status_radio_carin_not_print";
    private static final String PREF_STATUS_RADIO_CARIN_PRINT_ALL = "pref_status_radio_carin_print_all";

    private static final String PREF_STATUS_RADIO_CAROUT_NOT_PRINT = "pref_status_radio_carout_not_print";
    private static final String PREF_STATUS_RADIO_CAROUT_PRINT_ALL = "pref_status_radio_carout_print_all";
    private static final String PREF_STATUS_RADIO_CAROUT_PRINT_PRICE_ONLY = "pref_status_radio_carout_print_price_only";


    private final int DefaultInt = 0;
    private final boolean DefaultBoolean = false;
    private final String DefaultString = "null";
    private boolean status_radio_carin_not_print;
    private boolean status_radio_carin_print_all;

    private boolean status_radio_carout_not_print;
    private boolean status_radio_carout_print_all;
    private boolean status_radio_carout_print_price_only;


    private String name_mac_address_print = "0";

    EditText edit_ip_printer;
    CardView card_ok;
    CardView card_test_print;


    RadioButton radio_carin_not_print;
    RadioButton radio_carin_print_all;

    RadioButton radio_carout_not_print;
    RadioButton radio_carout_print_all;
    RadioButton radio_carout_print_price_only;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting_printer_main);



        loadPreferences();
        inintInstances();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ตั้งค่า Print");

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


    }

    private void inintInstances() {


        edit_ip_printer = findViewById(R.id.edit_ip_printer);
        card_ok = findViewById(R.id.card_ok);
        card_test_print = findViewById(R.id.card_test_print);

        radio_carout_print_all = findViewById(R.id.radio_carout_print_all);
        radio_carout_not_print = findViewById(R.id.radio_carout_not_print);
        radio_carout_print_price_only = findViewById(R.id.radio_carout_print_price_only);

        radio_carin_not_print = findViewById(R.id.radio_carin_not_print);
        radio_carin_print_all = findViewById(R.id.radio_carin_print_all);

        edit_ip_printer.setText(name_mac_address_print + "");
        edit_ip_printer.setSelection(name_mac_address_print.length());


        card_ok.setOnClickListener(this);
        card_test_print.setOnClickListener(this);


        radio_carout_print_all.setChecked(status_radio_carout_print_all);
        radio_carout_not_print.setChecked(status_radio_carout_not_print);
        radio_carout_print_price_only.setChecked(status_radio_carout_print_price_only);

        radio_carin_not_print.setChecked(status_radio_carin_not_print);
        radio_carin_print_all.setChecked(status_radio_carin_print_all);



        radio_carin_print_all.setOnCheckedChangeListener(this);
        radio_carin_not_print.setOnCheckedChangeListener(this);

        radio_carout_print_all.setOnCheckedChangeListener(this);
        radio_carout_not_print.setOnCheckedChangeListener(this);
        radio_carout_print_price_only.setOnCheckedChangeListener(this);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(SubSettingPrinterMainActivity.this, SettingMainActivity.class);
                startActivity(intent);
                finish();
                break;

            default:

        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SubSettingPrinterMainActivity.this, SettingMainActivity.class);
        startActivity(intent);
        finish();


    }



    @Override
    public void onClick(View view) {

        if (view == card_ok) {



            if(checkdata()){
                save_references();
                showToastSuccess("บันทึกสำเร็จ",getApplicationContext());

            }


        }else if(view == card_test_print){




            //region    VISITOR_IN_CONTENT
            String cardNoString = "xxxxx1 : 1234"  ;
            String headerString = "xxxxx2 : 5555";
            String description1 = "xxxxx3 : รถยนต์";
            String description2 = "xxxxx4 : 1234";
            String description3 = "xxxxx5 : 99/99";
            String description4 = "xxxxx6 : 02/05/2024 14.43";




            final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            final String printerMacAddress = "66:32:14:3A:41:3A";
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



                outputStream.write(MiniThermal80MMv4.Command.ESC_Init);
                outputStream.write(MiniThermal80MMv4.Command.LF);
                outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text(cardNoString, "CP874",255,2,2,0));
                outputStream.write(MiniThermal80MMv4.Command.LF);
                outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text(headerString, "CP874",255,1,1,0));
                outputStream.write(MiniThermal80MMv4.Command.LF);
                outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text(description1, "CP874",255,0,0,0));
                outputStream.write(MiniThermal80MMv4.Command.LF);
                outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text(description2, "CP874",255,0,0,0));
                outputStream.write(MiniThermal80MMv4.Command.LF);
                outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text(description3, "CP874",255,0,0,0));
                outputStream.write(MiniThermal80MMv4.Command.LF);
                outputStream.write(MiniThermal80MMv4.PrinterCommand.POS_Print_Text(description4, "CP874",255,0,0,0));
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

    }

    private boolean checkdata() {
        String ip_printer = edit_ip_printer.getText().toString().replaceAll(" ", "");

        boolean status = true;

        if (ip_printer.length() == 0) {
            status = false;
            showToastWarning("กรุณาใส่ IP Mac Address Print", getApplicationContext());
        }


        return status;
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (compoundButton == radio_carin_not_print) {
            status_radio_carin_not_print = b;

        } else if (compoundButton == radio_carin_print_all) {
            status_radio_carin_print_all = b;

        } else if (compoundButton == radio_carout_print_all) {
            status_radio_carout_print_all = b;

        } else if (compoundButton == radio_carout_not_print) {
            status_radio_carout_not_print = b;

        } else if (compoundButton == radio_carout_print_price_only) {

            status_radio_carout_print_price_only = b;

        }


    }





    private void save_references() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit

        editor.putString(PREF_MAC_ADDRESS_PRINT, edit_ip_printer.getText().toString());
        editor.putBoolean(PREF_STATUS_RADIO_CARIN_NOT_PRINT, status_radio_carin_not_print);
        editor.putBoolean(PREF_STATUS_RADIO_CARIN_PRINT_ALL, status_radio_carin_print_all);
        editor.putBoolean(PREF_STATUS_RADIO_CAROUT_NOT_PRINT, status_radio_carout_not_print);
        editor.putBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_ALL, status_radio_carout_print_all);
        editor.putBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_PRICE_ONLY, status_radio_carout_print_price_only);
        editor.commit();


    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        name_mac_address_print = settings.getString(PREF_MAC_ADDRESS_PRINT, DefaultString);
        status_radio_carin_not_print = settings.getBoolean(PREF_STATUS_RADIO_CARIN_NOT_PRINT, DefaultBoolean);
        status_radio_carin_print_all = settings.getBoolean(PREF_STATUS_RADIO_CARIN_PRINT_ALL, DefaultBoolean);

        status_radio_carout_not_print = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_NOT_PRINT, DefaultBoolean);
        status_radio_carout_print_all = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_ALL, DefaultBoolean);
        status_radio_carout_print_price_only = settings.getBoolean(PREF_STATUS_RADIO_CAROUT_PRINT_PRICE_ONLY, DefaultBoolean);



    }


}