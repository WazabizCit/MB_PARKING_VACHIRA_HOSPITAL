package com.example.mb_parking_vachira_hospital.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.mb_parking_vachira_hospital.model.History_data_carin_dao;

import java.util.ArrayList;


public class DataHistoryCarInDao {
    private SQLiteDatabase database;
    private DbHelper dbHelperCar;

    public DataHistoryCarInDao(Context context) {
        dbHelperCar = new DbHelper(context);
    }

    public void open() {
        database = dbHelperCar.getWritableDatabase();
    }

    public void close() {
        dbHelperCar.close();

    }

    public ArrayList<History_data_carin_dao> getDataHistory(String date) {

        ArrayList<History_data_carin_dao> list = new ArrayList<>();
        String query = "select * from tran_history_car_in  order by tran_carin_id desc limit 50";

        Cursor cursor = database.rawQuery(query,new String[]{});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            History_data_carin_dao dao = new History_data_carin_dao();
            dao.setTran_carin_id(cursor.getInt(0));
            dao.setTran_carin_card_id(cursor.getString(1));
            dao.setTran_carin_license_plate(cursor.getString(2));
            dao.setTran_carin_cartype_id(cursor.getString(3));
            dao.setTran_carin_user_id(cursor.getString(4));
            dao.setTran_carin_user_no_record(cursor.getString(5));
            dao.setTran_carin_address(cursor.getString(6));
            dao.setTran_carin_posid(cursor.getString(7));
            dao.setTran_carin_taxid(cursor.getString(8));
            dao.setTran_carin_timestamp_carin(cursor.getString(9));
            dao.setTran_carin_guardhouse_in(cursor.getString(10));
            dao.setTran_carin_path_img(cursor.getString(11));
            dao.setTran_carin_cartype_name(cursor.getString(12));
            dao.setTran_carin_user_name(cursor.getString(13));




            list.add(dao);
            cursor.moveToNext();
        }

        return list;
    }

    public void add_tran_history_car_in(History_data_carin_dao list) {


        ContentValues values = new ContentValues();
        values.put("tran_carin_card_id", list.getTran_carin_card_id());
        values.put("tran_carin_license_plate", list.getTran_carin_license_plate());
        values.put("tran_carin_cartype_id", list.getTran_carin_cartype_id());
        values.put("tran_carin_user_id", list.getTran_carin_user_id());
        values.put("tran_carin_user_no_record", list.getTran_carin_user_no_record());
        values.put("tran_carin_address", list.getTran_carin_address());
        values.put("tran_carin_posid", list.getTran_carin_posid());
        values.put("tran_carin_taxid", list.getTran_carin_taxid());
        values.put("tran_carin_timestamp_carin", list.getTran_carin_timestamp_carin());
        values.put("tran_carin_guardhouse_in", list.getTran_carin_guardhouse_in());
        values.put("tran_carin_path_img", list.getTran_carin_path_img());
        values.put("tran_carin_cartype_name", list.getTran_carin_cartype_name());
        values.put("tran_carin_user_name", list.getTran_carin_user_name());



        this.database.insert("tran_history_car_in", null, values);


    }




}
