package com.example.khale.mlabes.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khale.mlabes.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(User.CREATE_USER_TABLE);
        db.execSQL(IncomingGoods.CREATE_INCOMING_GOODS_TABLE);
        db.execSQL(OutgoingGoods.CREATE_OUTING_GOODS_TABLE);
        db.execSQL(Expenses.CREATE_EXPENSES_TABLE);
        db.execSQL(Gaint_mony.CREATE_GAINT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + IncomingGoods.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OutgoingGoods.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Expenses.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Gaint_mony.TABLE_NAME);

        onCreate(db);
    }

    public DatabaseHelper(Context context) {
        super(context, "myProject.db", null, 33);
    }


    public long insert(String tableName, String[] cName, String[] cValue) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        for (int i = 0; i < cName.length; i++) {
            c.put(cName[i], cValue[i]);
        }
        long id = db.insert(tableName, null, c);
        return id;
    }

    public long update(String tableName, String[] cName, String[] cValue, String colNameToUpdate,
                                                                    String colValueToUpdate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        for (int i = 0; i < cName.length; i++) {
            c.put(cName[i], cValue[i]);
        }
        long number = db.update(tableName , c , colNameToUpdate + " = ? " ,
                new String[]{colValueToUpdate});
        return number;
    }


    public Cursor selectAll(String tablename) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(" select * from " + tablename, null);
        return c;
    }

    public List<String> selectAllbyid(String tablename,String c_id,String v_id) {

        List<String> names = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(" select * from " + tablename + " where " +
                c_id + " = ? ",new String[]{v_id});

        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(1)+"-"+cursor.getString(2)+"-"+cursor.getString(3)+"-"+cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return names;
    }

    public long delete(String tableName, String colName, String colValue) {

        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(tableName, colName + " = ?", new String[]{colValue});
        return id;
    }

    public Cursor getUser(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + User.TABLE_NAME + " where " +
                User.NAME + " = ? AND " + User.PASSWORD + " = ?", new String[]{username, password});
        return c;
    }

    public List<String> getAllLabels(String tablename){
        List<String> names = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + tablename;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(3)+"   -   "+cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return names;
    }

    public List<String> getAllPrices(String Tablename){
        List<String> names = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + Tablename;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

         if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return names;
    }

    public List<String> getPrices(String Tablename){
        List<String> names = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + Tablename;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return names;
    }

    public List<String> getAllNames(String Tablename){
        List<String> names = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + Tablename;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return names;
    }

    public List<String> selectAllbyname(String tablename,String c_name,String v_name) {

        List<String> names = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(" select * from " + tablename + " where " +
                c_name + " = ? ",new String[]{v_name});

        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(1)+"-"+cursor.getString(2)+"-"+cursor.getString(3)+"-"+cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return names;
    }



    public List<String> selectAllbyname_gain(String tablename,String c_name,String v_name) {

        List<String> names = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(" select * from " + tablename + " where " +
                c_name + " = ? ",new String[]{v_name});

        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return names;
    }

    public List<String> selectAllby2value(String tablename,String[] c_name,String[] v_name) {

        List<String> names = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(" select * from " + tablename + " where " +
                c_name[0] + " = ? AND " + c_name[1] + " = ?", new String[]{v_name[0], v_name[1]});

        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(1)+"-"+cursor.getString(2)+"-"+cursor.getString(3)+"-"+cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return names;
    }

    public long updateby2value(String tableName, String[] cName, String[] cValue, String[] colNameToUpdate,
                       String[] colValueToUpdate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        for (int i = 0; i < cName.length; i++) {
            c.put(cName[i], cValue[i]);
        }
        long number = db.update(tableName , c ,colNameToUpdate[0] + " = ? AND " + colNameToUpdate[1] + " = ?",
                new String[]{colValueToUpdate[0], colValueToUpdate[1]});
        return number;
    }

    public void deleteAllTable(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ OutgoingGoods.TABLE_NAME);
        db.execSQL("delete from "+ Expenses.TABLE_NAME);
        db.execSQL("delete from "+ Gaint_mony.TABLE_NAME);
    }

    public static String DB_FILEPATH = "/data/data/{package_name}/databases/database.db";

    /**
     * Copies the database file at the specified location over the current
     * internal application database.
     * */
    public boolean importDatabase(String dbPath) throws IOException {

        // Close the SQLiteOpenHelper so it will commit the created empty
        // database to internal storage.
        close();
        File newDb = new File(dbPath);
        File oldDb = new File(DB_FILEPATH);
        if (newDb.exists()) {
            FileUtils.copyFile(new FileInputStream(newDb), new FileOutputStream(oldDb));
            // Access the copied database so SQLiteHelper will cache it and mark
            // it as created.
            getWritableDatabase().close();
            return true;
        }
        return false;
    }







}















