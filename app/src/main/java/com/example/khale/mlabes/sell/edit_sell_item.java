package com.example.khale.mlabes.sell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khale.mlabes.R;
import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.database.table.Gaint_mony;
import com.example.khale.mlabes.database.table.IncomingGoods;
import com.example.khale.mlabes.database.table.OutgoingGoods;

import java.util.List;

public class edit_sell_item extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText id,price,num;
    TextView name;
    String id_search;
    String codegaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sell_item);

        mydb = new DatabaseHelper(this);
        id = findViewById(R.id.txt_edit_sell_id);

        name = findViewById(R.id.txtv_edit_sell_name);
        price = findViewById(R.id.txt_edit_sell_price);
        num = findViewById(R.id.txt_edit_sell_num);

    }


    public void  update_gain_mony(){

        List<String> allvalues = mydb.selectAllbyid(IncomingGoods.TABLE_NAME, IncomingGoods.CODE,codegaint);
        String[] arrallvalues = allvalues.get(0).toString().trim().split("-");
        String pricevalue = arrallvalues[1];

        int priceout = Integer.parseInt(price.getText().toString());
        int pricein = Integer.parseInt(pricevalue);
        int number_item_sell= Integer.parseInt(num.getText().toString());

        int newprice = priceout - (pricein * number_item_sell);

        String value_of_price = String.valueOf(newprice);


        long id = mydb.update(Gaint_mony.TABLE_NAME, new String[]{Gaint_mony.GAINT},
                new String[]{value_of_price}, Gaint_mony.NAME, name.getText().toString());

    }


    public void btn_edit_sell_search(View view) {

        try {

            id_search = id.getText().toString();
            List<String> allvalues = mydb.selectAllbyid(OutgoingGoods.TABLE_NAME,OutgoingGoods.ID,id_search);
            String[] arrallvalues= allvalues.get(0).toString().trim().split("-");

            name.setText(arrallvalues[0]);
            price.setText(arrallvalues[1]);
            num.setText(arrallvalues[3]);

        }catch (Exception e){

        }


    }

    public void btn_edit_sell_save(View view) {

        String _price = price.getText().toString();
        String _num = num.getText().toString();

        List<String> allvalues = mydb.selectAllbyid(OutgoingGoods.TABLE_NAME,OutgoingGoods.ID,id_search);
        String[] arrallvalues= allvalues.get(0).toString().trim().split("-");

        String _code= arrallvalues[2].toString();
        codegaint=_code;
        String _name= arrallvalues[0].toString();

        int old_number = Integer.parseInt(arrallvalues[3].toString());
        int new_number = Integer.parseInt(_num);
        int net_number = old_number - new_number;


        List<String> allvaluesin = mydb.selectAllby2value(IncomingGoods.TABLE_NAME,
                new String[]{IncomingGoods.NAME,IncomingGoods.CODE},new String[]{_name,_code});
        String[] arrallvaluesin = allvaluesin.get(0).toString().trim().split("-");
        int in_number = Integer.parseInt(arrallvaluesin[3].toString());
        int in_numberafter=in_number+net_number;

        String number_after_update=String.valueOf(in_numberafter);



        long id_update = mydb.update(OutgoingGoods.TABLE_NAME,new String[]
                        {OutgoingGoods.PRICE,OutgoingGoods.NUMBER},
                new String[]{_price,_num},OutgoingGoods.ID,id_search);

        if (id_update !=-1){

            long id_updateIn = mydb.updateby2value(IncomingGoods.TABLE_NAME,new String[]
                            {IncomingGoods.NUMBER},
                    new String[]{number_after_update},new String[]{IncomingGoods.NAME,IncomingGoods.CODE},
            new String[]{_name,_code});

            if(id_updateIn != -1){
                update_gain_mony();
                Toast.makeText(getApplicationContext(),"تم التعديل",Toast.LENGTH_SHORT).show();
            }

            id.setText("");
            name.setText("name");
            price.setText("");
            num.setText("");
        }else {
            Toast.makeText(getApplicationContext(),"حاول مره اخري",Toast.LENGTH_SHORT).show();
        }

    }
}
