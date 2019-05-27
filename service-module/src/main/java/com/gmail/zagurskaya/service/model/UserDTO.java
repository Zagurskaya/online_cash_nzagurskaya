package com.gmail.zagurskaya.service.model;

import com.gmail.zagurskaya.repository.model.Reviews;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    @NotNull
    private Long id;
    @NotNull
    @Size(max = 50)
    private String username;
    @NotNull
    @Size(max = 100)
    private String password;
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
    private RoleDTO role ;
    @NotNull
    private Boolean isNotActive;

    private Set<Reviews> reviews = new HashSet<>();

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public Boolean getIsNotActive() {
        return isNotActive;
    }

    public void setIsNotActive(Boolean notActive) {
        isNotActive = notActive;
    }

    public Set<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Reviews> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", role=" + role +
                ", isNotActive=" + isNotActive +
                ", reviews=" + reviews +
                '}';
    }
}
