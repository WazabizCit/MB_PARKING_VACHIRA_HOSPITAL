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
import com.example.mb_parking_vachira_hospital.model.History_data_cutshift_dao;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;




public class History_CutShift_Data_Adapter extends BaseAdapter {
    //('WAIT','COMPLETE','INPROGRESS')
    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<History_data_cutshift_dao> dao;



    public History_CutShift_Data_Adapter(Activity activity, ArrayList<History_data_cutshift_dao> dao) {

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
            v = inflater.inflate(R.layout.list_data_history_cutshift, null);
        }






        TextView txt_cutshift_user_name  = (TextView) v.findViewById(R.id.txt_cutshift_user_name);
        TextView txt_cutshift_date_start  = (TextView) v.findViewById(R.id.txt_cutshift_date_start);
        TextView txt_cutshift_date_end  = (TextView) v.findViewById(R.id.txt_cutshift_date_end);
        TextView txt_cutshift_price  = (TextView) v.findViewById(R.id.txt_cutshift_price);
        TextView txt_cutshift_discount  = (TextView) v.findViewById(R.id.txt_cutshift_discount);






        History_data_cutshift_dao list = dao.get(position);


        String cutshift_user_name = list.getTran_cutshift_user_name();
        String cutshift_date_start = list.getTran_cutshift_date_start();
        String cutshift_date_end = list.getTran_cutshift_date_end();
        String cutshift_price = list.getTran_cutshift_price();
        String cutshift_discount = list.getTran_cutshift_discount()+"";







        txt_cutshift_user_name.setText(cutshift_user_name+"");
        txt_cutshift_date_start.setText(cutshift_date_start+"");
        txt_cutshift_date_end.setText(cutshift_date_end+"");
        txt_cutshift_price.setText(cutshift_price+"");
        txt_cutshift_discount.setText(cutshift_discount+"");





        return v;
    }





}
