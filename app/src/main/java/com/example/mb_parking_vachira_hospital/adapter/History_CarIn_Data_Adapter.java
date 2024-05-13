package com.example.mb_parking_vachira_hospital.adapter;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mb_parking_vachira_hospital.R;
import com.example.mb_parking_vachira_hospital.model.History_data_carin_dao;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class History_CarIn_Data_Adapter extends BaseAdapter {
    //('WAIT','COMPLETE','INPROGRESS')
    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<History_data_carin_dao> dao;



    public History_CarIn_Data_Adapter(Activity activity, ArrayList<History_data_carin_dao> dao) {

        this.dao = dao;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return dao.size();
    }

    @Override
    public Object getItem(int position) {
        return dao.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;


        if (convertView == null) {
            v = inflater.inflate(R.layout.list_data_history_carin, null);
        }




        ImageView ivImg = (ImageView) v.findViewById(R.id.ivImg);
        TextView txt_date  = (TextView) v.findViewById(R.id.txt_date);
        TextView txt_code_card  = (TextView) v.findViewById(R.id.txt_code_card);
        TextView txt_license  = (TextView) v.findViewById(R.id.txt_license);
        TextView txt_type  = (TextView) v.findViewById(R.id.txt_type);
        TextView txt_record_no  = (TextView) v.findViewById(R.id.txt_record_no);






        History_data_carin_dao list = dao.get(position);

        String date = list.getTran_carin_timestamp_carin();
        String code_card = list.getTran_carin_card_id();
        String license = list.getTran_carin_license_plate();
        String urlImage = list.getTran_carin_path_img();
        String type = list.getTran_carin_cartype_name();
        String record_no = list.getTran_carin_recordno()+"";






        //String filePath = urlImage;
        txt_date.setText(date+"");
        txt_code_card.setText(code_card+"");
        txt_license.setText(license+"");
        txt_type.setText(type);
        txt_record_no.setText(record_no);


        File file1 = new File("/sdcard/Pictures/" + urlImage);
        File imgFile = new File(file1.toString());
        if (imgFile.exists()) {
            Picasso.get().load(imgFile).resize(250, 250)
                    .centerCrop().into(ivImg);
        }

        return v;
    }





}
