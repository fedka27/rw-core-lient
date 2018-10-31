package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReservedCompletedResult implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<OrderCompleted> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderCompleted> getData() {
        return data;
    }

    public void setData(List<OrderCompleted> data) {
        this.data = data;
    }
}
