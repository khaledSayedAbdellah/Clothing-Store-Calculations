package com.example.khale.mlabes.entities;

public class InEntity_mony {

    private String id;
    private String reson;
    private String price;
    private String date;

    public InEntity_mony(String id, String reson, String price, String date) {
        this.id = id;
        this.reson = reson;
        this.price = price;
        this.date = date;
    }

    public InEntity_mony(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
