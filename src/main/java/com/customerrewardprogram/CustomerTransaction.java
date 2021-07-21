package com.customerrewardprogram;

import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class CustomerTransaction {
    int transID;
    int amount;
    String custId;
    Date date;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
