package com.example.mb_parking_vachira_hospital.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.mb_parking_vachira_hospital.model.History_data_carout_dao;

import java.util.ArrayList;

public class DataHistoryCarOutDao {


    private SQLiteDatabase database;
    private DbHelper dbHelperCar;

    public DataHistoryCarOutDao(Context context) {
        dbHelperCar = new DbHelper(context);
    }

    public void open() {
        database = dbHelperCar.getWritableDatabase();
    }

    public void close() {
        dbHelperCar.close();

    }

    public ArrayList<History_data_carout_dao> getDataHistoryCarout() {

        ArrayList<History_data_carout_dao> list = new ArrayList<>();
        String query = "select * from tran_history_car_out  order by tran_carout_id desc limit 50";

        Cursor cursor = database.rawQuery(query,new String[]{});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            History_data_carout_dao dao = new History_data_carout_dao();
            dao.setTran_carout_id(cursor.getInt(0));
            dao.setTran_carout_user_id(cursor.getString(1));
            dao.setTran_carout_user_name(cursor.getString(2));
            dao.setTran_carout_user_no_record(cursor.getString(3));
            dao.setTran_carout_address(cursor.getString(4));
            dao.setTran_carout_posid(cursor.getString(5));
            dao.setTran_carout_taxid(cursor.getString(6));
            dao.setTran_carout_cartype_name(cursor.getString(7));
            dao.setTran_carout_path_img(cursor.getString(8));
            dao.setTran_carout_guardhouse_out(cursor.getString(9));
            dao.setTran_carout_receipt(cursor.getString(10));
            dao.setTran_carout_date_in(cursor.getString(11));
            dao.setTran_carout_date_out(cursor.getString(12));
            dao.setTran_carout_card_id(cursor.getString(13));
            dao.setTran_carout_recordin_no(cursor.getString(14));
            dao.setTran_carout_license(cursor.getString(15));
            dao.setTran_carout_promotion_id(cursor.getString(16));
            dao.setTran_carout_price(cursor.getString(17));
            dao.setTran_carout_losscard(cursor.getString(18));
            dao.setTran_carout_discount(cursor.getString(19));
            dao.setTran_carout_hours(cursor.getString(20));
            dao.setTran_carout_minute(cursor.getString(21));
            dao.setTran_carout_isMember(cursor.getString(22));
            dao.setTran_carout_member_cash_balance(cursor.getString(23));
            dao.setTran_carout_member_decreaseCashOrHour(cursor.getString(24));
            dao.setTran_carout_member_pro_minute_balance_use(cursor.getString(25));
            dao.setTran_carout_overdate(cursor.getString(26));
            list.add(dao);
            cursor.moveToNext();
        }

        return list;
    }

    public void add_tran_history_car_out(History_data_carout_dao list) {


        ContentValues values = new ContentValues();

        values.put("tran_carout_user_id", list.getTran_carout_user_id());
        values.put("tran_carout_user_name", list.getTran_carout_user_name());
        values.put("tran_carout_user_no_record", list.getTran_carout_user_no_record());
        values.put("tran_carout_address", list.getTran_carout_address());
        values.put("tran_carout_posid", list.getTran_carout_posid());
        values.put("tran_carout_taxid", list.getTran_carout_taxid ());
        values.put("tran_carout_cartype_name", list.getTran_carout_cartype_name());
        values.put("tran_carout_path_img", list.getTran_carout_path_img());
        values.put("tran_carout_guardhouse_out", list.getTran_carout_guardhouse_out());
        values.put("tran_carout_receipt", list.getTran_carout_receipt());
        values.put("tran_carout_date_in", list.getTran_carout_date_in());
        values.put("tran_carout_date_out", list.getTran_carout_date_out());
        values.put("tran_carout_card_id", list.getTran_carout_card_id());
        values.put("tran_carout_recordin_no", list.getTran_carout_recordin_no());
        values.put("tran_carout_license", list.getTran_carout_license());
        values.put("tran_carout_promotion_id", list.getTran_carout_promotion_id());
        values.put("tran_carout_price", list.getTran_carout_price());
        values.put("tran_carout_losscard", list.getTran_carout_losscard());
        values.put("tran_carout_discount", list.getTran_carout_discount());
        values.put("tran_carout_hours", list.getTran_carout_hours());
        values.put("tran_carout_minute", list.getTran_carout_minute());
        values.put("tran_carout_isMember", list.getTran_carout_isMember());
        values.put("tran_carout_member_cash_balance", list.getTran_carout_member_cash_balance());
        values.put("tran_carout_member_decreaseCashOrHour", list.getTran_carout_member_decreaseCashOrHour());
        values.put("tran_carout_member_pro_minute_balance_use", list.getTran_carout_member_pro_minute_balance_use());
        values.put("tran_carout_overdate", list.getTran_carout_overdate());



        this.database.insert("tran_history_car_out", null, values);


    }




}
