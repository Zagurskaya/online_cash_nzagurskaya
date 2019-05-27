package com.gmail.zagurskaya.repository.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET isnotactive = 1 WHERE id=?")
@Where(clause = "isnotactive = 0")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role ;

    @Column(name = "isnotactive")
    private Boolean isNotActive;

    @OneToMany(mappedBy = "reviews", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Reviews> reviews = new HashSet<>();

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        User user = (User) o;
//        return Objects.equals(id, user.id) &&
//                Objects.equals(username, user.username);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(id, username);
//    }

    @Override
    public String toString() {
        return "User{" +
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
