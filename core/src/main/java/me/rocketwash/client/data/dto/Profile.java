package me.rocketwash.client.data.dto;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class Profile implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("cars_attributes")
    private List<CarsAttributes> cars_attributes;

    @SerializedName("email")
    private String email;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("good_user")
    private String good_user;

    @SerializedName("session_id")
    private String session_id;

    @SerializedName("service_location_id")
    private int service_location_id;

    @SerializedName("car_type_id")
    private int car_type_id;

    @SerializedName("sex")
    private String sex;

    @SerializedName("date_of_birth")
    private String date_of_birth;

    @SerializedName("category")
    private String category;

    @SerializedName("service_location_lane_id")
    private int service_location_lane_id;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("discount")
    private int discount;

    @SerializedName("address")
    private String address;

    @SerializedName("tin")
    private String tin;

    @SerializedName("kpp")
    private String kpp;

    @SerializedName("account_number")
    private String account_number;

    @SerializedName("bic")
    private String bic;

    @SerializedName("discount_card_number")
    private String discount_card_number;

    @SerializedName("original_user_id")
    private int original_user_id;

    @SerializedName("superuser")
    private boolean superuser;

    @SerializedName("full_name")
    private String full_name;

    @SerializedName("disable_sms")
    private boolean disable_sms;

    @SerializedName("organization_id")
    private int organization_id;

    @SerializedName("user_category_id")
    private int user_category_id;

    @SerializedName("user_category_updated_at")
    private String user_category_updated_at;

    @SerializedName("job_id")
    private int job_id;

    @SerializedName("employee_status")
    private String employee_status;

    @SerializedName("latest_pin_sms_sent_at")
    private String latest_pin_sms_sent_at;

    @SerializedName("phone_verified")
    private boolean phone_verified;

    @SerializedName("tenant_user_attributes")
    @Nullable
    private UserAttributes tenant_user_attributes;

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

    public List<CarsAttributes> getCars_attributes() {
        return cars_attributes;
    }

    public void setCars_attributes(List<CarsAttributes> cars_attributes) {
        this.cars_attributes = cars_attributes;
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

    public String getGood_user() {
        return good_user;
    }

    public void setGood_user(String good_user) {
        this.good_user = good_user;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public int getService_location_id() {
        return service_location_id;
    }

    public void setService_location_id(int service_location_id) {
        this.service_location_id = service_location_id;
    }

    public int getCar_type_id() {
        return car_type_id;
    }

    public void setCar_type_id(int car_type_id) {
        this.car_type_id = car_type_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getService_location_lane_id() {
        return service_location_lane_id;
    }

    public void setService_location_lane_id(int service_location_lane_id) {
        this.service_location_lane_id = service_location_lane_id;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getDiscount_card_number() {
        return discount_card_number;
    }

    public void setDiscount_card_number(String discount_card_number) {
        this.discount_card_number = discount_card_number;
    }

    public int getOriginal_user_id() {
        return original_user_id;
    }

    public void setOriginal_user_id(int original_user_id) {
        this.original_user_id = original_user_id;
    }

    public boolean isSuperuser() {
        return superuser;
    }

    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public boolean isDisable_sms() {
        return disable_sms;
    }

    public void setDisable_sms(boolean disable_sms) {
        this.disable_sms = disable_sms;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    public int getUser_category_id() {
        return user_category_id;
    }

    public void setUser_category_id(int user_category_id) {
        this.user_category_id = user_category_id;
    }

    public String getUser_category_updated_at() {
        return user_category_updated_at;
    }

    public void setUser_category_updated_at(String user_category_updated_at) {
        this.user_category_updated_at = user_category_updated_at;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getEmployee_status() {
        return employee_status;
    }

    public void setEmployee_status(String employee_status) {
        this.employee_status = employee_status;
    }

    public String getLatest_pin_sms_sent_at() {
        return latest_pin_sms_sent_at;
    }

    public void setLatest_pin_sms_sent_at(String latest_pin_sms_sent_at) {
        this.latest_pin_sms_sent_at = latest_pin_sms_sent_at;
    }

    public boolean isPhone_verified() {
        return phone_verified;
    }

    public void setPhone_verified(boolean phone_verified) {
        this.phone_verified = phone_verified;
    }

    public UserAttributes getTenant_user_attributes() {
        if (tenant_user_attributes == null) tenant_user_attributes = new UserAttributes();
        return tenant_user_attributes;
    }

    @Override
    public String toString() {
        return "";
    }

    public void setTenant_user_attributes(@Nullable UserAttributes tenant_user_attributes) {
        this.tenant_user_attributes = tenant_user_attributes;
    }

    //todo remove this
    public void var_dump()
    {
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            try
            {
                System.out.println(fields[i].getName() + " - " + fields[i].get(this));
            }
            catch (java.lang.IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String getCustomerKey() {
        return "Customer-Key" + getId();
    }
}
