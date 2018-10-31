package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderStats implements Serializable {
    @SerializedName("total_count")
    private int total_count;
    @SerializedName("total_paid_with_bonuses")
    private float total_paid_with_bonuses;
    @SerializedName("total_discount_received")
    private float total_discount_received;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public float getTotal_paid_with_bonuses() {
        return total_paid_with_bonuses;
    }

    public void setTotal_paid_with_bonuses(float total_paid_with_bonuses) {
        this.total_paid_with_bonuses = total_paid_with_bonuses;
    }

    public float getTotal_discount_received() {
        return total_discount_received;
    }

    public void setTotal_discount_received(float total_discount_received) {
        this.total_discount_received = total_discount_received;
    }
}
