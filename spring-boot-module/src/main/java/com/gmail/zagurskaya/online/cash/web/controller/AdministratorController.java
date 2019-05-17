package com.gmail.zagurskaya.online.cash.web.controller;

import com.gmail.zagurskaya.online.cash.service.ReviewsService;
import com.gmail.zagurskaya.online.cash.service.RoleService;
import com.gmail.zagurskaya.online.cash.service.UserService;
import com.gmail.zagurskaya.online.cash.service.model.ReviewsDTO;
import com.gmail.zagurskaya.online.cash.service.model.RoleDTO;
import com.gmail.zagurskaya.online.cash.service.model.UserDTO;
import com.gmail.zagurskaya.online.cash.web.exception.NotFoundAllUsersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdministratorController {
    private static final Logger logger = LogManager.getLogger(AdministratorController.class);
    private final UserService userService;
    private final RoleService roleService;
    private final ReviewsService reviewsService;

    public AdministratorController(UserService userService, RoleService roleService, ReviewsService reviewsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.reviewsService = reviewsService;
    }

    @GetMapping("")
    public String getAdminPage() {

        return "administrator/admin";
    }

    @GetMapping("/users")
    public String getUsersInAdminPage(Model model) throws NotFoundAllUsersException {
        List<UserDTO> users = userService.getActionUsersSortedByUserName();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/users";
    }

    @GetMapping("/new")
    public String getAddUserInAdminPage() {

        return "administrator/new_user";
    }

    @PostMapping("/users/new")
    public String postAddUserInAdminPage(@ModelAttribute(value = "user") UserDTO userDTO,
                                         Model model) throws NotFoundAllUsersException {
        userDTO.setIsNotActive(false);
        userService.add(userDTO);
        List<UserDTO> users = userService.getActionUsersSortedByUserName();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/users";
    }

    @PostMapping("/users/delete")
    public String postDeleteUsersInAdminPage(
            @RequestParam("ids") Long[] ids,
            Model model
    ) throws NotFoundAllUsersException {

        for (int i = 0; i < ids.length; i++) {
            userService.delete(ids[i]);
            logger.info("deleted user with id = " + ids[i]);
        }
        List<UserDTO> users = userService.getActionUsersSortedByUserName();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/users";
    }

    @PostMapping("/users/update/password/{id}")
    public String postUpdateUserPasswordInAdminPage(
            @RequestParam("ids") Long[] ids,
            Model model
    ) throws NotFoundAllUsersException {

        for (int i = 0; i < ids.length; i++) {
            userService.delete(ids[i]);
            logger.error("deleted user with id = " + ids[i]);
        }
        List<UserDTO> users = userService.getActionUsersSortedByUserName();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/users";
    }

    @PostMapping("/users/update_role")
    public String postUpdateUserRoleInAdminPage(
            @RequestParam("id") Long userId,
            @RequestParam("roleId") Long roleId,
            Model model
    ) throws NotFoundAllUsersException {

        UserDTO userDTO = userService.getUserById(userId);
        userDTO.setRoleId(roleId);
        userService.update(userDTO);
        logger.error("deleted user with id = " + userId);
        logger.error("deleted user with roleId = " + roleId);

        List<UserDTO> users = userService.getActionUsersSortedByUserName();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/users";
    }

    @PostMapping("/users/update_password")
    public String postUpdateUserPasswordSameAsLoginInAdminPage(
            @RequestParam("id") Long userId,
            Model model
    ) throws NotFoundAllUsersException {

        UserDTO userDTO = userService.getUserById(userId);
        userDTO.setPassword(userService.returnPasswordSameAsLogin(userDTO));
        userService.update(userDTO);
        logger.error("new password = " + userService.returnPasswordSameAsLogin(userDTO));

        List<UserDTO> users = userService.getActionUsersSortedByUserName();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administrator/users";
    }

    @GetMapping("/reviews")
    public String getReviewsInAdminPage(Model model) throws NotFoundAllUsersException {
        List<ReviewsDTO> reviews =reviewsService.getReviews();
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("reviews", reviews);
        return "administrator/reviews";
    }

    @PostMapping("/reviews/delete")
    public String postUpdateReviewsInAdminPage(
            @RequestParam("ids") Long[] ids,
            Model model
    ) throws NotFoundAllUsersException {
        for (int i = 0; i < ids.length; i++) {
            reviewsService.delete(ids[i]);
            logger.error("delete delete witch id = " + ids[i]);
        }
        List<ReviewsDTO> reviews = reviewsService.getReviews();
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("reviews", reviews);
        model.addAttribute("users", users);
        return "administrator/reviews";
    }
    @GetMapping("/exit")
    public String getExitPage() {

        return "/";
    }
}
