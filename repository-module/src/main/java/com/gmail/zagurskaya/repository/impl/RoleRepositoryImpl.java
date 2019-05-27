package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.RoleRepository;
import com.gmail.zagurskaya.repository.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl extends GenericRepositoryImpl<Long, Role> implements RoleRepository {

}
