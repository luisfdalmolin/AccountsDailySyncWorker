package com.luisfdalmolin.accountsdailysyncworker.domain.models;

public class Account {

    private String agency;
    private String number;
    private double balance;
    private String status;
    private boolean isUpdated = false;

    public Account(String agency, String number, double balance, String status) {
        this.agency = agency;
        this.number = number;
        this.balance = balance;
        this.status = status;
    }

    public String getAgency() {
        return agency;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }
}
