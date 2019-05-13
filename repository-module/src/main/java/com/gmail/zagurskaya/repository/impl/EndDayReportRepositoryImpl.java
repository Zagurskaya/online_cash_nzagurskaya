package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.EndDayReportRepository;
import com.gmail.zagurskaya.repository.exception.RoleRepositoryImplException;
import com.gmail.zagurskaya.repository.model.EndDayReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//public class EndDayReportRepositoryImpl extends AbstractRepository implements EndDayReportRepository {
@Repository
public class EndDayReportRepositoryImpl implements EndDayReportRepository {

    private static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private final AbstractRepository abstractRepository;

    public EndDayReportRepositoryImpl(AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }


    @Override
    public List<EndDayReport> getEndDayReports(Connection connection) {
        return getAll(connection);
    }

    @Override
    public EndDayReport create(Connection connection, EndDayReport endDayReport) {
        String sql = String.format("INSERT INTO `endDayReport`(`date`, `userId`, `description`, `isOpen`) " +
                        "VALUES ('%s','%s','%s','%s')",
                endDayReport.getDate(), endDayReport.getUserId(), endDayReport.getDescription(), endDayReport.getIsOpen() ? 1 : 0);

        long endDayReportId = abstractRepository.executeCreate(connection, sql);
        if (endDayReportId > 0) {
            endDayReport.setId(endDayReportId);
            return endDayReport;
        } else {
            return null;
        }
    }

    @Override
    public EndDayReport read(Connection connection, Long id) {
        List<EndDayReport> endDayReportList = getAll(connection, " WHERE id=" + id);
        return endDayReportList.size() == 0 ? null : endDayReportList.get(0);
    }

    @Override
    public boolean update(Connection connection, EndDayReport endDayReport) {
        String sql = String.format(
                "UPDATE `endDayReport` SET `date`='%s', `userId`='%s', `description`='%s', `isOpen`='%s'" +
                        " WHERE `id`='%d'",
                endDayReport.getDate(), endDayReport.getUserId(), endDayReport.getDescription(), endDayReport.getIsOpen() ? 1 : 0,
                endDayReport.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public boolean delete(Connection connection, EndDayReport endDayReport) {
        String sql = String.format(
                "DELETE FROM `endDayReport` WHERE `id`='%d'",
                endDayReport.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public List<EndDayReport> getAll(Connection connection) {
        return getAll(connection, "");
    }

    @Override
    public List<EndDayReport> getAll(Connection connection, String where) {
        List<EndDayReport> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "SELECT * FROM `endDayReport` " + where);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                EndDayReport EndDayReport = new EndDayReport();
                EndDayReport.setId(resultSet.getLong("id"));
                EndDayReport.setDate(resultSet.getDate("date"));
                EndDayReport.setUserId(resultSet.getLong("userId"));
                EndDayReport.setDescription(resultSet.getString("description"));
                EndDayReport.setIsOpen(resultSet.getBoolean("isOpen"));
                result.add(EndDayReport);
            }
            return result;
        } catch (SQLException e) {
            throw new RoleRepositoryImplException("Database exception during getALL Role where" + where, e);
        }
    }
}
