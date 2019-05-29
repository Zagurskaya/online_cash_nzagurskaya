package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public User loadUserByUsername(String name) {
        String query = "select u from  User u WHERE u.username like :name";
        Query q = entityManager.createQuery(query).setParameter( "name", name);
        return (User) q.getSingleResult();
    }

    @Override
    public List<User> getActionUsersSortedByUserName() {
        String query = "SELECT u FROM User u WHERE u.isNotActive = false ORDER BY u.username";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }

}
