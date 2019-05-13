package com.gmail.zagurskaya.repository.model;

public class Currency {
    private Long id;
    private String iso;
    private String name;

    public Currency() {
    }

    public Currency(Long id, String iso, String name) {
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

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", iso='" + iso + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
