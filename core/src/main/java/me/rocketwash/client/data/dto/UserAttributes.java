package me.rocketwash.client.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserAttributes implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("discount ")
    private int discount = 0;
    @SerializedName("bonuses_percentage ")
    private int bonuses_percentage = 0;
    @SerializedName("disable_bonuses")
    private boolean disable_bonuses;
    @SerializedName("financial_center_user_money_balance")
    private Balance financial_center_user_money_balance;
    @SerializedName("financial_center_user_bonuses_balance")
    private Balance financial_center_user_bonuses_balance;

    public UserAttributes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getBonuses_percentage() {
        return bonuses_percentage;
    }

    public void setBonuses_percentage(int bonuses_percentage) {
        this.bonuses_percentage = bonuses_percentage;
    }

    public boolean isDisable_bonuses() {
        return disable_bonuses;
    }

    public void setDisable_bonuses(boolean disable_bonuses) {
        this.disable_bonuses = disable_bonuses;
    }

    public Balance getFinancial_center_user_money_balance() {
        if (financial_center_user_bonuses_balance == null)
            financial_center_user_bonuses_balance = new Balance();
        return financial_center_user_money_balance;
    }

    public void setFinancial_center_user_money_balance(Balance financial_center_user_money_balance) {
        if (financial_center_user_money_balance == null)
            financial_center_user_money_balance = new Balance();
        this.financial_center_user_money_balance = financial_center_user_money_balance;
    }

    public Balance getFinancial_center_user_bonuses_balance() {
        return financial_center_user_bonuses_balance;
    }

    public void setFinancial_center_user_bonuses_balance(Balance financial_center_user_bonuses_balance) {
        this.financial_center_user_bonuses_balance = financial_center_user_bonuses_balance;
    }
}
