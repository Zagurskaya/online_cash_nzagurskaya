package com.gmail.zagurskaya.online.cash.repository;

import com.gmail.zagurskaya.online.cash.repository.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository
public interface UserRepositoryNew extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT u FROM User AS u WHERE u.username = :username")
    User queryFindUserByUsername(@Param("username") String username);

}
