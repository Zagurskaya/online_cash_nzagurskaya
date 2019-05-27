package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.model.UserDTO;
import com.gmail.zagurskaya.web.exception.NotFoundAllUsersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cash")
public class CashController {

    private final UserService userService;

    @Autowired
    public CashController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUsersPage(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/home_cash";
    }
}
