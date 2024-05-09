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
import com.example.mb_parking_vachira_hospital.model.History_data_estamp_dao;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class History_Estamp_Data_Adapter extends BaseAdapter {

    //('WAIT','COMPLETE','INPROGRESS')
    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<History_data_estamp_dao> dao;



    public History_Estamp_Data_Adapter(Activity activity, ArrayList<History_data_estamp_dao> dao) {

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
            v = inflater.inflate(R.layout.list_data_history_estamp, null);
        }




        ImageView ivImg = (ImageView) v.findViewById(R.id.ivImg);
        TextView txt_date  = (TextView) v.findViewById(R.id.txt_date);
        TextView txt_card_id  = (TextView) v.findViewById(R.id.txt_card_id);
        TextView txt_promotion_name  = (TextView) v.findViewById(R.id.txt_promotion_name);
        TextView txt_promotion_id  = (TextView) v.findViewById(R.id.txt_promotion_id);
        TextView txt_admin_name  = (TextView) v.findViewById(R.id.txt_admin_name);








        History_data_estamp_dao list = dao.get(position);

        String date = list.getM_create_date();
        String card_id = list.getTran_estamp_card_id();
        String promotion_name = list.getTran_estamp_promotion_name();
        String promotion_id = list.getTran_estamp_promotion_id();
        String admin_name = list.getTran_estamp_user_name();








        //String filePath = urlImage;
        txt_date.setText(date+"");
        txt_card_id.setText(card_id+"");
        txt_promotion_name.setText(promotion_name+"");
        txt_promotion_id.setText(promotion_id+"");
        txt_admin_name.setText(admin_name+"");




        return v;
    }





}
