package me.rocketwash.client.data.dto.sign_in;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import me.rocketwash.client.data.dto.Profile;
import me.rocketwash.client.data.dto.base.BaseData;

public class LoginData extends BaseData implements Serializable {

    @SerializedName("profile")
    private Profile profile;
    @SerializedName("session_id")
    private String session_id;
    @SerializedName("price_range")
    private List<Integer> price_range;
    //todo add serializable field

    public Profile getProfile() {
        return profile;
    }

    public String getSession_id() {
        return session_id;
    }

    public List<Integer> getPrice_range() {
        return price_range;
    }

    @Override
    public String toString() {
        String s = "";
        s = "{\nsession_id = " + session_id;
        s = s + "\nresult = " + getResult();
        s = s + "\nprofile = " + (profile == null ? "null" : profile.toString());
        s = s + "\n}\n";
        return s;
    }
}
