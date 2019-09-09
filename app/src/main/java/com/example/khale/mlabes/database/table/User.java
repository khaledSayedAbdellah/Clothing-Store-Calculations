package com.example.khale.mlabes.database.table;

public abstract class User {

    public final static String TABLE_NAME="user";

    public final static String ID="id";
    public final static String NAME="name";
    public final static String PASSWORD="password";
    public final static String MOBILE_NUMBER="mobile_number";
    public final static String EMAIL="email";

    public final static String CREATE_USER_TABLE="CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" TEXT, "+PASSWORD+" TEXT, "+MOBILE_NUMBER+" TEXT, "+EMAIL+" TEXT);";





}
