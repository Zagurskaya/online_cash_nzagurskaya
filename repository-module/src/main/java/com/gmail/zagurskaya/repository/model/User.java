package com.gmail.zagurskaya.repository.model;

public class User {

    private Long id;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String email;
    private Long roleId;
    private Boolean isNotActive;

    public User() {
    }

    public User(Long id, String username, String password, String lastName, String firstName, String patronymic, String email, Long roleId, Boolean isNotActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.email = email;
        this.roleId = roleId;
        this.isNotActive = isNotActive;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsNotActive() {
        return isNotActive;
    }

    public void setIsNotActive(Boolean notActive) {
        isNotActive = notActive;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", roleId=" + roleId +
                ", isNotActive=" + isNotActive +
                '}';
    }
}
