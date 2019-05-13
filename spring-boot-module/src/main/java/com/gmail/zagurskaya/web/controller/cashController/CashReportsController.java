package com.gmail.zagurskaya.web.controller.cashController;

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
@RequestMapping("/cash/reports")
public class CashReportsController {

    private final UserService userService;

    @Autowired
    public CashReportsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getCashReportsPage(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/reports";
    }

    @GetMapping("/report1")
    public String getCashReport1Page(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/reports/report1";
    }

    @GetMapping("/report2")
    public String getCashReport2Page(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/reports/report2";
    }
    @GetMapping("/report3")
    public String getCashReport3Page(Model model) throws NotFoundAllUsersException {
//        List<UserDTO> users = userService.getUsers();
//        model.addAttribute("users", users);
        return "cash/reports/report3";
    }
}
