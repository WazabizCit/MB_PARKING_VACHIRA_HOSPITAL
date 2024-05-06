package com.example.mb_parking_vachira_hospital.activity;




import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.manager.HttpManager;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_login;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginMainActivity extends ImportantMethod implements View.OnClickListener {


    String TAG = "LoginMainActivity";


    private static final String PREFS_NAME = "preferences";
    private static final String PREF_IP_ADDRESS = "pref_ip_address";
    private static final String PREF_PORT = "pref_port";
    private static final String PREF_GATE = "pref_gate";
    private static final String PREF_GUARDHOUSE = "pref_guardhouse";

    ///////////////////////////////////////////////


    private static final String PREF_USER_ID = "pref_user_id";
    private static final String PREF_USER_NAME = "pref_user_name";
    private static final String PREF_USER_ADDRESS = "pref_user_address";
    private static final String PREF_USER_NO_RECORD = "pref_user_no_record";

    private String user_id;
    private String user_address;
    private String user_no_record;
    private String user_name;




    //////////////////////////////////////////////


    private final int DefaultInt = 0;
    private final String DefaultString = "null";
    private String ip_address;
    private String port;
    private String name_gate;
    private String name_guardhouse;


    private ProgressDialog progressDoalog;
    RelativeLayout relativeLayout_login;
    EditText edit_username;
    EditText edit_password;
    Button btn_login;
    TextView txt_admin_setting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        loadPreferences();
        inintInstances();


    }


    private void inintInstances() {

        relativeLayout_login = findViewById(R.id.relativeLayout_login);
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        btn_login = findViewById(R.id.btn_login);
        txt_admin_setting = findViewById(R.id.txt_admin_setting);


        btn_login.setOnClickListener(this);
        txt_admin_setting.setOnClickListener(this);

//
//        edit_username.setText("cit");
//        edit_password.setText("12345678");


        edit_username.setText("2222");
        edit_password.setText("2222");


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();

    }


    @Override
    public void onClick(View v) {

        if (btn_login == v) {


//            Intent intent = new Intent(LoginMainActivity.this, MainActivity.class);
//            startActivity(intent);
//


            if (checkdata()) {


                progressDoalog = new ProgressDialog(this);
                progressDoalog.setTitle("กำลังส่งข้อมูล Service");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.setMessage("กรุณารอสักครู่...");
                progressDoalog.setCancelable(false);

                final String username = edit_username.getText().toString().replaceAll(" ", "");
                final String password = edit_password.getText().toString().replaceAll(" ", "");
                progressDoalog.show();




                HashMap<String, String> SendData = new HashMap<>();
                SendData.put("username", username);
                SendData.put("password", password);
                SendData.put("guardhouse", name_guardhouse);
                SendData.put("gate", name_gate);


                Call<Result_action_mobile_login> call = HttpManager.getInstance(ip_address, port).getService().action_mobile_login(SendData);
                call.enqueue(new Callback<Result_action_mobile_login>() {
                    @Override
                    public void onResponse(Call<Result_action_mobile_login> call, Response<Result_action_mobile_login> response) {
                        if (response.body() != null) {

                            if (response.body().getBoolStatus() == true) {

                                user_id = response.body().getData().getUserId()+"";
                                user_address = response.body().getData().getAddress()+"";
                                user_no_record = response.body().getData().getUserNoRecord()+"";
                                user_name = response.body().getData().getUserName()+"";
                                save_references();


                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressDoalog.dismiss();
                                        Intent intent = new Intent(LoginMainActivity.this, MainActivity.class);
                                        startActivity(intent);

                                    }
                                }, 800);


                            } else {

                                progressDoalog.dismiss();
                                showToastWarning(response.body().getMessage() + "", LoginMainActivity.this);

                            }


                        } else {
                            progressDoalog.dismiss();
                            showToastWarning("ทำรายการผิดพลาด", LoginMainActivity.this);

                        }


                    }

                    @Override
                    public void onFailure(Call<Result_action_mobile_login> call, Throwable t) {
                        showToastWarning("ผิดพลาด" + t.toString(), LoginMainActivity.this);
                        progressDoalog.dismiss();

                    }
                });


            }


        } else if (txt_admin_setting == v) {

            Intent intent = new Intent(LoginMainActivity.this, SettingMainActivity.class);
            startActivity(intent);


//            if(checkdata()){
//                final String username = edit_username.getText().toString().replaceAll(" ", "");
//                final String password = edit_password.getText().toString().replaceAll(" ", "");
//
//
//                if(username.equals("cit") && password.equals("12345678")){
//
//                    Intent intent = new Intent(LoginMainActivity.this, SettingMainActivity.class);
//                    startActivity(intent);
//
//                }else {
//
//
//
//                    showToastWarning("Username หรือ Password Admin ไม่ถูกต้อง",LoginMainActivity.this);
//
//                }
//
//
//
//            }
//
//





        }

    }

    private boolean checkdata() {

        boolean status = true;

        if (edit_username.getText().toString().replaceAll(" ", "").equals("")) {
            status = false;
            showToastWarning("กรุณาใส่ Username", getApplicationContext());
        } else if (edit_password.getText().toString().replaceAll(" ", "").equals("")) {
            status = false;
            showToastWarning("กรุณาใส่ Password", getApplicationContext());
        }
        return status;

    }


    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // Get value

        ip_address = settings.getString(PREF_IP_ADDRESS, DefaultString);
        port = settings.getString(PREF_PORT, DefaultString);
        name_gate = settings.getString(PREF_GATE, DefaultString);
        name_guardhouse = settings.getString(PREF_GUARDHOUSE, DefaultString);

    }

    private void save_references() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        editor.putString(PREF_USER_ID,user_id);
        editor.putString(PREF_USER_ADDRESS,user_address);
        editor.putString(PREF_USER_NO_RECORD,user_no_record);
        editor.putString(PREF_USER_NAME,user_name);


        editor.commit();


    }



}