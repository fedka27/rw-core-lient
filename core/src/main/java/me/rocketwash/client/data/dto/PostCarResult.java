package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;
import me.rocketwash.client.data.responses.BaseResponse;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public class PostCarResult extends BaseResponse<CarsAttributes> implements Serializable {

    public PostCarResult(@Nullable String status, CarsAttributes data) {
        super(status, data);
    }
}
