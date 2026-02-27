package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto;

import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "email is required")
    @Email
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min= 6, max = 8, message = "Password must not exceed 8 characters")
    private String password;
    @NotBlank
    private String fullName;
    private Role role;

    public RegisterRequest(String email, String password, String fullName, Role role) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
