package me.rocketwash.client.data.dto;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//todo move to core
public class WashService implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("organization_id")
    private int organization_id;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("active")
    private boolean active;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("unified_rating")
    private float unified_rating;

    @SerializedName("name")
    private String name;

    @SerializedName("time_zone")
    private String time_zone;

    @SerializedName("plan_id")
    private int plan_id;

    @SerializedName("agreement_number")
    private String agreement_number;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("top_order")
    private int top_order;

    @SerializedName("mobile_stub_text")
    private String mobile_stub_text;

    @SerializedName("sms_price")
    private String sms_price;

    @SerializedName("online_reservation_price")
    private String online_reservation_price;

    @SerializedName("distance")
    private float distance;

    @SerializedName("bearing")
    private int bearing;

    @SerializedName("service_name")
    private String service_name;

    @SerializedName("time_periods")
    private List<TimePeriods> time_periods;

    @SerializedName("favorite_id")
    private int favorite_id;

    @SerializedName("tenant_user_attributes")
    @Nullable
    private UserAttributes tenant_user_attributes;

    private int type;
    private Date rDate;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
        //return active == null ? false : active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getAgreement_number() {
        return agreement_number;
    }

    public void setAgreement_number(String agreement_number) {
        this.agreement_number = agreement_number;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public boolean isTop_order() {
        return top_order == 1;
    }

    public void setTop_order(boolean top_order) {
        this.top_order = top_order ? 1 : 0;
    }

    public String getMobile_stub_text() {
        return mobile_stub_text;
    }

    public void setMobile_stub_text(String mobile_stub_text) {
        this.mobile_stub_text = mobile_stub_text;
    }

    public String getSms_price() {
        return sms_price;
    }

    public void setSms_price(String sms_price) {
        this.sms_price = sms_price;
    }

    public String getOnline_reservation_price() {
        return online_reservation_price;
    }

    public void setOnline_reservation_price(String online_reservation_price) {
        this.online_reservation_price = online_reservation_price;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getBearing() {
        return bearing;
    }

    public void setBearing(int bearing) {
        this.bearing = bearing;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public List<TimePeriods> getTime_periods() {
        return time_periods;
    }

    public void setTime_periods(List<TimePeriods> time_periods) {
        this.time_periods = time_periods;
    }

    public float getUnified_rating() {
        return unified_rating;
    }

    public void setUnified_rating(float unified_rating) {
        this.unified_rating = unified_rating;
    }

    public UserAttributes getTenant_user_attributes() {
        if (tenant_user_attributes == null) tenant_user_attributes = new UserAttributes();
        return tenant_user_attributes;
    }

    public void setTenant_user_attributes(@Nullable UserAttributes tenant_user_attributes) {
        this.tenant_user_attributes = tenant_user_attributes;
    }


    public WashService() {

    }

    public void var_dump() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                System.out.println(fields[i].getName() + " - " + fields[i].get(this));
            } catch (java.lang.IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public int getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(int favorite_id) {
        this.favorite_id = favorite_id;
    }

    public WashService getClone() {
        WashService a = new WashService();

        a.id = id;
        a.organization_id = organization_id;
        a.address = address;
        a.phone = phone;
        a.email = email;
        a.active = active;
        a.created_at = created_at;
        a.updated_at = updated_at;
        a.latitude = latitude;
        a.longitude = longitude;
        a.unified_rating = unified_rating;
        a.name = name;
        a.time_zone = time_zone;
        a.plan_id = plan_id;
        a.agreement_number = agreement_number;
        a.deleted_at = deleted_at;
        a.setTop_order(isTop_order());
        a.mobile_stub_text = mobile_stub_text;
        a.sms_price = sms_price;
        a.online_reservation_price = online_reservation_price;
        a.distance = distance;
        a.bearing = bearing;
        a.service_name = service_name;
        a.tenant_user_attributes = tenant_user_attributes;
        if (time_periods != null) {
            a.time_periods = new ArrayList<>();
            for (int i = 0; i < time_periods.size(); i++) {
                TimePeriods t = new TimePeriods();
                t.setPrice(time_periods.get(i).getPrice());
                t.setTime_from(time_periods.get(i).getTime_from());
                t.setTime_from_no_time_zone(time_periods.get(i).getTime_from_no_time_zone());
                a.time_periods.add(t);
            }
        }
        a.favorite_id = favorite_id;
        return a;
    }
}
