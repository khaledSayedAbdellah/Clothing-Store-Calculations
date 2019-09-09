package com.example.khale.mlabes.stock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.khale.mlabes.R;

public class stock_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_select);
    }

    public void add_stock_select(View view) {
        Intent MyIntent=new Intent(this,add_in_stock.class);
        startActivity(MyIntent);
    }

    public void edit_item_clicked(View view) {
        Intent MyIntent =new Intent(this,edit_stock_item.class);
        startActivity(MyIntent);
    }

    public void delete_clicked(View view) {
        Intent MyIntent =new Intent(this,delete_from_stock.class);
        startActivity(MyIntent);
    }
}
