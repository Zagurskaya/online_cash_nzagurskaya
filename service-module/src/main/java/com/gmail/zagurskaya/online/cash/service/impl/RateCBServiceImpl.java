package com.gmail.zagurskaya.online.cash.service.impl;

import com.gmail.zagurskaya.online.cash.repository.RateCBRepository;
import com.gmail.zagurskaya.online.cash.repository.model.RateCB;
import com.gmail.zagurskaya.online.cash.service.RateCBService;
import com.gmail.zagurskaya.online.cash.service.converter.RateCBConverter;
import com.gmail.zagurskaya.online.cash.service.exception.RateCBServiceException;
import com.gmail.zagurskaya.online.cash.service.model.RateCBDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateCBServiceImpl implements RateCBService {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);
    private final RateCBConverter rateCBConverter;
    private final RateCBRepository rateCBRepository;

    public RateCBServiceImpl(RateCBConverter rateCBConverter, RateCBRepository rateCBRepository) {
        this.rateCBConverter = rateCBConverter;
        this.rateCBRepository = rateCBRepository;
    }

    @Override
    public List<RateCBDTO> getRatesCB() {
        try (Connection connection = rateCBRepository.getConnection()) {
            return getCurrenciesWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateCBServiceException("Exception with getAll currencies", e);
        }
    }

    private List<RateCBDTO> getCurrenciesWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<RateCB> currencies = rateCBRepository.findAll(0, Integer.MAX_VALUE);
            List<RateCBDTO> dtos = currencies.stream()
                    .map(rateCBConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return dtos;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateCBServiceException("Exception with getAll currencies", e);
        }
    }

    @Override
    public void add(RateCBDTO rateCBDTO) {
        try (Connection connection = rateCBRepository.getConnection()) {
            addWitchConnection(connection, rateCBDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateCBServiceException("Exception in add rateCB", e);
        }
    }

    private void addWitchConnection(Connection connection, RateCBDTO rateCBDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            RateCB rateCB = rateCBConverter.toEntity(rateCBDTO);
            rateCBRepository.persist(rateCB);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateCBServiceException("Exception in add rateCB witch Connection", e);
        }
    }


    @Override
    public void update(RateCBDTO rateCBDTO) {
        try (Connection connection = rateCBRepository.getConnection()) {
            updateWitchConnection(connection, rateCBDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateCBServiceException("Exception in add rateCB", e);
        }
    }


    private void updateWitchConnection(Connection connection, RateCBDTO rateCBDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            RateCB rateCB = rateCBConverter.toEntity(rateCBDTO);
            rateCBRepository.merge(rateCB);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateCBServiceException("Exception in add rateCB witch Connection", e);
        }
    }
    @Override
    public RateCBDTO getUserById(Long id) {
        try (Connection connection = rateCBRepository.getConnection()) {
            return getUserByIdWitchConnection(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateCBServiceException("Exception in get rateCB By Id", e);
        }
    }

    private RateCBDTO getUserByIdWitchConnection(Connection connection, Long id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            RateCB loaded = (RateCB) rateCBRepository.findById(id);
            RateCBDTO rateCBDTO = rateCBConverter.toDTO(loaded);
            connection.commit();
            return rateCBDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateCBServiceException("Exception in getUserByIdWitchConnection", e);
        }
    }
}
