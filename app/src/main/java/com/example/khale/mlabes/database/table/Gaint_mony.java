package com.example.khale.mlabes.database.table;

public abstract class Gaint_mony {
    public final static String TABLE_NAME="gaint";

    public final static String ID="id";
    public final static String NAME="name";
    public final static String GAINT="gaint";

    public final static String CREATE_GAINT_TABLE=" CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+ NAME +" TEXT , " +
            " "+GAINT+" TEXT );";
}
