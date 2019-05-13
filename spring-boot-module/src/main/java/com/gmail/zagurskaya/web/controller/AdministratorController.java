package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.RoleService;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.model.RoleDTO;
import com.gmail.zagurskaya.service.model.UserDTO;
import com.gmail.zagurskaya.web.exception.NotFoundAllUsersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.repository.AbstractRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdministratorController {
    private static final Logger logger = LogManager.getLogger(AdministratorController.class);
    private final UserService userService;
    private final RoleService roleService;
    private final ClientWithSelectionListWrapper wrapper;

    @Autowired
    public AdministratorController(UserService userService, RoleService roleService, ClientWithSelectionListWrapper wrapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.wrapper = wrapper;
    }

    @GetMapping("")
    public String getAdminPage() {

        return "administrator/admin";
    }

    @GetMapping("/users")
    public String getUsersInAdminPage(Model model) throws NotFoundAllUsersException {
        List<UserDTO> users = userService.getUsers();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/users";
    }

    @GetMapping("/add_user")
    public String getAddUserInAdminPage() {
        return "administrator/add_user";
    }

    @PostMapping("/add_user")
    public String postAddUserInAdminPage(@ModelAttribute(value = "user") UserDTO userDTO, Model model) throws NotFoundAllUsersException {
        userDTO.setIsNotActive(false);
        userService.add(userDTO);
        List<UserDTO> users = userService.getUsers();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/users";
    }

    @GetMapping("/delete_user")
    public String getDeleteUserInAdminPage(Model model) throws NotFoundAllUsersException {
        List<UserDTO> users = userService.getUsers();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);

//        ClientWithSelectionListWrapper wrapper =userService.getUsers();
        wrapper.setClientList(userService.getUsers());
        model.addAttribute("wrapper", wrapper);
        return "administrator/delete_user";
    }

    // как загнать сюда список ids ?
    //    @PostMapping("/delete_user/{ids}")
//public String postDeleteUserInAdminPage(@PathVariable("ids") Long[] ids, Model model) throws NotFoundAllUsersException {
    @PostMapping("/delete_user")
    public String postDeleteUserInAdminPage(Model model) throws NotFoundAllUsersException {
        List<Long> idDeletedUsersList = Arrays.asList(Long.valueOf(2), Long.valueOf(3));

        for (int i = 0; i < idDeletedUsersList.size(); i++) {
            userService.delete(idDeletedUsersList.get(i));
            logger.error("deleted user with id = " + idDeletedUsersList.get(i));
        }
        List<UserDTO> users = userService.getUsers();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/users";
    }

    @GetMapping("/update_users")
    public String getUpdateUserInAdminPage(Model model) {
        List<UserDTO> users = userService.getUsers();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/update_users";
    }

    @PutMapping("/update_users/{id}")
    public String postUpdateUserInAdminPage(@PathVariable Long id,
                                            @RequestBody UserDTO userDTO, Model model) throws NotFoundAllUsersException {
        userDTO.setIsNotActive(false);
        userService.update(userDTO);
        List<UserDTO> users = userService.getUsers();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/users";
    }

    @GetMapping("/exit")
    public String getExitPage() {

        return "/";
    }
}
