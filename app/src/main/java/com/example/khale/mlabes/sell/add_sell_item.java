package com.example.khale.mlabes.sell;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khale.mlabes.R;
import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.database.table.Gaint_mony;
import com.example.khale.mlabes.database.table.IncomingGoods;
import com.example.khale.mlabes.database.table.OutgoingGoods;

import java.util.List;

public class add_sell_item extends AppCompatActivity {

    Spinner SpinnerItems;
    DatabaseHelper mydb;
    EditText price, number;
    String codevalue;
    String nameofsell;
    String value_of_price;
    String number_sell_item;
    String finalnumberout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sell_item);

        price = findViewById(R.id.price_sell_item);
        number = findViewById(R.id.num_sell_item);

        SpinnerItems = findViewById(R.id.sell_spinner);

        mydb = new DatabaseHelper(this);
        codevalue = "";
        nameofsell = "";
        value_of_price = "";
        number_sell_item = "1";
        finalnumberout = "";

        loadSpinnerData();


    }

    public void updateIn_number() {

        List<String> allvalues = mydb.selectAllby2value(IncomingGoods.TABLE_NAME,
                new String[]{IncomingGoods.NAME, IncomingGoods.CODE}, new String[]{nameofsell, codevalue});
        String[] arrallvalues = allvalues.get(0).toString().trim().split("-");

        int in_number = Integer.parseInt(arrallvalues[3].toString());

        int out_number = Integer.parseInt(finalnumberout);

        int numtoupdate = in_number - out_number;
        String number_after_update = String.valueOf(numtoupdate);

        long id = mydb.updateby2value(IncomingGoods.TABLE_NAME, new String[]{IncomingGoods.NUMBER},
                new String[]{number_after_update}, new String[]{IncomingGoods.NAME, IncomingGoods.CODE},
                new String[]{nameofsell, codevalue});


    }

    public void setPriceandNameSells() {

        boolean statename = false;


        List<String> list_of_namesgain = mydb.getAllNames(Gaint_mony.TABLE_NAME);
        String[] nameofgain = new String[list_of_namesgain.size()];
        nameofgain = list_of_namesgain.toArray(nameofgain);

        List<String> allvalues = mydb.selectAllbyid(IncomingGoods.TABLE_NAME, IncomingGoods.CODE, codevalue);
        String[] arrallvalues = allvalues.get(0).toString().trim().split("-");

        String pricevalue = arrallvalues[1];


        int priceout1 = Integer.parseInt(price.getText().toString());
        int pricein1 = Integer.parseInt(pricevalue);
        int number_item_sell1 = Integer.parseInt(number_sell_item);
        int newprice1 = priceout1 - (pricein1 * number_item_sell1);

        value_of_price = String.valueOf(newprice1);


        long id = -1;

        if (nameofgain.length > 0) {
            for (int i = 0; i < nameofgain.length; i++) {

                if (nameofsell.equals(nameofgain[i].trim())) {
                    statename = true;
                }
            }
            if (statename == true) {
                //update


                List<String> allvaluesofgain = mydb.selectAllbyname_gain(Gaint_mony.TABLE_NAME, Gaint_mony.NAME, nameofsell);
                String[] arrallvaluesofgain = allvaluesofgain.get(0).toString().trim().split("-");

                int priceout = Integer.parseInt(price.getText().toString());
                int pricein = Integer.parseInt(pricevalue);
                int number_item_sell = Integer.parseInt(number_sell_item);
                int newprice = priceout - (pricein * number_item_sell);

                int oldprice = Integer.parseInt(arrallvaluesofgain[0].toString());
                int price_after_upfate = oldprice + newprice;
                value_of_price = String.valueOf(price_after_upfate);


                id = mydb.update(Gaint_mony.TABLE_NAME, new String[]{Gaint_mony.NAME, Gaint_mony.GAINT},
                        new String[]{nameofsell, value_of_price}, Gaint_mony.NAME, nameofsell);


            } else {


                id = mydb.insert(Gaint_mony.TABLE_NAME, new String[]{Gaint_mony.NAME, Gaint_mony.GAINT},
                        new String[]{nameofsell, value_of_price});
            }
        } else {

            id = mydb.insert(Gaint_mony.TABLE_NAME, new String[]{Gaint_mony.GAINT, Gaint_mony.NAME},
                    new String[]{value_of_price, nameofsell});
        }

        if (id != -1) {
        } else {

        }


    }

    private void loadSpinnerData() {
        // database handler
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        SpinnerItems.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        // Spinner Drop down elements
        List<String> lables = db.getAllLabels(IncomingGoods.TABLE_NAME);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position % 2 == 1) {
                    tv.setBackgroundColor(Color.parseColor("#5f98b5"));
                    SpinnerItems.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    SpinnerItems.setTextDirection(View.TEXT_DIRECTION_LTR);
                } else {
                    tv.setBackgroundColor(Color.parseColor("#ffffff"));
                    SpinnerItems.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    SpinnerItems.setTextDirection(View.TEXT_DIRECTION_LTR);
                }

                return view;
            }
        };
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        SpinnerItems.setAdapter(dataAdapter);
    }

    public void add_sell_clicked(View view) {

        try {


            boolean statename = false;

            List<String> list_of_namesout = mydb.getAllNames(OutgoingGoods.TABLE_NAME);
            String[] nameofouting = new String[list_of_namesout.size()];
            nameofouting = list_of_namesout.toArray(nameofouting);


            String[] spinner_texts = SpinnerItems.getSelectedItem().toString().split("-");

            String _name = spinner_texts[1].trim();
            String _price = price.getText().toString();
            String _code = spinner_texts[0].trim();
            String _number = number.getText().toString();

            number_sell_item = _number;

            nameofsell = _name;
            codevalue = _code;

            finalnumberout = String.valueOf(_number);

            long id = -1;

            if (nameofouting.length > 0) {
                for (int i = 0; i < nameofouting.length; i++) {

                    if (_name.trim().equals(nameofouting[i].trim())) {
                        statename = true;
                    }
                }

                if (statename == true) {


                    List<String> allvalues = mydb.selectAllbyname(OutgoingGoods.TABLE_NAME, OutgoingGoods.NAME, _name);
                    String[] arrallvalues = allvalues.get(0).toString().trim().split("-");

                    int newtprice = Integer.parseInt(_price);
                    int oldprice = Integer.parseInt(arrallvalues[1].toString());
                    int price_after_upfate = oldprice + newtprice;
                    String value_of_price = String.valueOf(price_after_upfate);


                    int newnumber = Integer.parseInt(_number);
                    int oldnumber = Integer.parseInt(arrallvalues[3].toString());
                    int number_after_update = newnumber + oldnumber;
                    String valueofnumber = String.valueOf(number_after_update);


                    id = mydb.update(OutgoingGoods.TABLE_NAME, new String[]
                                    {OutgoingGoods.NAME, OutgoingGoods.PRICE, OutgoingGoods.CODE, OutgoingGoods.NUMBER},
                            new String[]{_name, value_of_price, _code, valueofnumber}, OutgoingGoods.NAME, _name);


                } else {
                    id = mydb.insert(OutgoingGoods.TABLE_NAME, new String[]
                                    {OutgoingGoods.NAME, OutgoingGoods.PRICE, OutgoingGoods.CODE, OutgoingGoods.NUMBER},
                            new String[]{_name, _price, _code, _number});


                }


            } else {
                id = mydb.insert(OutgoingGoods.TABLE_NAME, new String[]
                                {OutgoingGoods.NAME, OutgoingGoods.PRICE, OutgoingGoods.CODE, OutgoingGoods.NUMBER},
                        new String[]{_name, _price, _code, _number});

            }


            if (id != -1) {
                setPriceandNameSells();
                updateIn_number();
                Toast.makeText(getApplicationContext(), "تمت الاضافه", Toast.LENGTH_SHORT).show();

                price.setText("");
                number.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "خطأ .. حاول مره اخري", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {

        }

    }
}

