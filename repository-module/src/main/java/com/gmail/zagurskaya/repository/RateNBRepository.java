package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.RateNB;

import java.sql.Connection;
import java.util.List;

public interface RateNBRepository extends DaoRepository<RateNB>{

    List<RateNB> getRateNB(Connection connection);

    RateNB add(Connection connection, RateNB rateNB);

}
