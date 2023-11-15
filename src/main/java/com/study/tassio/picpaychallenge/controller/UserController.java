package com.study.tassio.picpaychallenge.controller;

import com.study.tassio.picpaychallenge.model.dto.request.BalanceRequest;
import com.study.tassio.picpaychallenge.model.dto.request.UserRequest;
import com.study.tassio.picpaychallenge.model.dto.response.UserResponse;
import com.study.tassio.picpaychallenge.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Users", description = "Users documentation")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "Creates a new user.")
    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return this.userService.createUser(userRequest);
    }

    @Operation(summary = "Deposits money into an user account.")
    @PatchMapping(value = "/{userId}")
    public UserResponse addMoneyToUser(@PathVariable String userId, @RequestBody BalanceRequest balanceRequest){
        return this.userService.addMoneyToUser(userId, balanceRequest);
    }
}
