package com.example.android.grupodot.Model;

/**
 * Created by Ingmicha on 22/09/2016.
 */

public class Unit {

    private int currencyId;
    private int id;
    private int mantissa;
    private String name;
    private int relation;

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMantissa() {
        return mantissa;
    }

    public void setMantissa(int mantissa) {
        this.mantissa = mantissa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }
}
