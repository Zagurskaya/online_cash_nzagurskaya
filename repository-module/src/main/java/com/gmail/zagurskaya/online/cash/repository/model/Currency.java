package com.gmail.zagurskaya.online.cash.repository.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "currencies")
@SQLDelete(sql = "UPDATE currencies SET isNotActive = 1 WHERE id=?")
@Where(clause = "isNotActive = 0")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "iso")
    private String iso;

    @Column(name = "name")
    private String name;

    @Column(name = "isNotActive")
    private Boolean isNotActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNotActive() {
        return isNotActive;
    }

    public void setNotActive(Boolean notActive) {
        isNotActive = notActive;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", iso='" + iso + '\'' +
                ", name='" + name + '\'' +
                ", isNotActive=" + isNotActive +
                '}';
    }
}
