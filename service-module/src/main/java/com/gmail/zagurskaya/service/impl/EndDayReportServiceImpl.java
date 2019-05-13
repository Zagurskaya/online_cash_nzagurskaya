package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.EndDayReportRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.model.EndDayReport;
import com.gmail.zagurskaya.service.EndDayReportService;
import com.gmail.zagurskaya.service.converter.EndDayReportConverter;
import com.gmail.zagurskaya.service.exception.EndDayReportServiceImplException;
import com.gmail.zagurskaya.service.model.EndDayReportDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

//public class EndDayReportServiceImpl extends AbstractServiceImpl<EndDayReportDTO> implements EndDayReportService {
@Service
public class EndDayReportServiceImpl implements EndDayReportService {
    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    private final EndDayReportConverter endDayReportConverter;
    //    private final DaoRepository daoRepository;
    private final EndDayReportRepository endDayReportRepository;
    private final ConnectionHandler connectionHandler;

    public EndDayReportServiceImpl(EndDayReportConverter endDayReportConverter, EndDayReportRepository endDayReportRepository, ConnectionHandler connectionHandler) {
        this.endDayReportConverter = endDayReportConverter;
        this.endDayReportRepository = endDayReportRepository;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public List<EndDayReportDTO> getEndDayReports() {
        try (Connection connection = connectionHandler.getConnection()) {
            return getReportsEndDayWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new EndDayReportServiceImplException("Exception in getReportsEndDay", e);
        }
    }

    private List<EndDayReportDTO> getReportsEndDayWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<EndDayReport> endDayReports = endDayReportRepository.getEndDayReports(connection);
            List<EndDayReportDTO> endDayReportsDTO = endDayReports.stream()
                    .map(endDayReportConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return endDayReportsDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new EndDayReportServiceImplException("Exception in getAll witch endDayReports", e);
        }
    }
//    @Override
//    public EndDayReportDTO create(Connection connection, EndDayReportDTO endDayReportDTO) {
//        EndDayReport endDayReport = endDayReportConverter.toEntity(endDayReportDTO);
//        EndDayReport createEndDayReport = (EndDayReport) daoRepository.create(connection, endDayReport);
//        return endDayReportConverter.toDTO(createEndDayReport);
//    }
//
//    @Override
//    public EndDayReportDTO read(Connection connection, long id) {
//        EndDayReport readEndDayReport = (EndDayReport) daoRepository.read(connection, id);
//        return endDayReportConverter.toDTO(readEndDayReport);
//    }
//
//    @Override
//    public boolean update(Connection connection, EndDayReportDTO endDayReportDTO) {
//        EndDayReport endDayReport = endDayReportConverter.toEntity(endDayReportDTO);
//        return daoRepository.update(connection, endDayReport);
//    }
//
//    @Override
//    public boolean delete(Connection connection, EndDayReportDTO endDayReportDTO) {
//        EndDayReport endDayReport = endDayReportConverter.toEntity(endDayReportDTO);
//        return daoRepository.delete(connection, endDayReport);
//    }
//
//    @Override
//    public List<EndDayReportDTO> getAll(Connection connection) {
//        List<EndDayReport> endDayReports = daoRepository.getAll(connection);
//        List<EndDayReportDTO> dtos = endDayReports.stream()
//                .map(endDayReportConverter::toDTO)
//                .collect(Collectors.toList());
//        return dtos;
//    }
//
//    @Override
//    public List<EndDayReportDTO> getAll(Connection connection, String where) {
//        List<EndDayReport> endDayReports = daoRepository.getAll(connection, where);
//        List<EndDayReportDTO> dtos = endDayReports.stream()
//                .map(endDayReportConverter::toDTO)
//                .collect(Collectors.toList());
//        return dtos;
//    }
}
