package com.gmail.zagurskaya.online.cash.web.controller;

import com.gmail.zagurskaya.online.cash.service.CurrencyService;
import com.gmail.zagurskaya.online.cash.service.RateCBService;
import com.gmail.zagurskaya.online.cash.service.RateNBService;
import com.gmail.zagurskaya.online.cash.service.UserService;
import com.gmail.zagurskaya.online.cash.service.model.CurrencyDTO;
import com.gmail.zagurskaya.online.cash.service.model.RateCBDTO;
import com.gmail.zagurskaya.online.cash.service.model.RateNBDTO;
import com.gmail.zagurskaya.online.cash.service.model.UserDTO;
import com.gmail.zagurskaya.online.cash.web.exception.NotFoundAllUsersException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    private static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    private final UserService userService;
    private final RateNBService rateNBService;
    private final RateCBService rateCBService;
    private final CurrencyService currencyService;

    @Autowired
    public RestApiController(UserService userService, RateNBService rateNBService, RateCBService rateCBService, CurrencyService currencyService) {

        this.userService = userService;
        this.rateNBService = rateNBService;
        this.rateCBService = rateCBService;
        this.currencyService = currencyService;
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@RequestBody @Valid UserDTO user) {
        userService.add(user);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/currencies")
    public String getCurrenciesInCashPage(Model model) throws NotFoundAllUsersException {
        List<CurrencyDTO> currencyDTO = currencyService.getCurrencies();
        model.addAttribute("currencyDTO", currencyDTO);
        return "api/currencies";
    }
    @GetMapping("/ratenb")
    public String getRateNBInCashPage(Model model) throws NotFoundAllUsersException {
        List<RateNBDTO> rateNBDTO = rateNBService.getRatesNB();
        model.addAttribute("rateNBDTO", rateNBDTO);
        return "api/ratenb";
    }
    @GetMapping("/ratecb")
    public String getRateCBInCashPage(Model model) throws NotFoundAllUsersException {
        List<RateCBDTO> rateCBDTO = rateCBService.getRatesCB();
        model.addAttribute("rateCBDTO", rateCBDTO);
        return "api/ratecb";
    }


    @PostMapping("/ratenb")
    public ResponseEntity saveRateNB(@RequestBody @Valid RateNBDTO rateNBDTO) {
        rateNBService.add(rateNBDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/ratecb")
    public ResponseEntity saveRateCB(@RequestBody @Valid RateCBDTO rateCBDTO) {
        rateCBService.add(rateCBDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/currencies")
    public ResponseEntity saveCurrency(@RequestBody @Valid CurrencyDTO currencyDTO) {
        currencyService.add(currencyDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/ratenb/{id}")
    public String getRateNBByIdInCashPage(@ModelAttribute(value = "id") Long id,
                                      Model model) throws NotFoundAllUsersException {
        RateNBDTO rateNBDTO = rateNBService.getUserById(id);
        model.addAttribute("rateNBDTO", rateNBDTO);
        return "api/ratenb/id";
    }

    @GetMapping("/ratecb/{id}")
    public String getRateCBByIdInCashPage(@ModelAttribute(value = "id") Long id,
                                          Model model) throws NotFoundAllUsersException {
        RateCBDTO rateCBDTO = rateCBService.getUserById(id);
        model.addAttribute("rateCBDTO", rateCBDTO);
        return "api/ratecb/id";
    }

    @GetMapping("/currency/{id}")
    public String getCurrencyByIdInCashPage(@ModelAttribute(value = "id") Long id,
                                          Model model) throws NotFoundAllUsersException {
        CurrencyDTO currencyDTO = currencyService.getUserById(id);
        model.addAttribute("currencyDTO", currencyDTO);
        return "api/currency/id";
    }

    @DeleteMapping("/ratenb/{id}")
    public ResponseEntity deleteRateNB(@ModelAttribute(value = "id") Long id,
                                     Model model) {
        RateNBDTO rateNBDTO = rateNBService.getUserById(id);
        rateNBDTO.setNotActive(false);
        rateNBService.update(rateNBDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/ratecb/{id}")
    public ResponseEntity deleteRateCB(@ModelAttribute(value = "id") Long id,
                                     Model model) {
        RateCBDTO rateCBDTO = rateCBService.getUserById(id);
        rateCBDTO.setIsNotActive(false);
        rateCBService.update(rateCBDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/currency/{id}")
    public ResponseEntity deleteCurrency(@ModelAttribute(value = "id") Long id,
                                       Model model) {
        CurrencyDTO currencyDTO = currencyService.getUserById(id);
        currencyDTO.setIsNotActive(false);
        currencyService.update(currencyDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
