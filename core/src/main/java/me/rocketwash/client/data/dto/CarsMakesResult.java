package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

import me.rocketwash.client.data.responses.BaseResponse;


public class CarsMakesResult extends BaseResponse<List<CarsMakes>> implements Serializable {

    public CarsMakesResult(@Nullable String status, List<CarsMakes> data) {
        super(status, data);
    }
}
