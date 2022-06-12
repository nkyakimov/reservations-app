package com.app.reservation.web.rest.controller;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import com.app.reservation.service.UserServiceImpl;
import com.app.reservation.web.rest.resource.UserResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void register(@RequestBody @Valid UserResource userResource) {
        userService.register(userResource);
    }

}
