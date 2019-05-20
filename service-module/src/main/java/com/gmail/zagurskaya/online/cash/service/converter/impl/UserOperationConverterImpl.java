package com.gmail.zagurskaya.online.cash.service.converter.impl;

import com.gmail.zagurskaya.online.cash.repository.model.UserOperation;
import com.gmail.zagurskaya.online.cash.service.converter.UserOperationConverter;
import com.gmail.zagurskaya.online.cash.service.model.UserOperationDTO;
import org.springframework.stereotype.Component;

@Component
public class UserOperationConverterImpl implements UserOperationConverter {

    @Override
    public UserOperationDTO toDTO(UserOperation userOperation) {
        UserOperationDTO userOperationDTO = new UserOperationDTO();
        userOperationDTO.setId(userOperation.getId());
        userOperationDTO.setTimestamp(userOperation.getTimestamp());
        userOperationDTO.setRate(userOperation.getRate());
        userOperationDTO.setSum(userOperation.getSum());
        userOperationDTO.setCurrencyId(userOperation.getCurrencyId());
        userOperationDTO.setUserId(userOperation.getUserId());
        userOperationDTO.setOperationId(userOperation.getOperationId());
        userOperationDTO.setFio(userOperation.getFio());
        return userOperationDTO;
    }

    @Override
    public UserOperation toEntity(UserOperationDTO userOperationDTO) {
        UserOperation userOperation = new UserOperation();
        userOperation.setId(userOperationDTO.getId());
        userOperation.setTimestamp(userOperationDTO.getTimestamp());
        userOperation.setRate(userOperationDTO.getRate());
        userOperation.setSum(userOperationDTO.getSum());
        userOperation.setCurrencyId(userOperationDTO.getCurrencyId());
        userOperation.setUserId(userOperationDTO.getUserId());
        userOperation.setOperationId(userOperationDTO.getOperationId());
        userOperation.setSpecification(userOperationDTO.getSpecification());
        userOperation.setFio(userOperationDTO.getFio());
        return userOperation;
    }
}
