package com.example.mb_parking_vachira_hospital.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.mb_parking_vachira_hospital.adapter.History_CarIn_Data_Adapter;
import com.example.mb_parking_vachira_hospital.dao.DataHistoryCarInDao;
import com.example.mb_parking_vachira_hospital.model.History_data_carin_dao;
import com.example.mb_parking_vachira_hospital.util.ImportantMethod;

import java.util.ArrayList;

public class HistoryCarInMainActivity  extends ImportantMethod {

    ListView listview;
    History_CarIn_Data_Adapter adapter;
    ArrayList<History_data_carin_dao> mlist;
    TextView txt_view_nodata;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDoalog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_car_in_main);

        inintInstances();
        getListHistory();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("ประวัติงานขาเข้า");

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


                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryCarInMainActivity.this);
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
                        String id_card = ((History_data_carin_dao) adapter.getItem(position)).getTran_carin_card_id();
                        String timestamp = ((History_data_carin_dao) adapter.getItem(position)).getTran_carin_timestamp_carin();
                        String license_plate = ((History_data_carin_dao) adapter.getItem(position)).getTran_carin_license_plate();


                        dialog.dismiss();
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

            Intent intent = new Intent(HistoryCarInMainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(HistoryCarInMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void getListHistory() {
        String datetime = getCurrentDate();

        DataHistoryCarInDao dao = new DataHistoryCarInDao(getApplicationContext());
        dao.open();
        ArrayList<History_data_carin_dao> mlist = dao.getDataHistory(datetime);
        dao.close();
        if (mlist.size() == 0) {
            listview.setVisibility(View.GONE);
            txt_view_nodata.setVisibility(View.VISIBLE);

        } else {
            adapter = new History_CarIn_Data_Adapter(HistoryCarInMainActivity.this, mlist);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            txt_view_nodata.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
        }


    }






}
