package com.zby.pojo.entity;

public class Account {
    private String cardNum;
    private String name;
    private String password;
    private double money;
    private double limitMoney;


    public Account() {
    }

    public Account(String cardNum, String name, String password, double money, double limitMoney) {
        this.cardNum = cardNum;
        this.name = name;
        this.password = password;
        this.money = money;
        this.limitMoney = limitMoney;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(double limitMoney) {
        this.limitMoney = limitMoney;
    }

    @Override
    public String toString() {
        return "Account{" +
                "cardNum='" + cardNum + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", limitMoney=" + limitMoney +
                '}';
    }
}
