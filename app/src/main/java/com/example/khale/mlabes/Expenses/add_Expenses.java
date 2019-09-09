package com.example.khale.mlabes.Expenses;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khale.mlabes.R;
import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.database.table.Expenses;

import java.util.Calendar;

public class add_Expenses extends AppCompatActivity {

    private TextView mytext;
    private Button mybtn;
    EditText reson,price;
    DatabaseHelper mydb;
    String date;

    Calendar c;
    DatePickerDialog.OnDateSetListener dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__expenses);

        mydb=new DatabaseHelper(this);

        reson = findViewById(R.id.reson_of_Expenses);
        price = findViewById(R.id.value_of_Expenses);


        mytext=findViewById(R.id.text_date);
        mybtn=findViewById(R.id.chose_date);

        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c=Calendar.getInstance();
                int day=c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);

                DatePickerDialog dialog=new DatePickerDialog(add_Expenses.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dpd,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        dpd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month+=1;
                mytext.setText(day+"/"+month+"/"+year);
                date=String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);

            }
        };
    }

    public void btn_expenses_add_clicked(View view) {

        try {


            String _reson = reson.getText().toString();
            String _price = price.getText().toString();

            long id=mydb.insert(Expenses.TABLE_NAME,new String[]
                            {Expenses.REASON,Expenses.PRICE, Expenses.DATE},
                    new String[]{_reson,_price,date});

            if(id !=-1){
                Toast.makeText(getApplicationContext(),"تمت الاضافه",Toast.LENGTH_SHORT).show();

                reson.setText("");
                price.setText("");

            }else {
                Toast.makeText(getApplicationContext(),"خطأ .. حاول مره اخري",Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){

        }

    }
}
