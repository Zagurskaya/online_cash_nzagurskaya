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
//        String sql = "SELECT * FROM users WHERE username = ?";
        String query = "select u from  User u WHERE u.username like :name";

        Query q = entityManager.createQuery(query).setParameter( "name", name);
        int i = 4;
        return (User) q.getSingleResult();
    }

    @Override
    public List<User> getActionUsersSortedByUserName() {
//        String sql =
//                "SELECT * FROM `users` WHERE `isNotActive`= 0 ORDER BY username";
//            String query = "from " + entityClass.getName() + " ORDER BY username"+" c";
        String query = "SELECT * FROM users WHERE isnotactive= 0 ORDER BY username";

        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }

}
