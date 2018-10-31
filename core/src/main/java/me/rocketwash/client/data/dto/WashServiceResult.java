package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

import me.rocketwash.client.data.responses.BaseResponse;

public class WashServiceResult extends BaseResponse<List<WashService>> implements Serializable {

    public WashServiceResult(@Nullable String status, List<WashService> data) {
        super(status, data);
    }

}
