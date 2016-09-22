package com.example.android.grupodot.Model;

/**
 * Created by Ingmicha on 21/09/2016.
 */

public class Account {

    private int accountId;
    private Boolean active;
    private int activeFrom;
    private int balance;
    private String category;
    private int currencyId;
    private int expiryDate;
    private int initialBalance;
    private String name;
    private int reservedBalance;
    private String type;
    private Unit unit;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(int activeFrom) {
        this.activeFrom = activeFrom;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(int expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(int initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReservedBalance() {
        return reservedBalance;
    }

    public void setReservedBalance(int reservedBalance) {
        this.reservedBalance = reservedBalance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
