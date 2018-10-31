package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

import me.rocketwash.client.data.responses.BaseResponse;

public class AvailableTimesResult extends BaseResponse<List<String>> implements Serializable {

    public AvailableTimesResult(@Nullable String status, List<String> data) {
        super(status, data);
    }
}
