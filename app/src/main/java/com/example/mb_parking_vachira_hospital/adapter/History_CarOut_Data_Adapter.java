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
import com.example.mb_parking_vachira_hospital.model.History_data_carout_dao;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;


public class History_CarOut_Data_Adapter extends BaseAdapter {


    //('WAIT','COMPLETE','INPROGRESS')
    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<History_data_carout_dao> dao;



    public History_CarOut_Data_Adapter(Activity activity, ArrayList<History_data_carout_dao> dao) {

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
            v = inflater.inflate(R.layout.list_data_history_carout, null);
        }




        ImageView ivImg = (ImageView) v.findViewById(R.id.ivImg);
        TextView txt_date  = (TextView) v.findViewById(R.id.txt_date);
        TextView txt_code_card  = (TextView) v.findViewById(R.id.txt_code_card);
        TextView txt_license  = (TextView) v.findViewById(R.id.txt_license);
        TextView txt_type  = (TextView) v.findViewById(R.id.txt_type);
        TextView txt_time_out  = (TextView) v.findViewById(R.id.txt_time_out);
        TextView txt_time_in  = (TextView) v.findViewById(R.id.txt_time_in);
        TextView txt_price  = (TextView) v.findViewById(R.id.txt_price);







        History_data_carout_dao list = dao.get(position);

        String date = list.getTran_carout_date_out();
        String code_card = list.getTran_carout_card_id();
        String license = list.getTran_carout_license();
        String urlImage = list.getTran_carout_path_img();
        String type = list.getTran_carout_cartype_name();
        String time_in = list.getTran_carout_date_in()+"";
        String time_out = list.getTran_carout_date_out()+"";
        String price = list.getTran_carout_price()+" บาท";







        //String filePath = urlImage;
        txt_date.setText(date+"");
        txt_code_card.setText(code_card+"");
        txt_license.setText(license+"");
        txt_type.setText(type);
        txt_time_in.setText(time_in);
        txt_time_out.setText(time_out);
        txt_price.setText(price);


        File file1 = new File("/sdcard/Pictures/" + urlImage);
        File imgFile = new File(file1.toString());
        if (imgFile.exists()) {
            Picasso.get().load(imgFile).resize(250, 250)
                    .centerCrop().into(ivImg);
        }

        return v;
    }





}
