package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.EndDayReport;

import java.sql.Connection;
import java.util.List;


public interface EndDayReportRepository extends DaoRepository<EndDayReport>{

    List<EndDayReport> getEndDayReports(Connection connection);

    }
