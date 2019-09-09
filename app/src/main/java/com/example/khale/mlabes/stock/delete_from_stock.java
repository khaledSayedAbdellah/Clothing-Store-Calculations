package com.example.khale.mlabes.stock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khale.mlabes.R;
import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.database.table.IncomingGoods;
import com.example.khale.mlabes.database.table.OutgoingGoods;

import java.util.List;

public class delete_from_stock extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText id;
    TextView name;
    String id_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_from_stock);

        mydb = new DatabaseHelper(this);

        id = findViewById(R.id.txt_delete_stock_id);
        name = findViewById(R.id.txtv_delete_stock_name);

    }

    public void delete_stock_search(View view) {

        try {
            id_search = id.getText().toString();
            List<String> allvalues = mydb.selectAllbyid(IncomingGoods.TABLE_NAME,IncomingGoods.ID,id_search);
            String[] arrallvalues= allvalues.get(0).toString().trim().split("-");
            name.setText(arrallvalues[0]+" - "+arrallvalues[2]);

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"خطأ حاول مره اخري",Toast.LENGTH_SHORT).show();


        }

    }

    public void delete_stock_btn(View view) {

        id_search = id.getText().toString();
        long id_delete = mydb.delete(IncomingGoods.TABLE_NAME,IncomingGoods.ID,id_search);

        if (id_delete !=-1){
            Toast.makeText(getApplicationContext(),"تم الحذف",Toast.LENGTH_SHORT).show();
            id.setText("");
            name.setText("name");
        }else {
            Toast.makeText(getApplicationContext(),"حاول مره اخري",Toast.LENGTH_SHORT).show();
        }
    }
}
