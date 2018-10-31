package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

import me.rocketwash.client.data.responses.BaseResponse;

public class ChoiseServiceResult extends BaseResponse<List<ChoiceService>> implements Serializable {
    public ChoiseServiceResult(@Nullable String status, List<ChoiceService> data) {
        super(status, data);
    }
}
