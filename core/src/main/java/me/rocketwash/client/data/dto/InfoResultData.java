package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InfoResultData implements Serializable {
    @SerializedName("description")
    private String description;

    @SerializedName("recomendations")
    private String recomendations;

    public String getDescription() {
        return description;
    }

    public String getRecomendations() {
        return recomendations;
    }
}