package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CarsAttributes implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("tag")
    private String tag;

    @SerializedName("car_make_id")
    private int car_make_id;

    @SerializedName("car_model_id")
    private int car_model_id;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("contractor_id")
    private int contractor_id;

    @SerializedName("year")
    private int year;

    @SerializedName("service_location_id")
    private int service_location_id;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("organization_id")
    private int organization_id;

    private String brandName;
    private String modelName;

    private int type;

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCar_make_id() {
        return car_make_id;
    }

    public void setCar_make_id(int car_make_id) {
        this.car_make_id = car_make_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getContractor_id() {
        return contractor_id;
    }

    public int getYear() {
        return year;
    }

    public int getService_location_id() {
        return service_location_id;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public int getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(int car_model_id) {
        this.car_model_id = car_model_id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String s;
        s = "{\n id=" + id;
        s = s + "\n tag=" + tag;
        s = s + "\n car_make_id=" + car_make_id;
        s = s + "\n car_model_id=" + car_model_id;
        s = s + "\n created_at=" + created_at;
        s = s + "\n updated_at=" + updated_at;
        s = s + "\n contractor_id=" + contractor_id;
        s = s + "\n year=" + year;
        s = s + "\n service_location_id=" + service_location_id;
        s = s + "\n deleted_at=" + deleted_at;
        s = s + "\n organization_id=" + organization_id;
        s = s + "\n}";
        return s;
    }

    @Deprecated
    public CarsAttributes copy() {
        CarsAttributes c = new CarsAttributes();
        c.id = id;
        c.tag = tag;
        c.car_make_id = car_make_id;
        c.car_model_id = car_model_id;
        c.created_at = created_at;
        c.updated_at = updated_at;
        c.contractor_id = contractor_id;
        c.year = year;
        c.service_location_id = service_location_id;
        c.deleted_at = deleted_at;
        c.organization_id = organization_id;
        c.brandName = brandName;
        c.modelName = modelName;
        return c;
    }


}
