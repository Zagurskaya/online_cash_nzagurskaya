package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.EndDayReport;
import com.gmail.zagurskaya.service.model.EndDayReportDTO;

public interface EndDayReportConverter {

    EndDayReportDTO toDTO(EndDayReport endDayReport);

    EndDayReport toEntity(EndDayReportDTO endDayReportDTO);

}
