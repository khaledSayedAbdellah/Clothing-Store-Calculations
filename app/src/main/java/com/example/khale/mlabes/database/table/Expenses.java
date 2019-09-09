package com.example.khale.mlabes.database.table;

public abstract class Expenses {

    public final static String TABLE_NAME="expenses";

    public final static String ID="id";
    public final static String REASON="reson";
    public final static String PRICE="price";
    public final static String DATE="date";

    public final static String CREATE_EXPENSES_TABLE=" CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+ REASON +" TEXT , " +
            " "+PRICE+" TEXT, "+DATE+" TEXT );";
}
