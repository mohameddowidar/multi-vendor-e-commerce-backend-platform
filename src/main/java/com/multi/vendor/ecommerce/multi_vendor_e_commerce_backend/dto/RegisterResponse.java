package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto;

import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.enums.Role;

public class RegisterResponse {
    private Long id;
    private String email;
    private Role role;

    public RegisterResponse(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
