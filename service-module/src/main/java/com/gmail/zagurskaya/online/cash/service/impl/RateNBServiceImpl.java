package com.gmail.zagurskaya.online.cash.service.impl;

import com.gmail.zagurskaya.online.cash.repository.RateNBRepository;
import com.gmail.zagurskaya.online.cash.repository.model.RateNB;
import com.gmail.zagurskaya.online.cash.service.RateNBService;
import com.gmail.zagurskaya.online.cash.service.converter.RateNBConverter;
import com.gmail.zagurskaya.online.cash.service.exception.RateCBServiceException;
import com.gmail.zagurskaya.online.cash.service.exception.RateNBServiceException;
import com.gmail.zagurskaya.online.cash.service.model.RateNBDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateNBServiceImpl implements RateNBService {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);
    private final RateNBConverter rateNBConverter;
    private final RateNBRepository rateNBRepository;

    public RateNBServiceImpl(RateNBConverter rateNBConverter, RateNBRepository rateNBRepository) {
        this.rateNBConverter = rateNBConverter;
        this.rateNBRepository = rateNBRepository;
    }

    @Override
    public List<RateNBDTO> getRatesNB() {
        try (Connection connection = rateNBRepository.getConnection()) {
            return getCurrenciesWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateNBServiceException("Exception with getAll currencies", e);
        }
    }

    private List<RateNBDTO> getCurrenciesWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<RateNB> currencies = rateNBRepository.findAll(0, Integer.MAX_VALUE);
            List<RateNBDTO> dtos = currencies.stream()
                    .map(rateNBConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return dtos;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateNBServiceException("Exception with getAll currencies", e);
        }
    }
    @Override
    public void add(RateNBDTO rateNBDTO) {
        try (Connection connection = rateNBRepository.getConnection()) {
            addWitchConnection(connection, rateNBDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateCBServiceException("Exception in add rateNB", e);
        }
    }

    private void addWitchConnection(Connection connection, RateNBDTO rateNBDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            RateNB rateNB = rateNBConverter.toEntity(rateNBDTO);
            rateNBRepository.persist(rateNB);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateNBServiceException("Exception in add rateNB witch Connection", e);
        }
    }


    @Override
    public void update(RateNBDTO rateNBDTO) {
        try (Connection connection = rateNBRepository.getConnection()) {
            updateWitchConnection(connection, rateNBDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateNBServiceException("Exception in add rateNB", e);
        }
    }


    private void updateWitchConnection(Connection connection, RateNBDTO rateNBDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            RateNB rateNB = rateNBConverter.toEntity(rateNBDTO);
            rateNBRepository.merge(rateNB);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateNBServiceException("Exception in add rateNB witch Connection", e);
        }
    }
    @Override
    public RateNBDTO getUserById(Long id) {
        try (Connection connection = rateNBRepository.getConnection()) {
            return getUserByIdWitchConnection(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateNBServiceException("Exception in get rateNB By Id", e);
        }
    }

    private RateNBDTO getUserByIdWitchConnection(Connection connection, Long id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            RateNB loaded = (RateNB) rateNBRepository.findById(id);
            RateNBDTO rateNBDTO = rateNBConverter.toDTO(loaded);
            connection.commit();
            return rateNBDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateNBServiceException("Exception in getUserByIdWitchConnection", e);
        }
    }
}
