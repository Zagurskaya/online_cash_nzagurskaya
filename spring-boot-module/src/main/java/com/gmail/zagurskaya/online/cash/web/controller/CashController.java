package com.gmail.zagurskaya.online.cash.web.controller;

import com.gmail.zagurskaya.online.cash.service.*;
import com.gmail.zagurskaya.online.cash.service.model.*;
import com.gmail.zagurskaya.online.cash.web.exception.NotFoundAllUsersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/cash")
public class CashController {
    private static final Logger logger = LogManager.getLogger(AdministratorController.class);
    private final UserService userService;
    private final RoleService roleService;
    private final SprOperationsService sprOperationsService;
    private final RateCBService rateCBService;
    private final CurrencyService currencyService;
    private final UserOperationService userOperationService;

    @Autowired
    public CashController(UserService userService, RoleService roleService, SprOperationsService sprOperationsService, RateCBService rateCBService, CurrencyService currencyService, UserOperationService userOperationService) {
        this.userService = userService;
        this.roleService = roleService;
        this.sprOperationsService = sprOperationsService;
        this.rateCBService = rateCBService;
        this.currencyService = currencyService;
        this.userOperationService = userOperationService;
    }

    @GetMapping("")
    public String getUsersPage(Model model, SecurityContext securityContext) throws NotFoundAllUsersException {

        String username = securityContext.getAuthentication().getName();
        UserDTO userDTO = userService.loadUserByUsername(username);

        UserProfileDTO userProfileDTO = new UserProfileDTO();

        userProfileDTO.setUsername(userDTO.getUsername());
        userProfileDTO.setLastName(userDTO.getLastName());
        userProfileDTO.setFirstName(userDTO.getFirstName());
        userProfileDTO.setPatronymic(userDTO.getPatronymic());
        userProfileDTO.setRole(userDTO.getRoleId().toString());

        model.addAttribute("userProfileDTO", userProfileDTO);
        return "cash/home_cash";
    }

    @PostMapping("/new_password")
    public String postUpdateUserPasswordSameAsLoginInAdminPage(
            @RequestParam("id") Long userId,
            Model model
    ) throws NotFoundAllUsersException {
        UserDTO userDTO = userService.getUserById(userId);
        userDTO.setPassword(userService.returnPasswordSameAsLogin(userDTO));
        userService.update(userDTO);
        logger.error("new password = " + userService.returnPasswordSameAsLogin(userDTO));

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(userDTO.getUsername());
        userProfileDTO.setLastName(userDTO.getLastName());
        userProfileDTO.setFirstName(userDTO.getFirstName());
        userProfileDTO.setPatronymic(userDTO.getPatronymic());
        userProfileDTO.setRole(userDTO.getRoleId().toString());
        model.addAttribute("userProfileDTO", userProfileDTO);
        return "cash/home_cash";
    }

    @GetMapping("/operation/payment")
    public String getSprOperationsInCashPage(Model model, SecurityContext securityContext) throws NotFoundAllUsersException {

        List<SprOperationsDTO> sprOperationsDTO = sprOperationsService.getSprOperations();
        List<CurrencyDTO> currencyDTOS = currencyService.getCurrencies();

        model.addAttribute("sprOperationsDTO", sprOperationsDTO);
        model.addAttribute("currencyDTOS", currencyDTOS);
        return "cash/home_cash";
    }

    @PostMapping("/operation/payment")
    public ResponseEntity savePayment(@ModelAttribute(value = "idoperation") Long idOperation,
                                     @ModelAttribute(value = "idcurrency") Long idcurrency,
                                     @ModelAttribute(value = "sum") String sum,
                                     Model model,
                                     SecurityContext securityContext) {
        String username = securityContext.getAuthentication().getName();
        Long userId = userService.loadUserByUsername(username).getId();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        UserOperationDTO userOperationDTO = new UserOperationDTO();
        userOperationDTO.setTimestamp(now);
        userOperationDTO.setRate(rateCBService.getUserById(1L).getSum());
        userOperationDTO.setSum(Double.valueOf(sum));
        userOperationDTO.setCurrencyId(idcurrency);
        userOperationDTO.setUserId(userId);
        userOperationDTO.setOperationId(idOperation);

        userOperationService.add(userOperationDTO);

        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/operation/user_operation")
    public String getUserOperationsInCashPage(Model model) throws NotFoundAllUsersException {

        List<UserOperationDTO> userOperationDTOS = userOperationService.getUserOperations();

        model.addAttribute("userOperationDTOS", userOperationDTOS);
        return "cash/operation/user_operation";
    }

    @PostMapping("/operation/user_operation")
    public ResponseEntity updateUserOperation(@ModelAttribute(value = "id") Long id,
                                      Model model) {
        UserOperationDTO userOperationDTO = userOperationService.getUserOperationById(id);
        userOperationDTO.setSpecification("тест");

        userOperationService.update(userOperationDTO);

        return new ResponseEntity(HttpStatus.OK);
    }
}
