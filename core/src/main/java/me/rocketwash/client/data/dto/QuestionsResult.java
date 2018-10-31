package me.rocketwash.client.data.dto;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

import me.rocketwash.client.data.responses.BaseResponse;

public class QuestionsResult extends BaseResponse<List<Question>> implements Serializable {

    public QuestionsResult(@Nullable String status, List<Question> data) {
        super(status, data);
    }
}
