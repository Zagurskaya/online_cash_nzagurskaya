package com.gmail.zagurskaya.online.cash.service.model;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class RateCBDTO {
    private Long id;
    @NotNull
    private Long coming;
    @NotNull
    private Long spending;
    @NotNull
    private Timestamp timestamp;
    @NotNull
    private Double sum;
    @NotNull
    private boolean isBack;
    @NotNull
    private Boolean isNotActive;


    public RateCBDTO() {
    }

    public RateCBDTO(Long id, Long coming, Long spending, Timestamp timestamp, Double sum, boolean isBack) {
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

    public boolean isBack() {
        return isBack;
    }

    public void setBack(boolean back) {
        isBack = back;
    }

    public Boolean getIsNotActive() {
        return isNotActive;
    }

    public void setIsNotActive(Boolean notActive) {
        isNotActive = notActive;
    }

    @Override
    public String toString() {
        return "RateCBDTO{" +
                "id=" + id +
                ", coming=" + coming +
                ", spending=" + spending +
                ", timestamp=" + timestamp +
                ", sum=" + sum +
                ", isBack=" + isBack +
                ", isNotActive=" + isNotActive +
                '}';
    }
}
