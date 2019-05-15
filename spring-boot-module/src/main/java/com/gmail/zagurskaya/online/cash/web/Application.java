package com.gmail.zagurskaya.online.cash.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ={
        "com.gmail.zagurskaya.online.cash.repository",
        "com.gmail.zagurskaya.online.cash.service",
        "com.gmail.zagurskaya.online.cash.web"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
