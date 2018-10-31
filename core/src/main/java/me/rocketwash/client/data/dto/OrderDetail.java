package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderDetail implements Serializable {
    //todo not complete model

    @SerializedName("id")
    private int id;
    @SerializedName("time_start")
    private String time_start;
    @SerializedName("time_end")
    private String time_end;
    @SerializedName("status")
    private String status;
    @SerializedName("mobile")
    private boolean mobile;
    @SerializedName("ordinal")
    private int ordinal;
    @SerializedName("full_duration")
    private int full_duration;
    @SerializedName("fully_paid")
    private boolean fully_paid;
    @SerializedName("attached_services ")
    private List<AttachedService> attached_services = null;
    //todo unknown model
    @SerializedName("attached_products ")
    private List<Object> attached_products = null;
    @SerializedName("reservation_payments ")
    private List<PaymentType> reservation_payments = null;
    @SerializedName("added_bonuses")
    private int added_bonuses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getFull_duration() {
        return full_duration;
    }

    public void setFull_duration(int full_duration) {
        this.full_duration = full_duration;
    }

    public boolean isFully_paid() {
        return fully_paid;
    }

    public void setFully_paid(boolean fully_paid) {
        this.fully_paid = fully_paid;
    }

    public List<AttachedService> getAttached_services() {
        return attached_services;
    }

    public void setAttached_services(List<AttachedService> attached_services) {
        this.attached_services = attached_services;
    }

    public List<Object> getAttached_products() {
        return attached_products;
    }

    public void setAttached_products(List<Object> attached_products) {
        this.attached_products = attached_products;
    }

    public List<PaymentType> getReservation_payments() {
        return reservation_payments;
    }

    public void setReservation_payments(List<PaymentType> reservation_payments) {
        this.reservation_payments = reservation_payments;
    }

    public int getAdded_bonuses() {
        return added_bonuses;
    }

    public void setAdded_bonuses(int added_bonuses) {
        this.added_bonuses = added_bonuses;
    }
}
