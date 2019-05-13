package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.Currency;

import java.sql.Connection;
import java.util.List;

public interface CurrencyRepository extends DaoRepository<Currency>{

    List<Currency> getCurrency(Connection connection);

    Currency add(Connection connection, Currency currency);

}
