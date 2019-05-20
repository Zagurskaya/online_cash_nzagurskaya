package com.gmail.zagurskaya.online.cash.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserProfileDTO {
    @NotNull
    @Size(max = 50)
    private String username;
    @NotNull
    @Size(max = 40)
    private String lastName;
    @NotNull
    @Size(max = 20)
    private String firstName;
    @NotNull
    @Size(max = 40)
    private String patronymic;
    @NotNull
    private String role;

    public UserProfileDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
