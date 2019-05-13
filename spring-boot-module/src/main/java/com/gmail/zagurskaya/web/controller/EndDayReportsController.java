package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/end_day_reports")
public class EndDayReportsController {

    private final UserService userService;

    @Autowired
    public EndDayReportsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getEndDayReportsPage() {
        return "enddayreports/end_day_reports";
    }
}
