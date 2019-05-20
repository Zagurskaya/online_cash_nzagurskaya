package com.gmail.zagurskaya.online.cash.service;

import com.gmail.zagurskaya.online.cash.service.model.SprOperationsDTO;
import com.gmail.zagurskaya.online.cash.service.model.UserDTO;

import java.util.List;

public interface SprOperationsService {

    List<SprOperationsDTO> getSprOperations();

    UserDTO getSprOperationById(Long id);

}
