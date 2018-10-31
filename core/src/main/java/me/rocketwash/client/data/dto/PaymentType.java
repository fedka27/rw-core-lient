package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PaymentType implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("amount")
    private double amount;
    @SerializedName("payment_type")
    private Type type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return type.getName();
    }
}

class Type implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
