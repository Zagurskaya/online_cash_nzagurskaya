package com.gmail.zagurskaya.online.cash.repository.model;

import java.sql.Timestamp;

public class UserOperation {
    private long id;
    private Timestamp timestamp;
    private double rate;
    private double sum;
    private long currencyId;
    private long userId;
    private long operationId;
    private String specification;
    private String fio;

    public UserOperation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOperationId() {
        return operationId;
    }

    public void setOperationId(long operationId) {
        this.operationId = operationId;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public String toString() {
        return "UserOperationRepository{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", rate=" + rate +
                ", sum=" + sum +
                ", currencyId=" + currencyId +
                ", userId=" + userId +
                ", operationId=" + operationId +
                ", specification='" + specification + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }
}
