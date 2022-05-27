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

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }
}
