package com.gmail.zagurskaya.service.model;

import com.gmail.zagurskaya.repository.model.User;

import javax.validation.constraints.NotNull;

public class UserInfoDTO {
    @NotNull
    private Long id;

    @NotNull
    private User user;

    @NotNull
    private String lastName;

   @NotNull
    private String firstName;

    @NotNull
    private String patronymic;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
