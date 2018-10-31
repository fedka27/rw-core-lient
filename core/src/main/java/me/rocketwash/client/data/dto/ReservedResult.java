package me.rocketwash.client.data.dto;


import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

import me.rocketwash.client.data.responses.BaseResponse;

public class ReservedResult extends BaseResponse<List<Reservation>> implements Serializable {

    public ReservedResult(@Nullable String status, List<Reservation> data) {
        super(status, data);
    }
}
