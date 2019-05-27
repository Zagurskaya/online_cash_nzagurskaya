package com.gmail.zagurskaya.repository.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reviews")
@SQLDelete(sql = "UPDATE reviews SET isnotopen = 1 WHERE id=?")
@Where(clause = "isnotopen = 0")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user ;

    @Column(name = "description")
    private String description;

    @Column(name = "isnotopen")
    private boolean isNotOpen;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                ", description='" + description + '\'' +
                ", isNotOpen=" + isNotOpen +
                '}';
    }
}
