package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.RateNBRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.model.RateNB;
import com.gmail.zagurskaya.service.RateNBService;
import com.gmail.zagurskaya.service.converter.RateNBConverter;
import com.gmail.zagurskaya.service.exception.RateNBServiceImplException;
import com.gmail.zagurskaya.service.model.RateNBDTO;
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
    private final ConnectionHandler connectionHandler;

    public RateNBServiceImpl(RateNBConverter rateNBConverter, RateNBRepository rateNBRepository, ConnectionHandler connectionHandler) {
        this.rateNBConverter = rateNBConverter;
        this.rateNBRepository = rateNBRepository;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public List<RateNBDTO> getRateNB() {
        try (Connection connection = connectionHandler.getConnection()) {
            return getCurrenciesWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateNBServiceImplException("Exception with getAll currencies", e);
        }
    }

    private List<RateNBDTO> getCurrenciesWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<RateNB> currencies = rateNBRepository.getRateNB(connection);
            List<RateNBDTO> dtos = currencies.stream()
                    .map(rateNBConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return dtos;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateNBServiceImplException("Exception with getAll currencies", e);
        }
    }


    @Override
    public RateNBDTO add(RateNBDTO rateNBDTO) {
        try (Connection connection = connectionHandler.getConnection()) {
            return addWitchConnection(connection, rateNBDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateNBServiceImplException("Exception in add currencies", e);
        }
    }

    private RateNBDTO addWitchConnection(Connection connection, RateNBDTO rateNBDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            RateNB rateNB = rateNBConverter.toEntity(rateNBDTO);
            RateNB added = rateNBRepository.add(connection, rateNB);
            RateNBDTO addedRateNB = rateNBConverter.toDTO(added);
            connection.commit();
            return addedRateNB;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RateNBServiceImplException("Exception in add currencies witch Connection", e);
        }
    }
}
