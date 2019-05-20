package com.gmail.zagurskaya.online.cash.service;

import com.gmail.zagurskaya.online.cash.service.model.UserOperationDTO;

import java.util.List;

public interface UserOperationService {

    List<UserOperationDTO> getUserOperations();

    UserOperationDTO getUserOperationById(Long id);

    void add(UserOperationDTO userOperationDTO);

    void update(UserOperationDTO userOperationDTO);
}
