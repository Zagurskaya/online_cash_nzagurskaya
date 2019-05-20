package com.gmail.zagurskaya.online.cash.web.controller.cashController;

import com.gmail.zagurskaya.online.cash.service.UserService;
import com.gmail.zagurskaya.online.cash.service.model.UserDTO;
import com.gmail.zagurskaya.online.cash.web.exception.NotFoundAllUsersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cash/currency")
public class CashCurrencyController {

    private final UserService userService;

    @Autowired
    public CashCurrencyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getCashCurrencyPage(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/currency";
    }
    @GetMapping("/all_currencies")
    public String getCashCurrencyAllCurrencyPage(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/currency/all_currencies";
    }
    @GetMapping("/ratenb")
    public String getCashCurrencyRateNBPage(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/currency/ratenb";
    }
    @GetMapping("/ratecb")
    public String getCashCurrencyRateCBPage(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/currency/ratecb";
    }
}
