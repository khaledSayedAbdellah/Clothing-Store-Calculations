package com.example.khale.mlabes.tabs;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.khale.mlabes.R;
import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.database.table.IncomingGoods;
import com.example.khale.mlabes.entities.Adapter;
import com.example.khale.mlabes.entities.InEntity;

import java.util.ArrayList;

public class Tab3 extends AppCompatActivity {

    DatabaseHelper mydb;
    ArrayList<InEntity> list=new ArrayList<InEntity>();
    InEntity entity;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);

        mydb = new DatabaseHelper(this);
        lv= findViewById(R.id.lv_stock);

        SetDataToList();
        Adapter adapter=new Adapter(Tab3.this,list);
        lv.setAdapter(adapter);

    }

    public void SetDataToList(){

        Cursor cursor = mydb.selectAll(IncomingGoods.TABLE_NAME);
        while (cursor.moveToNext()){
            entity=new InEntity();

            entity.setId(cursor.getString(cursor.getColumnIndex(IncomingGoods.ID)));
            entity.setName(cursor.getString(cursor.getColumnIndex(IncomingGoods.NAME)));
            entity.setCode(cursor.getString(cursor.getColumnIndex(IncomingGoods.CODE)));
            entity.setPrice(cursor.getString(cursor.getColumnIndex(IncomingGoods.PRICE)));
            entity.setNumber(cursor.getString(cursor.getColumnIndex(IncomingGoods.NUMBER)));

            list.add(entity);
        }
    }
}
