package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

import me.rocketwash.client.data.responses.BaseResponse;

public class AnswersResult extends BaseResponse<Object> implements Serializable {
    //todo Answers data result ?
    private Object data;

    public AnswersResult(@Nullable String status, Object data) {
        super(status, data);
    }
}
