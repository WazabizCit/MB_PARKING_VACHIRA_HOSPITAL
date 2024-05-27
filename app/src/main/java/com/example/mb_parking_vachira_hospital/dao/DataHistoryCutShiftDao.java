package com.example.mb_parking_vachira_hospital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mb_parking_vachira_hospital.model.History_data_carin_dao;
import com.example.mb_parking_vachira_hospital.model.History_data_cutshift_dao;

import java.util.ArrayList;


public class DataHistoryCutShiftDao {
    private SQLiteDatabase database;
    private DbHelper dbHelperCar;

    public DataHistoryCutShiftDao(Context context) {
        dbHelperCar = new DbHelper(context);
    }

    public void open() {
        database = dbHelperCar.getWritableDatabase();
    }

    public void close() {
        dbHelperCar.close();

    }

    public ArrayList<History_data_cutshift_dao> getDataHistory() {

        ArrayList<History_data_cutshift_dao> list = new ArrayList<>();
        String query = "select * from tran_history_cutshift  order by tran_cutshift_id desc limit 50";

        Cursor cursor = database.rawQuery(query,new String[]{});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {



            History_data_cutshift_dao dao = new History_data_cutshift_dao();
            dao.setTran_cutshift_id(cursor.getInt(0));
            dao.setTran_cutshift_user_name(cursor.getString(1));
            dao.setTran_cutshift_date_start(cursor.getString(2));
            dao.setTran_cutshift_date_end(cursor.getString(3));
            dao.setTran_cutshift_price(cursor.getString(4));
            dao.setTran_cutshift_discount(cursor.getString(5));

            list.add(dao);
            cursor.moveToNext();
        }

        return list;
    }

    public void add_tran_history_cutshift(History_data_cutshift_dao list) {


        ContentValues values = new ContentValues();
        values.put("tran_cutshift_user_name", list.getTran_cutshift_user_name());
        values.put("tran_cutshift_date_start", list.getTran_cutshift_date_start());
        values.put("tran_cutshift_date_end", list.getTran_cutshift_date_end());
        values.put("tran_cutshift_price", list.getTran_cutshift_price());
        values.put("tran_cutshift_discount", list.getTran_cutshift_discount());




        this.database.insert("tran_history_cutshift", null, values);


    }




}
