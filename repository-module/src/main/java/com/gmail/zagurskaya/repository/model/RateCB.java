package com.gmail.zagurskaya.repository.model;

import java.sql.Timestamp;

public class RateCB {
    private Long id;
    private Long coming;
    private Long spending;
    private Timestamp timestamp;
    private Double sum;
    private boolean isBack;

    public RateCB() {
    }

    public RateCB(Long id, Long coming, Long spending, Timestamp timestamp, Double sum, boolean isBack) {
        this.id = id;
        this.coming = coming;
        this.spending = spending;
        this.timestamp = timestamp;
        this.sum = sum;
        this.isBack = isBack;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComing() {
        return coming;
    }

    public void setComing(Long coming) {
        this.coming = coming;
    }

    public Long getSpending() {
        return spending;
    }

    public void setSpending(Long spending) {
        this.spending = spending;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public boolean getIsBack() {
        return isBack;
    }

    public void setIsBack(boolean back) {
        isBack = back;
    }

    @Override
    public String toString() {
        return "RateCB{" +
                "id=" + id +
                ", coming=" + coming +
                ", spending=" + spending +
                ", timestamp=" + timestamp +
                ", sum=" + sum +
                ", isBack=" + isBack +
                '}';
    }
}
