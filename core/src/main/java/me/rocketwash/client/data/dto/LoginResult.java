package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import me.rocketwash.client.data.dto.sign_in.LoginData;

@Deprecated
public class LoginResult implements Serializable {

    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private LoginData data;

    public LoginData getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        String s;
        s = "status = " + status;
        s = s + "\ndata = " + (data == null ? "null" : data.toString());
        return s;
    }
}
