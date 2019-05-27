package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.model.UserDTO;
import com.gmail.zagurskaya.web.exception.NotFoundAllUsersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) throws NotFoundAllUsersException {
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
