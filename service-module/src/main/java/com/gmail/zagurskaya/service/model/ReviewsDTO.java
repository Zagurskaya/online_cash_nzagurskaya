package com.gmail.zagurskaya.service.model;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReviewsDTO {
    @NotNull
    private Long id;
    @NotNull
    private Date date;
    @NotNull
    private UserDTO user ;
    @NotNull
    private Long roleId;
    @NotNull
    @Size(max = 200)
    private String description;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "ReviewsDTO{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", description='" + description + '\'' +
                '}';
    }
}
