package com.example.khale.mlabes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khale.mlabes.database.table.DatabaseHelper;

public class log_in extends AppCompatActivity {

    EditText username, password;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }

        mydb = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


    }

    public void sign_in(View view) {
        login();
    }

    public void create_clicked(View view) {
        Intent MyIntent = new Intent(this, create_account.class);
        startActivity(MyIntent);
    }

    public void login() {
        String _username = username.getText().toString();
        String _password = password.getText().toString();

        Cursor c = mydb.getUser(_username, _password);

        if (c.getCount() != 0) {
            Intent MyIntent = new Intent(this, MainActivity.class);
            startActivity(MyIntent);
        } else {
            Toast.makeText(getApplicationContext(), "المستخدم غير موجود", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1001:{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else {
                    finish();
                }
            }
        }
    }

}
