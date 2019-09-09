package com.example.khale.mlabes.sell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.khale.mlabes.R;

public class sell_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_select);
    }

    public void add_clicked(View view) {
        Intent MyIntent=new Intent(this,add_sell_item.class);
        startActivity(MyIntent);
    }

    public void edit_clicked(View view) {
        Intent MyIntent =new Intent(this ,edit_sell_item.class);
        startActivity(MyIntent);
    }

    public void delete_clicked(View view) {
        Intent MyIntent =new Intent(this ,delete_sell_item.class);
        startActivity(MyIntent);
    }
}
