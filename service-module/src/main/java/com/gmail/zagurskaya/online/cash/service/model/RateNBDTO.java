package com.gmail.zagurskaya.online.cash.service.model;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class RateNBDTO {
    private Long id;
    private Long currencyId;
    private Date date;
    private double sum;
    @NotNull
    private Boolean isNotActive;


    public RateNBDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Boolean getNotActive() {
        return isNotActive;
    }

    public void setNotActive(Boolean notActive) {
        isNotActive = notActive;
    }

    @Override
    public String toString() {
        return "RateNBDTO{" +
                "id=" + id +
                ", currencyId=" + currencyId +
                ", date=" + date +
                ", sum=" + sum +
                ", isNotActive=" + isNotActive +
                '}';
    }
}
