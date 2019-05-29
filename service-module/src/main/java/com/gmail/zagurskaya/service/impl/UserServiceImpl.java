package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.RoleRepository;
import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.model.Role;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserConverter userConverter, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll(0, Integer.MAX_VALUE);
        List<UserDTO> dtos = users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    @Transactional
    public void add(UserDTO userDTO) {
        User user = userConverter.toEntity(userDTO);
        String password = randomPasswordWithEncoder(6);
        user.setPassword(password);
        userRepository.persist(user);
    }

    @Override
    @Transactional
    public void deleteUsersList(List<Long> ids) {
        ids.stream().forEach(id -> {
            delete(id);
            logger.info("deleted user with id = " + id);
        });
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id);
        user.setIsNotActive(true);
        userRepository.merge(user);
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) {
        User user = userConverter.toEntity(userDTO);
        userRepository.merge(user);
    }

    @Override
    @Transactional
    public UserDTO loadUserByUsername(String name) {
        User loaded = userRepository.loadUserByUsername(name);
        UserDTO userDTO = userConverter.toDTO(loaded);
        return userDTO;
    }

    @Override
    @Transactional
    public List<UserDTO> getActionUsersSortedByUserName() {
        List<User> users = userRepository.getActionUsersSortedByUserName();
        List<UserDTO> dtos = users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    @Transactional
    public UserDTO getUserById(Long id) {
        User loaded = (User) userRepository.findById(id);
        UserDTO userDTO = userConverter.toDTO(loaded);
        return userDTO;
    }

    @Override
    public UserDTO updateUserRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId);
        Role role = roleRepository.findById(roleId);
        user.setRole(role);
        userRepository.merge(user);
        return userConverter.toDTO(user);
    }

    private String randomPasswordWithEncoder(int length) {
        String password = "";
        for (int i = 0; i < length; i++) {
            int oneNumber = (int) (Math.random() * 10);
            password += String.valueOf(oneNumber);
        }
        logger.info("New password => " + password);
        return encoder(password);
    }

    public String returnPasswordSameAsLogin(UserDTO userDTO) {

        return encoder(userDTO.getUsername());
    }


    private String encoder(String word) {
        String encode = passwordEncoder.encode(word);
        logger.info("New password encoder => " + encode);
        return encode;

    }

}
