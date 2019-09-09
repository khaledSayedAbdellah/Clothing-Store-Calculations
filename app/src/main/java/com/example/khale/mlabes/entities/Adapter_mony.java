package com.example.khale.mlabes.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.khale.mlabes.R;

import java.util.ArrayList;

public class Adapter_mony extends BaseAdapter {

    Context c;
    ArrayList<InEntity_mony> list;

    public Adapter_mony(Context c, ArrayList<InEntity_mony> list) {
        this.c = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){

            convertView = inflater.inflate(R.layout.model_mony,null);
        }
        TextView id = convertView.findViewById(R.id.txt_model_mony_id);
        TextView reson = convertView.findViewById(R.id.txt_model_mony_reson);
        TextView price = convertView.findViewById(R.id.txt_model_mony_price);
        TextView date = convertView.findViewById(R.id.txt_model_mony_date);

        id.setText(list.get(position).getId());
        reson.setText(list.get(position).getReson());
        price.setText(list.get(position).getPrice());
        date.setText(list.get(position).getDate());

        if(reson.getText().length()>20){
            reson.setMaxWidth(500);
        }


        return convertView;
    }


}
