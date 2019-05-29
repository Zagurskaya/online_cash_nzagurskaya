package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.model.UserInfo;
import com.gmail.zagurskaya.service.converter.UserInfoConverter;
import com.gmail.zagurskaya.service.model.UserInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class UserInfoConverterImpl implements UserInfoConverter {


    @Override
    public UserInfoDTO toDTO(UserInfo userInfo) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userInfo.getId());
        userInfoDTO.setLastName(userInfo.getLastName());
        userInfoDTO.setFirstName(userInfo.getFirstName());
        userInfoDTO.setPatronymic(userInfo.getPatronymic());
        return userInfoDTO;
    }

    @Override
    public UserInfo toEntity(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userInfoDTO.getId());
        userInfo.setLastName(userInfoDTO.getLastName());
        userInfo.setFirstName(userInfoDTO.getFirstName());
        userInfo.setPatronymic(userInfoDTO.getPatronymic());
        return userInfo;
    }
}
