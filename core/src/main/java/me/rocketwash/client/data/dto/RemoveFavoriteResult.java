package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

import me.rocketwash.client.data.responses.BaseResponse;

public class RemoveFavoriteResult extends BaseResponse<Boolean> implements Serializable {

    public RemoveFavoriteResult(@Nullable String status, Boolean data) {
        super(status, data);
    }
}
