package me.rocketwash.client.data.dto;

import java.util.List;

public class HistoryCompletableResult {
    private int totalCount;
    private float totalPaidWithBonuses;
    private float totalDiscountReceived;
    private List<OrderCompleted> list;

    public HistoryCompletableResult() {
    }

    public HistoryCompletableResult(int totalCount,
                                    float totalPaidWithBonuses,
                                    float totalDiscountReceived,
                                    List<OrderCompleted> list) {
        this.totalCount = totalCount;
        this.totalPaidWithBonuses = totalPaidWithBonuses;
        this.totalDiscountReceived = totalDiscountReceived;
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public float getTotalPaidWithBonuses() {
        return totalPaidWithBonuses;
    }

    public void setTotalPaidWithBonuses(float totalPaidWithBonuses) {
        this.totalPaidWithBonuses = totalPaidWithBonuses;
    }

    public float getTotalDiscountReceived() {
        return totalDiscountReceived;
    }

    public void setTotalDiscountReceived(float totalDiscountReceived) {
        this.totalDiscountReceived = totalDiscountReceived;
    }

    public List<OrderCompleted> getList() {
        return list;
    }

    public void setList(List<OrderCompleted> list) {
        this.list = list;
    }
}
