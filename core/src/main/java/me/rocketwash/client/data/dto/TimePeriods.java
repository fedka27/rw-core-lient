package me.rocketwash.client.data.dto;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import me.rocketwash.client.utils.Util;

public class TimePeriods implements Serializable {

    @SerializedName("time_from")
    private String time_from;

    @SerializedName("time_from_no_time_zone")
    private String time_from_no_time_zone;

    @SerializedName("price")
    private int price;

    private int selected = 0;

    private Date date;

    private Date date_td;

    public String getTime_from() {
        return time_from;
    }

    public void setTime_from(String time_from) {
        this.time_from = time_from;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        if (date == null)
            date = Util.getDateS1(time_from);

        return date;
    }

    public String getTimeStr() {
        if (TextUtils.isEmpty(time_from_no_time_zone))
            return "";

        return time_from_no_time_zone.substring(time_from_no_time_zone.indexOf("T") + 1).replace(":00+00:00", "");
    }

    public void setToday(Date date_td) {
        this.date_td = date_td;
    }

    public boolean isToday() {
        Calendar c = Calendar.getInstance();
        c.setTime(date_td);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int m = c.get(Calendar.MONTH);
        int y = c.get(Calendar.YEAR);
        c.setTime(getDate());
        int d1 = c.get(Calendar.DAY_OF_MONTH);
        int m1 = c.get(Calendar.MONTH);
        int y1 = c.get(Calendar.YEAR);
        return d == d1 && m == m1 && y == y1;
    }

    public boolean isTomorrow() {
        Calendar c = Calendar.getInstance();
        c.setTime(date_td);
        //c.roll(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, 1);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int m = c.get(Calendar.MONTH);
        int y = c.get(Calendar.YEAR);
        c.setTime(getDate());
        int d1 = c.get(Calendar.DAY_OF_MONTH);
        int m1 = c.get(Calendar.MONTH);
        int y1 = c.get(Calendar.YEAR);
        return d == d1 && m == m1 && y == y1;
    }

    public TimePeriods() {

    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getTime_from_no_time_zone() {
        return time_from_no_time_zone;
    }

    public void setTime_from_no_time_zone(String time_from_no_time_zone) {
        this.time_from_no_time_zone = time_from_no_time_zone;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
