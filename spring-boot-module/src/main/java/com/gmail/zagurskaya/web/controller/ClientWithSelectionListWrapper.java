package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClientWithSelectionListWrapper {
    private List<UserDTO> clientList;

    @Autowired
    public List<UserDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<UserDTO> clients) {
        this.clientList = clients;
    }
}
