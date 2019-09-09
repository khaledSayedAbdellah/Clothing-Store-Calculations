package com.example.khale.mlabes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.database.table.User;

public class create_account extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText name,password,phone,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mydb = new DatabaseHelper(this);

        name = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);

    }

    public boolean solveInputExceptions(){

        boolean value=false;

        if(!(name.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")
                || phone.getText().toString().trim().equals("")||email.getText().toString().trim().equals(""))){

            if(email.getText().toString().contains("@") && email.getText().toString().length()>=7){
                if(phone.getText().toString().length()==11 &&
                        phone.getText().toString().substring(0,2).equals("01")){
                    value=true;
                }

            }

        }
        return value;
    }

    public void create_click_to_login(View view) {
        boolean id = solveInputExceptions();
        if(id != false){
            addUser();
        }else {
            Toast.makeText(getApplicationContext(),"يرجي ادخال بيانات صحيحه",Toast.LENGTH_SHORT).show();
        }

    }

    public void addUser(){
        String _name =name.getText().toString();
        String _password =password.getText().toString();
        String _phone =phone.getText().toString();
        String _email =email.getText().toString();

        long id= mydb.insert(User.TABLE_NAME,
                new String[]{User.NAME,User.PASSWORD,User.MOBILE_NUMBER,User.EMAIL},
                new String[]{_name,_password,_phone,_email});
        if (id !=-1){
            Toast.makeText(getApplicationContext(),"اكتمل التسجيل",Toast.LENGTH_SHORT).show();
            Intent MyIntent=new Intent(this,log_in.class);
            startActivity(MyIntent);
        }else {
            Toast.makeText(getApplicationContext(),"خطأ في التسجيل",Toast.LENGTH_LONG).show();

        }
    }
}
