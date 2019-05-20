package com.gmail.zagurskaya.online.cash.repository;

import com.gmail.zagurskaya.online.cash.repository.model.UserOperation;

import java.sql.Connection;
import java.util.List;

public interface UserOperationRepository extends GenericRepository  {

    List<UserOperation> getUserOperations(Connection connection);

}
