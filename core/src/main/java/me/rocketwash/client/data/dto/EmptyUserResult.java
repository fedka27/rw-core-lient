package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

import me.rocketwash.client.data.responses.BaseResponse;

public class EmptyUserResult extends BaseResponse<Profile> implements Serializable {

    public EmptyUserResult(@Nullable String status, Profile data) {
        super(status, data);
    }
}
