package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.ReviewsRepository;
import com.gmail.zagurskaya.repository.RoleRepository;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.converter.ReviewsConverter;
import com.gmail.zagurskaya.service.converter.RoleConverter;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ReviewsConverter reviewsConverter;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setLastName(user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setPatronymic(user.getPatronymic());
        userDTO.setRole(roleConverter.toDTO(user.getRole()));
        userDTO.setIsNotActive(user.getIsNotActive());
        userDTO.setReviews(user.getReviews().stream().map(reviews -> reviewsConverter.toDTO(reviews)).collect(Collectors.toSet()));

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setPatronymic(userDTO.getPatronymic());
        user.setRole(roleRepository.findById(userDTO.getRole().getId()));
        user.setIsNotActive(userDTO.getIsNotActive());
        user.setReviews(userDTO.getReviews().stream().map(reviewsDTO -> reviewsRepository.findById(reviewsDTO.getId())).collect(Collectors.toSet()));
        return user;
    }
}
