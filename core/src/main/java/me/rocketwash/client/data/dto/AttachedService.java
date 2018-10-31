package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AttachedService implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private double price;
    @SerializedName("duration")
    private int duration;
    @SerializedName("car_type_id")
    private int car_type_id;
    @SerializedName("service_id")
    private int service_id;
    @SerializedName("service_type")
    private String service_type;
    @SerializedName("count")
    private int count;
    @SerializedName("discount")
    private int discount;
    @SerializedName("type")
    private String type;
    @SerializedName("description")
    private String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public int getCar_type_id() {
        return car_type_id;
    }

    public int getService_id() {
        return service_id;
    }

    public String getService_type() {
        return service_type;
    }

    public int getCount() {
        return count;
    }

    public int getDiscount() {
        return discount;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
