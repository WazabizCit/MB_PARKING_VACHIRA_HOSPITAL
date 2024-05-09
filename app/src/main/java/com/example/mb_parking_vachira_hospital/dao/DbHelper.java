package com.example.mb_parking_vachira_hospital.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DbHelper extends SQLiteOpenHelper {

    private static final String databaseName = "ValetPark.Sqlite";
    private static final int databaseVerSion = 1;
    Context mContect;

    public DbHelper(Context context) {
        super(context, databaseName, null, databaseVerSion);
        this.mContect = context;
    }


    private static final String tableDataHistoryCarInCreateSQL = "CREATE TABLE tran_history_car_in("
            + "tran_carin_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "tran_carin_card_id TEXT  DEFAULT '',"
            + "tran_carin_license_plate TEXT  DEFAULT '',"
            + "tran_carin_cartype_id TEXT  DEFAULT '',"
            + "tran_carin_user_id TEXT  DEFAULT '',"
            + "tran_carin_user_no_record TEXT  DEFAULT '',"
            + "tran_carin_address TEXT DEFAULT '',"
            + "tran_carin_posid TEXT DEFAULT '',"
            + "tran_carin_taxid TEXT  DEFAULT '',"
            + "tran_carin_timestamp_carin TEXT  DEFAULT '',"
            + "tran_carin_guardhouse_in TEXT  DEFAULT '',"
            + "tran_carin_path_img TEXT  DEFAULT '',"
            + "tran_carin_cartype_name TEXT  DEFAULT '',"
            + "tran_carin_user_name TEXT  DEFAULT '',"
            + "m_create_date TEXT  DEFAULT  (datetime('now','localtime')),"
            + "m_delete_flag TEXT  DEFAULT 'N'"
            + ");";



    private static final String tableDataHistoryCarOutCreateSQL = "CREATE TABLE tran_history_car_out("
            + "tran_carout_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "tran_carout_user_id TEXT  DEFAULT '',"
            + "tran_carout_user_name TEXT  DEFAULT '',"
            + "tran_carout_user_no_record TEXT  DEFAULT '',"
            + "tran_carout_address TEXT  DEFAULT '',"
            + "tran_carout_posid TEXT  DEFAULT '',"
            + "tran_carout_taxid TEXT DEFAULT '',"
            + "tran_carout_cartype_name TEXT DEFAULT '',"
            + "tran_carout_path_img TEXT  DEFAULT '',"
            + "tran_carout_guardhouse_out TEXT  DEFAULT '',"
            + "tran_carout_receipt TEXT  DEFAULT '',"
            + "tran_carout_date_in TEXT  DEFAULT '',"
            + "tran_carout_date_out TEXT  DEFAULT '',"
            + "tran_carout_card_id TEXT  DEFAULT '',"
            + "tran_carout_recordin_no TEXT  DEFAULT '',"
            + "tran_carout_license TEXT  DEFAULT '',"
            + "tran_carout_promotion_id TEXT  DEFAULT '',"
            + "tran_carout_price TEXT  DEFAULT '',"
            + "tran_carout_losscard TEXT  DEFAULT '',"
            + "tran_carout_discount TEXT  DEFAULT '',"
            + "tran_carout_hours TEXT  DEFAULT '',"
            + "tran_carout_minute TEXT  DEFAULT '',"
            + "tran_carout_isMember TEXT  DEFAULT '',"
            + "tran_carout_member_cash_balance TEXT  DEFAULT '',"
            + "tran_carout_member_decreaseCashOrHour TEXT  DEFAULT '',"
            + "tran_carout_member_pro_minute_balance_use TEXT  DEFAULT '',"
            + "tran_carout_overdate TEXT  DEFAULT '',"
            + "m_create_date TEXT  DEFAULT  (datetime('now','localtime')),"
            + "m_delete_flag TEXT  DEFAULT 'N'"
            + ");";



    private static final String tableDataHistoryEstampCreateSQL = "CREATE TABLE tran_history_estamp("
            + "tran_estamp_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "tran_estamp_card_id TEXT  DEFAULT '',"
            + "tran_estamp_promotion_id TEXT  DEFAULT '',"
            + "tran_estamp_promotion_name TEXT  DEFAULT '',"
            + "tran_estamp_user_id TEXT  DEFAULT '',"
            + "tran_estamp_user_no_record TEXT  DEFAULT '',"
            + "tran_estamp_user_name TEXT DEFAULT '',"
            + "m_create_date TEXT  DEFAULT  (datetime('now','localtime')),"
            + "m_delete_flag TEXT  DEFAULT 'N'"
            + ");";










    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(tableDataHistoryCarInCreateSQL);
        sqLiteDatabase.execSQL(tableDataHistoryCarOutCreateSQL);
        sqLiteDatabase.execSQL(tableDataHistoryEstampCreateSQL);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
