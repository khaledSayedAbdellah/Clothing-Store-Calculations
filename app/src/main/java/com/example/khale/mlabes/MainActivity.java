package com.example.khale.mlabes;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.khale.mlabes.Expenses.add_Expenses;
import com.example.khale.mlabes.database.table.DatabaseHelper;
import com.example.khale.mlabes.sell.sell_select;
import com.example.khale.mlabes.stock.stock_select;
import com.example.khale.mlabes.tabs.Tab1;
import com.example.khale.mlabes.tabs.Tab2;
import com.example.khale.mlabes.tabs.Tab3;
import com.example.khale.mlabes.tabs.Tab4;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.FileWriter;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper mydb;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mydb = new DatabaseHelper(this);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_stock) {
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------
    //--------------------------new functions created----------------------------------




    //---------------------------------------------------------------
    //---------------------------------------------------------------




    public void reset_clicked(MenuItem item) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Delete All")
                .setMessage("هل انت متأكد ؟؟ ... سيتم حذف جميع البيانات ! ")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        mydb.deleteAllTable();
                        Toast.makeText(getApplicationContext(),"تم حذف البيانات ",Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_delete)
                .show();

    }

    public void load_clicked(MenuItem item) {

        new MaterialFilePicker()
                .withActivity(MainActivity.this)
                .withRequestCode(1000)
                .withFilter(Pattern.compile(".*\\.csv$"))
                .withHiddenFiles(true) // Show hidden files and folders
                .start();

        Toast.makeText(getApplicationContext(),"اختبار التحميل  ",Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
        }
    }

    public void save_clicked(MenuItem item) {



        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvname.csv");
        try
        {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM contacts",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
        Toast.makeText(getApplicationContext(),"تم الحفظ ",Toast.LENGTH_LONG).show();

    }

    public void all_payed_clicked(MenuItem item) {
        Intent MyIntent=new Intent(this,add_Expenses.class);
        startActivity(MyIntent);
    }

    public void sett_sell_clicked(MenuItem item) {

        Intent MyIntent=new Intent(this,sell_select.class);
        startActivity(MyIntent);
    }

    public void sett_stock__clicked(MenuItem item) {

        Intent MyIntent=new Intent(this,stock_select.class);
        startActivity(MyIntent);
    }



    public void btn_stock_clicked(View view) {
        Intent MyIntent =new Intent(this, Tab3.class);
        startActivity(MyIntent);
    }

    public void btn_sell_clicked(View view) {

        Intent MyIntent =new Intent(this, Tab2.class);
        startActivity(MyIntent);
    }

    public void btn_gaint_clicked(View view) {

        Intent MyIntent =new Intent(this, Tab1.class);
        startActivity(MyIntent);
    }

    public void btn_payed_clicked(View view) {

        Intent MyIntent =new Intent(this, Tab4.class);
        startActivity(MyIntent);
    }




    //---------------------------------------------------------------


    //--------------------------ends all functions-------------------------------------
    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------

}
