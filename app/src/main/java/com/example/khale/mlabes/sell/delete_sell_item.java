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

public class delete_sell_item extends AppCompatActivity {


    DatabaseHelper mydb;
    EditText id;
    TextView name;
    String id_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_sell_item);

        mydb = new DatabaseHelper(this);

        id = findViewById(R.id.txt_delete_sell_id);
        name = findViewById(R.id.txtv_delete_sell_name);
    }
    public void updateInNumber(){



        List<String> allvalues = mydb.selectAllbyid(OutgoingGoods.TABLE_NAME,OutgoingGoods.ID,id.getText().toString());
        String[] arrallvalues= allvalues.get(0).toString().trim().split("-");

        String _name = arrallvalues[0];
        String _code = arrallvalues[2];
        String _numberout = arrallvalues[3];


        List<String> allvaluesin = mydb.selectAllby2value(IncomingGoods.TABLE_NAME,
                new String[]{IncomingGoods.NAME,IncomingGoods.CODE},
                new String[]{_name,_code});
        String[] arrallvaluesin= allvaluesin.get(0).toString().trim().split("-");
        String _numberin = arrallvaluesin[3];

        int inNumber=Integer.parseInt(_numberin);
        int outNumber=Integer.parseInt(_numberout);
        int netNumber= inNumber+outNumber;

        String numberAfterUpdate= String.valueOf(netNumber);

        long id= mydb.updateby2value(IncomingGoods.TABLE_NAME,new String[]{IncomingGoods.NUMBER},
                new String[]{numberAfterUpdate}, new String[]{OutgoingGoods.NAME,OutgoingGoods.CODE},
                new String[]{_name,_code});
    }

    public void setPriceSells(){


        List<String> allvalues = mydb.selectAllbyid(OutgoingGoods.TABLE_NAME,OutgoingGoods.ID,id.getText().toString());
        String[] arrallvalues= allvalues.get(0).toString().trim().split("-");

        String _name = arrallvalues[0];

        updateInNumber();
        mydb.delete(Gaint_mony.TABLE_NAME,Gaint_mony.NAME,_name);

    }

    public void delete_sell_searchbtn(View view) {

        try {

            id_search = id.getText().toString();
            List<String> allvalues = mydb.selectAllbyid(OutgoingGoods.TABLE_NAME,OutgoingGoods.ID,id_search);
            String[] arrallvalues= allvalues.get(0).toString().trim().split("-");
            name.setText(arrallvalues[0]+" - "+arrallvalues[2]);

        }catch (Exception e){

        }



    }


    public void delete_sell_deletebtn(View view) {


        id_search = id.getText().toString();
        setPriceSells();
        long id_delete = mydb.delete(OutgoingGoods.TABLE_NAME,OutgoingGoods.ID,id_search);

        if (id_delete !=-1){
            Toast.makeText(getApplicationContext(),"تم الحذف",Toast.LENGTH_SHORT).show();
            id.setText("");
            name.setText("name");
        }else {
            Toast.makeText(getApplicationContext(),"حاول مره اخري",Toast.LENGTH_SHORT).show();
        }

    }
}
