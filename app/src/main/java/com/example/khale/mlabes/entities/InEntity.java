package com.example.khale.mlabes.entities;

public class InEntity   {

    private String id;
    private String name;
    private String code;
    private String price;
    private String number;

    public InEntity(String id, String name, String code, String price, String number) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.number = number;
    }

    public InEntity(){

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
