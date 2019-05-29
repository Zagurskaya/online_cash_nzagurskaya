package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RestApiController {

    private static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    private final UserService userService;
//    private final RateNBService rateNBService;
//    private final RateCBService rateCBService;

    //    @Autowired
//    public RestApiController(UserService userService, RateNBService rateNBService, RateCBService rateCBService) {
//
//        this.userService = userService;
//        this.rateNBService = rateNBService;
//        this.rateCBService = rateCBService;
//    }
    @Autowired
    public RestApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@RequestBody @Valid UserDTO user) {
        userService.add(user);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @PostMapping("/ratenb")
//    public ResponseEntity saveRateNB(@RequestBody @Valid RateNBDTO rateNBDTO) {
//        rateNBService.add(rateNBDTO);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @PostMapping("/ratecb")
//    public ResponseEntity saveRateCB(@RequestBody @Valid RateCBDTO rateCBDTO) {
//        rateCBService.add(rateCBDTO);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
