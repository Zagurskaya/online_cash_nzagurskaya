package com.gmail.zagurskaya.online.cash.service.converter;

import com.gmail.zagurskaya.online.cash.repository.model.SprOperations;
import com.gmail.zagurskaya.online.cash.service.model.SprOperationsDTO;

public interface SprOperationsConverter {

    SprOperationsDTO toDTO(SprOperations sprOperations);

    SprOperations toEntity(SprOperationsDTO sprOperationDTO);

}
