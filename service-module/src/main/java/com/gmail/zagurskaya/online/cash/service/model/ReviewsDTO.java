package com.gmail.zagurskaya.online.cash.service.model;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReviewsDTO {
    @NotNull
    private Long id;
    @NotNull
    private Date date;
    @NotNull
    private Long userId;
    @NotNull
    @Size(max = 200)
    private String description;
    @NotNull
    private boolean isNotOpen;

    public ReviewsDTO() {
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
