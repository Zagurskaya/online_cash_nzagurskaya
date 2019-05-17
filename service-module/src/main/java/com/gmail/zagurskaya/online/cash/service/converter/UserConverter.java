package com.gmail.zagurskaya.online.cash.service.converter;

import com.gmail.zagurskaya.online.cash.repository.model.User;
import com.gmail.zagurskaya.online.cash.service.model.UserDTO;

public interface UserConverter {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

}
