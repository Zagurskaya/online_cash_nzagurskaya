package com.gmail.zagurskaya.online.cash.web.controller.cashController;

import com.gmail.zagurskaya.online.cash.service.UserService;
import com.gmail.zagurskaya.online.cash.service.model.UserDTO;
import com.gmail.zagurskaya.online.cash.web.exception.NotFoundAllUsersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cash/operation")
public class CashOperationController {

    private final UserService userService;

    @Autowired
    public CashOperationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getCashOperationPage(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/operation";
    }

    @GetMapping("/payment")
    public String getCashOperationPaymentPage(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/operation/payment";
    }

    @GetMapping("/balance")
    public String getCashOperationBalancePage(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/operation/balance";
    }

    @GetMapping("/user_operation")
    public String getCashOperationUserOperationPage(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/operation/user_operation";
    }
}
