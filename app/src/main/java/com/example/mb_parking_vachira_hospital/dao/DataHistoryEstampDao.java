package com.example.mb_parking_vachira_hospital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mb_parking_vachira_hospital.model.History_data_carin_dao;
import com.example.mb_parking_vachira_hospital.model.History_data_estamp_dao;

import java.util.ArrayList;



public class DataHistoryEstampDao {
    private SQLiteDatabase database;
    private DbHelper dbHelperCar;

    public DataHistoryEstampDao(Context context) {
        dbHelperCar = new DbHelper(context);
    }

    public void open() {
        database = dbHelperCar.getWritableDatabase();
    }

    public void close() {
        dbHelperCar.close();

    }

    public ArrayList<History_data_estamp_dao> getDataHistory() {

        ArrayList<History_data_estamp_dao> list = new ArrayList<>();
        String query = "select * from tran_history_estamp  order by tran_estamp_id desc limit 50";

        Cursor cursor = database.rawQuery(query,new String[]{});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            History_data_estamp_dao dao = new History_data_estamp_dao();
            dao.setTran_estamp_id(cursor.getInt(0));
            dao.setTran_estamp_card_id(cursor.getString(1));
            dao.setTran_estamp_promotion_id(cursor.getString(2));
            dao.setTran_estamp_promotion_name(cursor.getString(3));
            dao.setTran_estamp_user_id(cursor.getString(4));
            dao.setTran_estamp_user_no_record(cursor.getString(5));
            dao.setTran_estamp_user_name(cursor.getString(6));
            dao.setM_create_date(cursor.getString(7));


            list.add(dao);
            cursor.moveToNext();
        }

        return list;
    }

    public void add_tran_history_estamp(History_data_estamp_dao list) {


        ContentValues values = new ContentValues();
        values.put("tran_estamp_card_id", list.getTran_estamp_card_id());
        values.put("tran_estamp_promotion_id", list.getTran_estamp_promotion_id());
        values.put("tran_estamp_promotion_name", list.getTran_estamp_promotion_name());
        values.put("tran_estamp_user_id", list.getTran_estamp_user_id());
        values.put("tran_estamp_user_no_record", list.getTran_estamp_user_no_record());
        values.put("tran_estamp_user_name", list.getTran_estamp_user_name());





        this.database.insert("tran_history_estamp", null, values);


    }




}
