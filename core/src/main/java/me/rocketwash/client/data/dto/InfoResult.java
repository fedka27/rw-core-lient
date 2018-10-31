package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

import me.rocketwash.client.data.responses.BaseResponse;

public class InfoResult extends BaseResponse<InfoResultData> implements Serializable {

    public InfoResult(@Nullable String status, InfoResultData data) {
        super(status, data);
    }

    public String getRecommendation() {
        return getData().getRecomendations();
    }

    public String getInformation() {
        return getData().getDescription();
    }
}
