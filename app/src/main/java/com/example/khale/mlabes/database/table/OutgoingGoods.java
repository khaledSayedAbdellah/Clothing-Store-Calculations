package com.example.khale.mlabes.database.table;

public abstract class OutgoingGoods {

    public final static String TABLE_NAME="outing_goods";

    public final static String ID="id";
    public final static String NAME="name";
    public final static String PRICE="price";
    public final static String CODE="code";
    public final static String NUMBER="number";
    public final static String C_ID="c_id";

    public final static String CREATE_OUTING_GOODS_TABLE="CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" TEXT," +
            " "+PRICE+" TEXT, "+CODE+" TEXT, "+NUMBER+" TEXT,"+C_ID+" TEXT);";


}
