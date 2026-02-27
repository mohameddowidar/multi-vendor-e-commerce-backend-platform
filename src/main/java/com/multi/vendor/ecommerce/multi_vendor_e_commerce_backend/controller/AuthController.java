package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.controller;

import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto.*;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.exceptions.UserEmailExistsException;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse<RegisterResponse>> register(
            @Valid @RequestBody RegisterRequest request) throws UserEmailExistsException {

        RegisterResponse response = userService.register(request);
        GeneralResponse<RegisterResponse> generalResponse = new GeneralResponse<>();
        generalResponse.setBody(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(generalResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        GeneralResponse<LoginResponse> generalResponse = new GeneralResponse<>();
        generalResponse.setBody(response);
        return ResponseEntity.ok(response);
    }
}