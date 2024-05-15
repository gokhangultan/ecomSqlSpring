package com.ecom.gg.ecomgg.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Rest API for Admin", description = "This URL Will Be Used For Admin Panel")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Operation(summary = "Authentication Mapping For Admin Area", description = "This Mapping URL is Reserved For The Users Whom Type Is ADMIN")
    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "If You Will Get 401 or 403 Codes Please Contact With System Admin For Authorization Your Credentials")

    public String admin(){
        return "Admin Area";
    }
}
