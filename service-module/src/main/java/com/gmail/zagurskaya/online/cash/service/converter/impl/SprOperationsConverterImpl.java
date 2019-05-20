package com.gmail.zagurskaya.online.cash.service.converter.impl;

import com.gmail.zagurskaya.online.cash.repository.model.SprOperations;
import com.gmail.zagurskaya.online.cash.repository.model.User;
import com.gmail.zagurskaya.online.cash.service.converter.SprOperationsConverter;
import com.gmail.zagurskaya.online.cash.service.converter.UserConverter;
import com.gmail.zagurskaya.online.cash.service.model.SprOperationsDTO;
import org.springframework.stereotype.Component;

@Component
public class SprOperationsConverterImpl implements SprOperationsConverter {

    @Override
    public SprOperationsDTO toDTO(SprOperations sprOperations) {
        SprOperationsDTO sprOperationsDTO = new SprOperationsDTO();
        sprOperationsDTO.setId(sprOperations.getId());
        sprOperationsDTO.setName(sprOperations.getName());
        sprOperationsDTO.setSpecification(sprOperations.getSpecification());
        return sprOperationsDTO;
    }

    @Override
    public SprOperations toEntity(SprOperationsDTO sprOperationsDTO) {
        SprOperations sprOperations = new SprOperations();
        sprOperations.setId(sprOperationsDTO.getId());
        sprOperations.setName(sprOperationsDTO.getName());
        sprOperations.setSpecification(sprOperationsDTO.getSpecification());
        return sprOperations;
    }
}
