package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostCarResult implements Serializable {

    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private CarsAttributes data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CarsAttributes getData() {
        return data;
    }

    public void setData(CarsAttributes data) {
        this.data = data;
    }
}
