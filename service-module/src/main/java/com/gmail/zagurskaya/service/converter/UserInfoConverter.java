package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.UserInfo;
import com.gmail.zagurskaya.service.model.UserInfoDTO;

public interface UserInfoConverter {

    UserInfoDTO toDTO(UserInfo userInfo);

    UserInfo toEntity(UserInfoDTO userInfoDTO);

}
