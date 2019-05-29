package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.UserInfoRepository;
import com.gmail.zagurskaya.repository.model.UserInfo;
import org.springframework.stereotype.Repository;


@Repository
public class UserInfoRepositoryImpl extends GenericRepositoryImpl<Long, UserInfo> implements UserInfoRepository {

}
