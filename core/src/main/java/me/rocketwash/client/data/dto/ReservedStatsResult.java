package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

import me.rocketwash.client.data.responses.BaseResponse;

public class ReservedStatsResult extends BaseResponse<OrderStats> implements Serializable {

    public ReservedStatsResult(@Nullable String status, OrderStats data) {
        super(status, data);
    }
}
