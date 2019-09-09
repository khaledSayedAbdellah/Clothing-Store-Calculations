package com.example.khale.mlabes.tabs;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.khale.mlabes.R;
import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.database.table.Expenses;
import com.example.khale.mlabes.database.table.OutgoingGoods;
import com.example.khale.mlabes.entities.Adapter;
import com.example.khale.mlabes.entities.Adapter_mony;
import com.example.khale.mlabes.entities.InEntity;
import com.example.khale.mlabes.entities.InEntity_mony;

import java.util.ArrayList;
import java.util.List;

public class Tab4 extends AppCompatActivity {

    DatabaseHelper mydb;
    ArrayList<InEntity_mony> list=new ArrayList<InEntity_mony>();
    InEntity_mony entity;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab4);

        mydb = new DatabaseHelper(this);
        lv= findViewById(R.id.lv_expenses);

        SetDataToList();

        Adapter_mony adapter=new Adapter_mony(Tab4.this,list);
        lv.setAdapter(adapter);



    }


    public void SetDataToList(){

        Cursor cursor = mydb.selectAll(Expenses.TABLE_NAME);
        while (cursor.moveToNext()){
            entity=new InEntity_mony();

            entity.setId(cursor.getString(cursor.getColumnIndex(Expenses.ID)));
            entity.setReson(cursor.getString(cursor.getColumnIndex(Expenses.REASON)));
            entity.setPrice(cursor.getString(cursor.getColumnIndex(Expenses.PRICE)));
            entity.setDate(cursor.getString(cursor.getColumnIndex(Expenses.DATE)));

            list.add(entity);
        }
    }

}
