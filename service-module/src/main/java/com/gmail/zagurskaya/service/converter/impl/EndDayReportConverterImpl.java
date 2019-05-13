package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.model.EndDayReport;
import com.gmail.zagurskaya.service.converter.EndDayReportConverter;
import com.gmail.zagurskaya.service.model.EndDayReportDTO;
import org.springframework.stereotype.Component;

@Component
public class EndDayReportConverterImpl implements EndDayReportConverter {
    @Override
    public EndDayReportDTO toDTO(EndDayReport endDayReport) {
        EndDayReportDTO endDayReportDTO = new EndDayReportDTO();
        endDayReportDTO.setId(endDayReport.getId());
        endDayReportDTO.setDate(endDayReport.getDate());
        endDayReportDTO.setUserId(endDayReport.getUserId());
        endDayReportDTO.setDescription(endDayReport.getDescription());
        endDayReportDTO.setIsOpen(endDayReport.getIsOpen());
        return endDayReportDTO;
    }

    @Override
    public EndDayReport toEntity(EndDayReportDTO endDayReportDTO) {
        EndDayReport endDayReport = new EndDayReport();
        endDayReport.setId(endDayReportDTO.getId());
        endDayReport.setDate(endDayReportDTO.getDate());
        endDayReport.setUserId(endDayReportDTO.getUserId());
        endDayReport.setDescription(endDayReportDTO.getDescription());
        endDayReport.setIsOpen(endDayReportDTO.getIsOpen());
        return endDayReport;
    }
}
