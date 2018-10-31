package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CarsMakes implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("car_models")
    private List<CarMake> car_models;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CarMake> getCar_models() {
        return car_models;
    }
}
