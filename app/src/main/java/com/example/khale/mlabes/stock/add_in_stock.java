package com.example.khale.mlabes.stock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khale.mlabes.R;
import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.database.table.IncomingGoods;
import com.example.khale.mlabes.database.table.OutgoingGoods;

import java.util.List;

public class add_in_stock extends AppCompatActivity {

    EditText name,price,code,number;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_in_stock);

        mydb=new DatabaseHelper(this);
        name=findViewById(R.id.name_item);
        price=findViewById(R.id.price_item);
        code=findViewById(R.id.code_item);
        number=findViewById(R.id.num_item);

    }


    public void add_clicked_stock(View view) {

        try {

            String _name = name.getText().toString();
            String _price = price.getText().toString();
            String _code = code.getText().toString();
            String _number = number.getText().toString();

            if(_name.trim().length()>2 && _price.trim().length()>1 && _code.trim().length()>1
                    && _number.trim().length()>0){

                boolean statename = false;

                List<String> list_of_namesin = mydb.getAllNames(IncomingGoods.TABLE_NAME);
                String[] nameofin = new String[list_of_namesin.size()];
                nameofin = list_of_namesin.toArray(nameofin);


                long id =-1;
                int icount=0;

                if (nameofin.length > 0) {

                    for (int i = 0; i < nameofin.length; i++) {

                        if (_name.trim().equals(nameofin[i].trim())) {

                            statename = true;
                            icount=i;
                        }
                    }

                    if (statename == true) {

                        List<String> allvalues1 = mydb.selectAllbyname(IncomingGoods.TABLE_NAME, IncomingGoods.NAME,nameofin[icount]);
                        String[] arrallvalues1 = allvalues1.get(0).toString().trim().split("-");

                        String code_of_old= arrallvalues1[2].toString();
                        if(_code.trim().equals(code_of_old)){


                            if(_price.equals(arrallvalues1[1].toString())){

                                List<String> allvalues = mydb.selectAllbyname(IncomingGoods.TABLE_NAME, IncomingGoods.NAME, nameofin[icount]);
                                String[] arrallvalues = allvalues.get(0).toString().trim().split("-");

                                int newnumber = Integer.parseInt(_number);
                                int oldnumber = Integer.parseInt(arrallvalues[3].toString());
                                int number_after_update = newnumber + oldnumber;
                                String valueofnumber = String.valueOf(number_after_update);


                                id = mydb.update(IncomingGoods.TABLE_NAME, new String[]
                                                {IncomingGoods.NAME, IncomingGoods.PRICE, IncomingGoods.CODE, IncomingGoods.NUMBER},
                                        new String[]{_name, _price, _code, valueofnumber},IncomingGoods.NAME,nameofin[icount]);


                            }else {
                                id=mydb.insert(IncomingGoods.TABLE_NAME,new String[]
                                                {IncomingGoods.NAME,IncomingGoods.PRICE, IncomingGoods.CODE,IncomingGoods.NUMBER},
                                        new String[]{_name,_price,_code,_number});
                            }

                        }else {
                            id=mydb.insert(IncomingGoods.TABLE_NAME,new String[]
                                            {IncomingGoods.NAME,IncomingGoods.PRICE, IncomingGoods.CODE,IncomingGoods.NUMBER},
                                    new String[]{_name,_price,_code,_number});
                        }

                    }else {
                        id=mydb.insert(IncomingGoods.TABLE_NAME,new String[]
                                        {IncomingGoods.NAME,IncomingGoods.PRICE, IncomingGoods.CODE,IncomingGoods.NUMBER},
                                new String[]{_name,_price,_code,_number});
                    }
                }else {

                    id=mydb.insert(IncomingGoods.TABLE_NAME,new String[]
                                    {IncomingGoods.NAME,IncomingGoods.PRICE, IncomingGoods.CODE,IncomingGoods.NUMBER},
                            new String[]{_name,_price,_code,_number});

                }


                if(id !=-1){
                    Toast.makeText(getApplicationContext(),"تمت الاضافه",Toast.LENGTH_SHORT).show();

                    name.setText("");
                    price.setText("");
                    code.setText("");
                    number.setText("");

                }else {
                    Toast.makeText(getApplicationContext(),"خطأ .. حاول مره اخري",Toast.LENGTH_SHORT).show();


                }


            }else{
                Toast.makeText(getApplicationContext(),"يرجي ادخال بيانات صحيحه",Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"خطأ يرجي المحاوله مره اخري",Toast.LENGTH_SHORT).show();

        }





    }
}
