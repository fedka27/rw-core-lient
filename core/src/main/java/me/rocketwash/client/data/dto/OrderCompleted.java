package me.rocketwash.client.data.dto;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import me.rocketwash.client.utils.Util;

public class OrderCompleted implements Serializable {
    private static final String TAG = OrderCompleted.class.getSimpleName();
    public static final String EXTRA_ORDER_COMPLETED = TAG + "_ORDER_COMPLETED";
    @SerializedName("id")
    private int id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("service_location_lane_id")
    private int service_location_lane_id;
    @SerializedName("time_start")
    private String time_start;
    @SerializedName("rating")
    private String rating;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("status")
    private String status;
    @SerializedName("thank_message")
    private String thank_message;
    @SerializedName("thank_the_client")
    private String thank_the_client;
    @SerializedName("comments")
    private String comments;
    @SerializedName("name")
    private String name;
    @SerializedName("time_end")
    private String time_end;
    @SerializedName("paid")
    private String paid;
    @SerializedName("car_id")
    private int car_id;
    @SerializedName("mobile")
    private boolean mobile;
    @SerializedName("full_duration")
    private int full_duration;
    @SerializedName("ordinal")
    private int ordinal;
    @SerializedName("admin_status")
    private String admin_status;
    @SerializedName("full_discount")
    private String full_discount;
    @SerializedName("notes")
    private String notes;
    @SerializedName("paid_at")
    private String paid_at;
    @SerializedName("price")
    private double price;
    @SerializedName("fully_paid")
    private boolean fully_paid;
    @SerializedName("discounted_price")
    private String discounted_price;
    @SerializedName("rounded_price")
    private String rounded_price;
    @SerializedName("result")
    private String result;
    @SerializedName("carwash_id")
    private int carwash_id;
    @SerializedName("organization_id")
    private int organization_id;
    @SerializedName("time_from")
    private String time_from;
    @SerializedName("carwash")
    private WashService carwash;
    @SerializedName("time_from_no_time_zone")
    private String time_from_no_time_zone;
    @SerializedName("time_to_no_time_zone")
    private String time_to_no_time_zone;
    @SerializedName("estimated")
    private boolean estimated;
    @SerializedName("paid_bonuses")
    private int paid_bonuses;
    @SerializedName("paid_cash")
    private int paid_cash;
    @SerializedName("paid_card")
    private int paid_card;
    @SerializedName("added_bonuses")
    private int added_bonuses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getService_location_lane_id() {
        return service_location_lane_id;
    }

    public void setService_location_lane_id(int service_location_lane_id) {
        this.service_location_lane_id = service_location_lane_id;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public Date getTime_start_Date() {
        return Util.getDatenoUTC(getTime_from_no_time_zone());
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isEstimated() {
        return estimated;
    }

    public void setEstimated(boolean estimated) {
        this.estimated = estimated;
    }

    public String getTime_start_format() {
        Date d = getTime_start_Date();
        if (d == null)
            d = Util.getDate(getTime_start());
        if (d == null)
            d = Util.getDateS1(getTime_start());
        if (d == null)
            d = new Date();

        return Util.dateToDMYHM(d);
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThank_message() {
        return thank_message;
    }

    public void setThank_message(String thank_message) {
        this.thank_message = thank_message;
    }

    public String getThank_the_client() {
        return thank_the_client;
    }

    public void setThank_the_client(String thank_the_client) {
        this.thank_the_client = thank_the_client;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    @Nullable
    public Date getCreated_at_Date() {
        return Util.getDateS(getCreated_at());
    }

    public boolean isFully_paid() {
        return fully_paid;
    }

    public void setFully_paid(boolean fully_paid) {
        this.fully_paid = fully_paid;
    }

    public int getFull_duration() {
        return full_duration;
    }

    public void setFull_duration(int full_duration) {
        this.full_duration = full_duration;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getAdmin_status() {
        return admin_status;
    }

    public void setAdmin_status(String admin_status) {
        this.admin_status = admin_status;
    }

    public String getFull_discount() {
        return full_discount;
    }

    public void setFull_discount(String full_discount) {
        this.full_discount = full_discount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPaid_at() {
        return paid_at;
    }

    public void setPaid_at(String paid_at) {
        this.paid_at = paid_at;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(String discounted_price) {
        this.discounted_price = discounted_price;
    }

    public String getRounded_price() {
        return rounded_price;
    }

    public void setRounded_price(String rounded_price) {
        this.rounded_price = rounded_price;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public WashService getCarwash() {
        return carwash;
    }

    public void setCarwash(WashService carwash) {
        this.carwash = carwash;
    }

    public int getCarwash_id() {
        return carwash_id;
    }

    public void setCarwash_id(int carwash_id) {
        this.carwash_id = carwash_id;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    public String getTime_from() {
        return time_from;
    }

    public void setTime_from(String time_from) {
        this.time_from = time_from;
    }

    public String getTime_to_no_time_zone() {
        return time_to_no_time_zone;
    }

    public void setTime_to_no_time_zone(String time_to_no_time_zone) {
        this.time_to_no_time_zone = time_to_no_time_zone;
    }

    public String getTime_from_no_time_zone() {
        return time_from_no_time_zone;
    }

    public void setTime_from_no_time_zone(String time_from_no_time_zone) {
        this.time_from_no_time_zone = time_from_no_time_zone;
    }

    public int getPaid_bonuses() {
        return paid_bonuses;
    }

    public void setPaid_bonuses(int paid_bonuses) {
        this.paid_bonuses = paid_bonuses;
    }

    public int getPaid_cash() {
        return paid_cash;
    }

    public void setPaid_cash(int paid_cash) {
        this.paid_cash = paid_cash;
    }

    public int getPaid_card() {
        return paid_card;
    }

    public void setPaid_card(int paid_card) {
        this.paid_card = paid_card;
    }

    public int getAdded_bonuses() {
        return added_bonuses;
    }

    public void setAdded_bonuses(int added_bonuses) {
        this.added_bonuses = added_bonuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCompleted that = (OrderCompleted) o;
        return id == that.id;
    }
}
