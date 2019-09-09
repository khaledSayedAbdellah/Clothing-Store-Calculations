package com.example.khale.mlabes.entities;

public class InEntity_gain {

    private String id;
    private String name;
    private String price;

    public InEntity_gain(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public InEntity_gain(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
