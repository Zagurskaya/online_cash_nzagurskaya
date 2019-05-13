package com.gmail.zagurskaya.repository;


import java.sql.Connection;
import java.util.List;

public interface DaoRepository<T> {

    T create(Connection connection, T t);

    T read(Connection connection, Long id);

    boolean update(Connection connection, T t);

    boolean delete(Connection connection, T t);

    List<T> getAll(Connection connection);

    List<T> getAll(Connection connection, String where);
}
