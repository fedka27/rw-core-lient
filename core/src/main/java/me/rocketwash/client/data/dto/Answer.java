package me.rocketwash.client.data.dto;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Answer implements Serializable {
    @SerializedName("review_scale_id")
    private int review_scale_id;
    @SerializedName("rating")
    private int rating;
    @SerializedName("comment")
    @Nullable
    private String comment;

    /**
     * @param review_scale_id id of question
     */
    public Answer(int review_scale_id) {
        this.review_scale_id = review_scale_id;
        rating = 1;
        comment = null;
    }

    public int getReview_scale_id() {
        return review_scale_id;
    }

    /**
     * @param review_scale_id id of question
     * @deprecated use constructor
     * this field will be final
     */
    @Deprecated
    public void setReview_scale_id(int review_scale_id) {
        this.review_scale_id = review_scale_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    public void setComment(@Nullable String comment) {
        this.comment = comment;
    }

}
