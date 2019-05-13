package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.RateCB;

import java.sql.Connection;
import java.util.List;

public interface RateCBRepository extends DaoRepository<RateCB>{

    List<RateCB> getRateCB(Connection connection);

    RateCB add(Connection connection, RateCB rateCB);

}
