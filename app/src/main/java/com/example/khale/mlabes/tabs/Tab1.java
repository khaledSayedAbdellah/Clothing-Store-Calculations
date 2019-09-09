package com.example.khale.mlabes.tabs;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khale.mlabes.R;
import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.database.table.Expenses;
import com.example.khale.mlabes.database.table.Gaint_mony;
import com.example.khale.mlabes.database.table.IncomingGoods;
import com.example.khale.mlabes.database.table.OutgoingGoods;
import com.example.khale.mlabes.entities.Adapter;
import com.example.khale.mlabes.entities.Adapter_gain;
import com.example.khale.mlabes.entities.InEntity;
import com.example.khale.mlabes.entities.InEntity_gain;
import com.example.khale.mlabes.entities.InEntity_mony;

import java.util.ArrayList;
import java.util.List;

public class Tab1 extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText mony_out, all_gaint, netgaint;
    String monyset;
    ArrayList<InEntity_gain> list = new ArrayList<InEntity_gain>();
    InEntity_gain entity;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);

        mydb = new DatabaseHelper(this);

        mony_out = findViewById(R.id.all_mony_out);
        all_gaint = findViewById(R.id.all_gaint);

        netgaint = findViewById(R.id.netgaint);
        monyset = String.valueOf(getAllPayed());

        lv = findViewById(R.id.list_rebh);

        SetDataToList();
        Adapter_gain adapter = new Adapter_gain(Tab1.this, list);
        lv.setAdapter(adapter);

        setmonyouting();
        setAllgaint();
        setnetgaint();
    }


    public void setAllgaint() {

        int gaints = 0;

        List<String> NamesOfGain = mydb.getPrices(Gaint_mony.TABLE_NAME);
        String[] priceArr = new String[NamesOfGain.size()];
        priceArr = NamesOfGain.toArray(priceArr);

        for (int i = 0; i < priceArr.length; i++) {

            gaints += Integer.parseInt(priceArr[i]);
        }
        all_gaint.setText(String.valueOf(gaints));

    }

    public void SetDataToList() {

        Cursor cursor = mydb.selectAll(Gaint_mony.TABLE_NAME);
        while (cursor.moveToNext()) {
            entity = new InEntity_gain();

            entity.setId(cursor.getString(cursor.getColumnIndex(Gaint_mony.ID)));
            entity.setName(cursor.getString(cursor.getColumnIndex(Gaint_mony.NAME)));
            entity.setPrice(cursor.getString(cursor.getColumnIndex(Gaint_mony.GAINT)));

            list.add(entity);
        }
    }

    public void setnetgaint() {

        int allgaint = Integer.parseInt(all_gaint.getText().toString());
        int allpayed = Integer.parseInt(mony_out.getText().toString());

        int netgaint_int = allgaint - allpayed;

        netgaint.setText(String.valueOf(netgaint_int));
    }

    public void setmonyouting() {
        mony_out.setText(monyset);
    }

    public int getAllPayed() {

        int price_integer = 0;

        List<String> allprices = mydb.getAllPrices(Expenses.TABLE_NAME);

        for (int i = 0; i < allprices.size(); i++) {

            price_integer += Integer.parseInt(allprices.get(i));
        }

        return price_integer;
    }
}
