package com.ecom.gg.ecomgg.controller;

import com.ecom.gg.ecomgg.dto.RegisterUser;
import com.ecom.gg.ecomgg.entity.ApplicationUser;
import com.ecom.gg.ecomgg.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ApplicationUser register(@RequestBody RegisterUser registerUser){

        return authenticationService.register(registerUser.fullName(),registerUser.email(), registerUser.password());
    }
}
