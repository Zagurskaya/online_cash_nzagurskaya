package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.CurrencyRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.model.Currency;
import com.gmail.zagurskaya.service.CurrencyService;
import com.gmail.zagurskaya.service.converter.CurrencyConverter;
import com.gmail.zagurskaya.service.exception.CurrencyServiceImplException;
import com.gmail.zagurskaya.service.model.CurrencyDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);
    private final CurrencyConverter currencyConverter;
    private final CurrencyRepository currencyRepository;
    private final ConnectionHandler connectionHandler;

    public CurrencyServiceImpl(CurrencyConverter currencyConverter, CurrencyRepository currencyRepository, ConnectionHandler connectionHandler) {
        this.currencyConverter = currencyConverter;
        this.currencyRepository = currencyRepository;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public List<CurrencyDTO> getCurrency() {
        try (Connection connection = connectionHandler.getConnection()) {
            return getCurrenciesWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceImplException("Exception with getAll currencies", e);
        }
    }

    private List<CurrencyDTO> getCurrenciesWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<Currency> currencies = currencyRepository.getCurrency(connection);
            List<CurrencyDTO> dtos = currencies.stream()
                    .map(currencyConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return dtos;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceImplException("Exception with getAll currencies", e);
        }
    }


    @Override
    public CurrencyDTO add(CurrencyDTO currencyDTO) {
        try (Connection connection = connectionHandler.getConnection()) {
            return addWitchConnection(connection, currencyDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceImplException("Exception in add currencies", e);
        }
    }

    private CurrencyDTO addWitchConnection(Connection connection, CurrencyDTO currencyDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Currency currency = currencyConverter.toEntity(currencyDTO);
            Currency added = currencyRepository.add(connection, currency);
            CurrencyDTO addedCurrency = currencyConverter.toDTO(added);
            connection.commit();
            return addedCurrency;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceImplException("Exception in add currencies witch Connection", e);
        }
    }
}
