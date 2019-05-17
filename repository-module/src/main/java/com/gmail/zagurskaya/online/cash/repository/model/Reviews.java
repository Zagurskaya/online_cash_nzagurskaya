package com.gmail.zagurskaya.online.cash.repository.model;

import java.sql.Date;

public class Reviews {

    private Long id;
    private Date date;
    private Long userId;
    private String description;
    private boolean isNotOpen;

    public Reviews() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsNotOpen() {
        return isNotOpen;
    }

    public void setIsNotOpen(boolean notOpen) {
        isNotOpen = notOpen;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", date=" + date +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", isNotOpen=" + isNotOpen +
                '}';
    }
}
