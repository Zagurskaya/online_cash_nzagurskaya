package com.gmail.zagurskaya.service.model;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class EndDayReportDTO {
    @NotNull
    private Long id;
    @NotNull
    private Date date;
    @NotNull
    private Long userId;
    @NotNull
    private String description;
    @NotNull
    private boolean isOpen;

    public EndDayReportDTO() {
    }

    public EndDayReportDTO(@NotNull Long id, @NotNull Date date, @NotNull Long userId, @NotNull String description, @NotNull boolean isOpen) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.description = description;
        this.isOpen = isOpen;
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

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        isOpen = isOpen;
    }

    @Override
    public String toString() {
        return "EndDayReportDTO{" +
                "id=" + id +
                ", date=" + date +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", isOpen=" + isOpen +
                '}';
    }
}
