package com.gmail.zagurskaya.online.cash.service.model;

import javax.validation.constraints.NotNull;

public class CurrencyDTO {
    @NotNull
    private Long id;
    @NotNull
    private String iso;
    @NotNull
    private String name;
    @NotNull
    private Boolean isNotActive;


    public CurrencyDTO() {
    }

    public CurrencyDTO(Long id, String iso, String name) {
        this.id = id;
        this.iso = iso;
        this.name = name;
    }

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

    public Boolean getisNotActive() {
        return isNotActive;
    }

    public void setIsNotActive(Boolean notActive) {
        isNotActive = notActive;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", iso='" + iso + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Boolean getNotActive() {
        return isNotActive;
    }

    public void setNotActive(Boolean notActive) {
        isNotActive = notActive;
    }
}
