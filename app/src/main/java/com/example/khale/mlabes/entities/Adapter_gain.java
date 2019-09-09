package com.example.khale.mlabes.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.khale.mlabes.R;

import java.util.ArrayList;

public class Adapter_gain extends BaseAdapter {

    Context c;
    ArrayList<InEntity_gain> list;

    public Adapter_gain(Context c, ArrayList<InEntity_gain> list) {
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

            convertView = inflater.inflate(R.layout.model_gain,null);
        }
        TextView id = convertView.findViewById(R.id.txt_model_gain_id);
        TextView name = convertView.findViewById(R.id.txt_model_gain_name);
        TextView price = convertView.findViewById(R.id.txt_model_gain_price);

        id.setText(list.get(position).getId());
        name.setText(list.get(position).getName());
        price.setText(list.get(position).getPrice());

        if(name.getText().length()>20){
            name.setMaxWidth(500);
        }


        return convertView;
    }


}
