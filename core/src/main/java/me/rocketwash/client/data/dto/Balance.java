package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Balance implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("amount")
    private int amount;

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }
}
