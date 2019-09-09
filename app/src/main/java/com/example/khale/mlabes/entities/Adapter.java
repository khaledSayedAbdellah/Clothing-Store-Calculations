package com.example.khale.mlabes.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.khale.mlabes.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    Context c;
    ArrayList<InEntity> list;

    public Adapter(Context c, ArrayList<InEntity> list) {
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

            convertView = inflater.inflate(R.layout.model,null);
        }
        TextView id = convertView.findViewById(R.id.txt_model_id);
        TextView name = convertView.findViewById(R.id.txt_model_name);
        TextView code = convertView.findViewById(R.id.txt_model_code);
        TextView price = convertView.findViewById(R.id.txt_model_price);
        TextView number = convertView.findViewById(R.id.txt_model_number);

        id.setText(list.get(position).getId());
        name.setText(list.get(position).getName());
        code.setText(list.get(position).getCode());
        price.setText(list.get(position).getPrice());
        number.setText(list.get(position).getNumber());

        if(name.getText().length()>15){
            name.setMaxWidth(500);
        }


        return convertView;
    }


}
