package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

import me.rocketwash.client.data.responses.BaseResponse;

public class ReserveCancelResult extends BaseResponse<Boolean> implements Serializable {

    public ReserveCancelResult(@Nullable String status, Boolean data) {
        super(status, data);
    }

    public boolean isData() {
        return getData();
    }
}
