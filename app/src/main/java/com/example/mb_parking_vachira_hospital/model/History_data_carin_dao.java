package com.example.mb_parking_vachira_hospital.model;


import okhttp3.MultipartBody;
import retrofit2.http.Part;
import retrofit2.http.Query;


public class History_data_carin_dao {
    int tran_carin_id;
    String tran_carin_card_id;
    String tran_carin_license_plate;
    String tran_carin_cartype_id;
    String tran_carin_user_id;
    String tran_carin_user_name;
    String tran_carin_user_no_record;
    String tran_carin_address;
    String tran_carin_posid;
    String tran_carin_taxid;
    String tran_carin_timestamp_carin;
    String tran_carin_guardhouse_in;
    String tran_carin_path_img;
    String tran_carin_cartype_name;
    String tran_carin_recordno;

    public String getTran_carin_recordno() {
        return tran_carin_recordno;
    }

    public void setTran_carin_recordno(String tran_carin_recordno) {
        this.tran_carin_recordno = tran_carin_recordno;
    }

    public String getTran_carin_user_name() {
        return tran_carin_user_name;
    }

    public void setTran_carin_user_name(String tran_carin_user_name) {
        this.tran_carin_user_name = tran_carin_user_name;
    }

    public String getTran_carin_cartype_name() {
        return tran_carin_cartype_name;
    }

    public void setTran_carin_cartype_name(String tran_carin_cartype_name) {
        this.tran_carin_cartype_name = tran_carin_cartype_name;
    }

    public int getTran_carin_id() {
        return tran_carin_id;
    }

    public void setTran_carin_id(int tran_carin_id) {
        this.tran_carin_id = tran_carin_id;
    }

    public String getTran_carin_card_id() {
        return tran_carin_card_id;
    }

    public void setTran_carin_card_id(String tran_carin_card_id) {
        this.tran_carin_card_id = tran_carin_card_id;
    }

    public String getTran_carin_license_plate() {
        return tran_carin_license_plate;
    }

    public void setTran_carin_license_plate(String tran_carin_license_plate) {
        this.tran_carin_license_plate = tran_carin_license_plate;
    }

    public String getTran_carin_cartype_id() {
        return tran_carin_cartype_id;
    }

    public void setTran_carin_cartype_id(String tran_carin_cartype_id) {
        this.tran_carin_cartype_id = tran_carin_cartype_id;
    }

    public String getTran_carin_user_id() {
        return tran_carin_user_id;
    }

    public void setTran_carin_user_id(String tran_carin_user_id) {
        this.tran_carin_user_id = tran_carin_user_id;
    }

    public String getTran_carin_user_no_record() {
        return tran_carin_user_no_record;
    }

    public void setTran_carin_user_no_record(String tran_carin_user_no_record) {
        this.tran_carin_user_no_record = tran_carin_user_no_record;
    }


    public String getTran_carin_address() {
        return tran_carin_address;
    }

    public void setTran_carin_address(String tran_carin_address) {
        this.tran_carin_address = tran_carin_address;
    }

    public String getTran_carin_posid() {
        return tran_carin_posid;
    }

    public void setTran_carin_posid(String tran_carin_posid) {
        this.tran_carin_posid = tran_carin_posid;
    }

    public String getTran_carin_taxid() {
        return tran_carin_taxid;
    }

    public void setTran_carin_taxid(String tran_carin_taxid) {
        this.tran_carin_taxid = tran_carin_taxid;
    }

    public String getTran_carin_timestamp_carin() {
        return tran_carin_timestamp_carin;
    }

    public void setTran_carin_timestamp_carin(String tran_carin_timestamp_carin) {
        this.tran_carin_timestamp_carin = tran_carin_timestamp_carin;
    }

    public String getTran_carin_guardhouse_in() {
        return tran_carin_guardhouse_in;
    }

    public void setTran_carin_guardhouse_in(String tran_carin_guardhouse_in) {
        this.tran_carin_guardhouse_in = tran_carin_guardhouse_in;
    }

    public String getTran_carin_path_img() {
        return tran_carin_path_img;
    }

    public void setTran_carin_path_img(String tran_carin_path_img) {
        this.tran_carin_path_img = tran_carin_path_img;
    }
}
