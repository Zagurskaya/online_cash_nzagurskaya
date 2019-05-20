package com.gmail.zagurskaya.online.cash.service.converter;

import com.gmail.zagurskaya.online.cash.repository.model.UserOperation;
import com.gmail.zagurskaya.online.cash.service.model.UserOperationDTO;

public interface UserOperationConverter {

    UserOperationDTO toDTO(UserOperation userOperation);

    UserOperation toEntity(UserOperationDTO userOperationDTO);

}
