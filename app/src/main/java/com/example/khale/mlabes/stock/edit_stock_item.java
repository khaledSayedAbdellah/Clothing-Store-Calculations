package com.example.khale.mlabes.stock;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khale.mlabes.R;
import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.database.table.IncomingGoods;

import java.util.List;

public class edit_stock_item extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText id,name,price,code,num;
    String id_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_stock);

        mydb = new DatabaseHelper(this);
        id = findViewById(R.id.txt_edit_stock_id);

        name = findViewById(R.id.txt_edit_stock_name);
        price = findViewById(R.id.txt_edit_stock_price);
        code = findViewById(R.id.txt_edit_stock_code);
        num = findViewById(R.id.txt_edit_stock_num);


    }



    public void btn_edit_search(View view) {

        try {

            id_search = id.getText().toString();

            List<String> allvalues = mydb.selectAllbyid(IncomingGoods.TABLE_NAME,IncomingGoods.ID,id_search);

            String[] arrallvalues= allvalues.get(0).toString().trim().split("-");


            name.setText(arrallvalues[0]);
            price.setText(arrallvalues[1]);
            code.setText(arrallvalues[2]);
            num.setText(arrallvalues[3]);

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"خطأ حاول مره اخري",Toast.LENGTH_SHORT).show();


        }


    }

    public void btn_edit_save(View view) {


        String _name = name.getText().toString();
        String _price = price.getText().toString();
        String _code = code.getText().toString();
        String _num = num.getText().toString();

        long id_update = mydb.update(IncomingGoods.TABLE_NAME,new String[]
                {IncomingGoods.NAME,IncomingGoods.PRICE,IncomingGoods.CODE,IncomingGoods.NUMBER},
                new String[]{_name,_price,_code,_num},IncomingGoods.ID,id_search);

        if (id_update !=-1){
            Toast.makeText(getApplicationContext(),"تم التعديل",Toast.LENGTH_SHORT).show();

            id.setText("");
            name.setText("");
            price.setText("");
            code.setText("");
            num.setText("");
        }else {
            Toast.makeText(getApplicationContext(),"حاول مره اخري",Toast.LENGTH_SHORT).show();
        }



    }
}
