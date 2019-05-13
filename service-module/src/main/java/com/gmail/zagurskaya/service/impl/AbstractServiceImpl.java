package com.gmail.zagurskaya.service.impl;

import java.sql.Connection;
import java.util.List;

abstract class AbstractServiceImpl<DTO> {

    DTO create(Connection connection, DTO dto) {
        return null;
    }

    DTO read(Connection connection, long id) {
        return null;
    }

    boolean update(Connection connection, DTO dto) {
        return false;
    }

    boolean delete(Connection connection, DTO dto) {
        return false;
    }

    List<DTO> getAll(Connection connection) {
        return null;
    }

    List<DTO> getAll(Connection connection, String where) {
        return null;
    }
}
