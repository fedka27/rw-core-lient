package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

import me.rocketwash.client.data.responses.BaseResponse;

public class ReservationResult extends BaseResponse<Reservation> implements Serializable {

    public ReservationResult(@Nullable String status, Reservation data) {
        super(status, data);
    }
}
