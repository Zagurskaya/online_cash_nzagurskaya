package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.RateCBRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.model.RateCB;
import com.gmail.zagurskaya.service.RateCBService;
import com.gmail.zagurskaya.service.converter.RateCBConverter;
import com.gmail.zagurskaya.service.exception.RateCBServiceImplException;
import com.gmail.zagurskaya.service.model.RateCBDTO;
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
    private final ConnectionHandler connectionHandler;

    public RateCBServiceImpl(RateCBConverter rateCBConverter, RateCBRepository rateCBRepository, ConnectionHandler connectionHandler) {
        this.rateCBConverter = rateCBConverter;
        this.rateCBRepository = rateCBRepository;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public List<RateCBDTO> getRateCB() {
        try (Connection connection = connectionHandler.getConnection()) {
            return getCurrenciesWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateCBServiceImplException("Exception with getAll currencies", e);
        }
    }

    private List<RateCBDTO> getCurrenciesWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<RateCB> currencies = rateCBRepository.getRateCB(connection);
            List<RateCBDTO> dtos = currencies.stream()
                    .map(rateCBConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return dtos;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateCBServiceImplException("Exception with getAll currencies", e);
        }
    }


    @Override
    public RateCBDTO add(RateCBDTO rateCBDTO) {
        try (Connection connection = connectionHandler.getConnection()) {
            return addWitchConnection(connection, rateCBDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateCBServiceImplException("Exception in add currencies", e);
        }
    }

    private RateCBDTO addWitchConnection(Connection connection, RateCBDTO rateCBDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            RateCB rateCB = rateCBConverter.toEntity(rateCBDTO);
            RateCB added = rateCBRepository.add(connection, rateCB);
            RateCBDTO addedRateCB = rateCBConverter.toDTO(added);
            connection.commit();
            return addedRateCB;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateCBServiceImplException("Exception in add currencies witch Connection", e);
        }
    }
}
