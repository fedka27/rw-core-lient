package me.rocketwash.client.data.dto;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("allow_comment")
    private boolean allow_comment;
    //organization_id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        name = in.readString();
        allow_comment = in.readByte() == 1;
    }

    public boolean isAllow_comment() {
        return allow_comment;
    }
}
