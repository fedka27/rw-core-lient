package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CarsProfileResult implements Serializable {

    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<CarsAttributes> data;

    public String getStatus() {
        return status;
    }

    public List<CarsAttributes> getData() {
        return data;
    }
}
