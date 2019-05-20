package com.gmail.zagurskaya.online.cash.service.impl;

import com.gmail.zagurskaya.online.cash.repository.CurrencyRepository;
import com.gmail.zagurskaya.online.cash.repository.model.Currency;
import com.gmail.zagurskaya.online.cash.service.CurrencyService;
import com.gmail.zagurskaya.online.cash.service.converter.CurrencyConverter;
import com.gmail.zagurskaya.online.cash.service.exception.CurrencyServiceException;
import com.gmail.zagurskaya.online.cash.service.model.CurrencyDTO;
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

    public CurrencyServiceImpl(CurrencyConverter currencyConverter, CurrencyRepository currencyRepository) {
        this.currencyConverter = currencyConverter;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<CurrencyDTO> getCurrencies() {
        try (Connection connection = currencyRepository.getConnection()) {
            return getCurrencysWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceException("Exception with getAll currencies", e);
        }
    }

    private List<CurrencyDTO> getCurrencysWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<Currency> currencys = currencyRepository.findAll(0, Integer.MAX_VALUE);
            List<CurrencyDTO> dtos = currencys.stream()
                    .map(currencyConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return dtos;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceException("Exception with getAll currencys", e);
        }
    }
    @Override
    public void add(CurrencyDTO currencyDTO) {
        try (Connection connection = currencyRepository.getConnection()) {
            addWitchConnection(connection, currencyDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceException("Exception in add currency", e);
        }
    }

    private void addWitchConnection(Connection connection, CurrencyDTO currencyDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Currency currency = currencyConverter.toEntity(currencyDTO);
            currencyRepository.persist(currency);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceException("Exception in add currency witch Connection", e);
        }
    }


    @Override
    public void update(CurrencyDTO currencyDTO) {
        try (Connection connection = currencyRepository.getConnection()) {
            updateWitchConnection(connection, currencyDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceException("Exception in add currency", e);
        }
    }


    private void updateWitchConnection(Connection connection, CurrencyDTO currencyDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Currency currency = currencyConverter.toEntity(currencyDTO);
            currencyRepository.merge(currency);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceException("Exception in add currency witch Connection", e);
        }
    }
    @Override
    public CurrencyDTO getUserById(Long id) {
        try (Connection connection = currencyRepository.getConnection()) {
            return getCurrencyByIdWitchConnection(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceException("Exception in get currency By Id", e);
        }
    }

    private CurrencyDTO getCurrencyByIdWitchConnection(Connection connection, Long id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Currency loaded = (Currency) currencyRepository.findById(id);
            CurrencyDTO currencyDTO = currencyConverter.toDTO(loaded);
            connection.commit();
            return currencyDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new CurrencyServiceException("Exception in getCurrencyByIdWitchConnection", e);
        }
    }
}
