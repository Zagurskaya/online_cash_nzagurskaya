package com.gmail.zagurskaya.online.cash.repository.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;


@Entity
@Table(name = "ratecb")
@SQLDelete(sql = "UPDATE ratecb SET isNotActive = 1 WHERE id=?")
@Where(clause = "isNotActive = 0")
public class RateCB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "coming")
    private Long coming;

    @Column(name = "spending")
    private Long spending;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "sum")
    private Double sum;

    @Column(name = "isBack")
    private boolean isBack;

    @Column(name = "isNotActive")
    private Boolean isNotActive;

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

    public Boolean getIsNotActive() {
        return isNotActive;
    }

    public void setIsNotActive(Boolean notActive) {
        isNotActive = notActive;
    }
}
