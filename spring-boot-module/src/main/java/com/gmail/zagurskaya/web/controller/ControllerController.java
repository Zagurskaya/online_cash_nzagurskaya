package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.web.exception.NotFoundAllUsersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/controller")
public class ControllerController {

    private final UserService userService;

    @Autowired
    public ControllerController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("")
    public String getControllerPage() {
        return "controller/home_controller";
    }

    @GetMapping("/all_currencies")
    public String getCashCurrencyAllCurrencyPageInControllerPage(Model model) throws NotFoundAllUsersException {
        return "cash/currency/all_currencies";
    }

    @GetMapping("/ratenb")
    public String getCashCurrencyRateNBPageInControllerPage(Model model) throws NotFoundAllUsersException {
        return "cash/currency/ratenb";
    }

    @GetMapping("/ratecb")
    public String getCashCurrencyRateCBPageInControllerPage(Model model) throws NotFoundAllUsersException {
        return "cash/currency/ratecb";
    }

    @GetMapping("/entries")
    public String getControllerEntriesPageInControllerPage(Model model) throws NotFoundAllUsersException {
        return "controller/entries";
    }
}
