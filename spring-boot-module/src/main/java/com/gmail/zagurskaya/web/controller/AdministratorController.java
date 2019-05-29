package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.ReviewsService;
import com.gmail.zagurskaya.service.RoleService;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.model.ReviewsDTO;
import com.gmail.zagurskaya.service.model.RoleDTO;
import com.gmail.zagurskaya.service.model.UserDTO;
import com.gmail.zagurskaya.web.exception.NotFoundAllUsersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping
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

    @GetMapping("/new_user")
    public String getAddUserInAdminPage() {

        return "administrator/new_user";
    }

    @PostMapping("/users/new")
    public String postAddUserInAdminPage(@ModelAttribute(value = "user") UserDTO userDTO,
                                         Model model) throws NotFoundAllUsersException {
        userDTO.setIsNotActive(false);
        userService.add(userDTO);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/delete")
    public String postDeleteUsersInAdminPage(
            @RequestParam("ids") List<Long> ids,
            Model model
    ) throws NotFoundAllUsersException {
        userService.deleteUsersList(ids);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/update_role")
    public String postUpdateUserRoleInAdminPage(
            @RequestParam("id") Long userId,
            @RequestParam("roleId") Long roleId,
            Model model) {

        UserDTO userDTO = userService.updateUserRole(userId, roleId);
        return "redirect:/admin/users";
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
        return "redirect:/admin/users";
    }

    @GetMapping("/reviews")
    public String getReviewsInAdminPage(Model model) throws NotFoundAllUsersException {
        List<ReviewsDTO> reviews = reviewsService.getReviews();
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("reviews", reviews);
        return "administrator/reviews";
    }

    @PostMapping("/reviews/delete")
    public String postDeleteReviewsInAdminPage(
            @RequestParam("ids") List<Long> ids,
            Model model
    ) throws NotFoundAllUsersException {
        reviewsService.deleteReviewsList(ids);
        return "redirect:/admin/reviews";
    }

    @GetMapping("/exit")
    public String getExitPage() {

        return "/";
    }
}
