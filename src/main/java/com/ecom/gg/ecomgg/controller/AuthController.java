package com.ecom.gg.ecomgg.controller;

import com.ecom.gg.ecomgg.dto.RegisterUser;
import com.ecom.gg.ecomgg.entity.ApplicationUser;
import com.ecom.gg.ecomgg.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Rest API for Auth", description = "Signup Authentication Register Mapping.")
@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @Operation(summary = "Register User With Authentication", description = "Save User Information To Associated PostgreSQL database with Authentication.")
    @PostMapping("/register")
    @ApiResponse(responseCode = "201", description = "JSON Must Be Same Format With RegisterUser DTO")
    public ApplicationUser register( @RequestBody RegisterUser registerUser){

        return authenticationService.register(registerUser.fullName(),registerUser.email(), registerUser.password());
    }
}
