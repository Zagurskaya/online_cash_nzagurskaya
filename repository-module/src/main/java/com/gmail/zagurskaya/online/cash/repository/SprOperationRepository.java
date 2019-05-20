package com.gmail.zagurskaya.online.cash.repository;

import com.gmail.zagurskaya.online.cash.repository.model.SprOperations;

import java.sql.Connection;
import java.util.List;

public interface SprOperationRepository extends GenericRepository {

    List<SprOperations> getSprOperations(Connection connection);

}