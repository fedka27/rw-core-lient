package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChoiceService implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private double price;

    @SerializedName("duration")
    private int duration;

    @SerializedName("description")
    private String description;

    private int type;
    private int check;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public int getType() {
        return type;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public boolean isCheck() {
        return check == 1;
    }

    public String getDescription() {
        return description;
    }

    @Deprecated
    public ChoiceService getClone() {
        ChoiceService s = new ChoiceService();
        s.id = this.id;
        s.name = this.name;
        s.price = this.price;
        s.duration = this.duration;
        s.type = this.type;
        s.check = this.check;
        s.description = description;
        return s;
    }
}
