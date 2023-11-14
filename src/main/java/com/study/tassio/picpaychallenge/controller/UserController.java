package com.study.tassio.picpaychallenge.controller;

import com.study.tassio.picpaychallenge.model.dto.request.BalanceRequest;
import com.study.tassio.picpaychallenge.model.dto.request.UserRequest;
import com.study.tassio.picpaychallenge.model.dto.response.UserResponse;
import com.study.tassio.picpaychallenge.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return this.userService.createUser(userRequest);
    }

    @PatchMapping(value = "/{userId}")
    public UserResponse addMoneyToUser(@PathVariable String userId, @RequestBody BalanceRequest balanceRequest){
        return this.userService.addMoneyToUser(userId, balanceRequest);
    }
}
