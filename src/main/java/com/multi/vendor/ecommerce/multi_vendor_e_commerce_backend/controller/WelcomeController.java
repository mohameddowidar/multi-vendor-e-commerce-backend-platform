package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("welcome")
public class WelcomeController {

    @GetMapping("msg")
    public ResponseEntity<?> welcomeStudentWithPathVariableApi() {
        return ResponseEntity.ok("Welcome to Advanced Java diploma");
    }

}
