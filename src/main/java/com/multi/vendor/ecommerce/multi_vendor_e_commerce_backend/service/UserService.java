package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.service;

import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto.LoginRequest;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto.LoginResponse;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto.RegisterRequest;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto.RegisterResponse;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.entity.User;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.enums.Role;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.exceptions.UserEmailExistsException;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.repository.UserRepository;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.util.PasswordHashUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordHashUtil passwordHashUtil;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       PasswordHashUtil passwordHashUtil) {
        this.userRepository = userRepository;
        this.passwordHashUtil = passwordHashUtil;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) throws UserEmailExistsException {
        // 1. business rule
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserEmailExistsException("Email already exists");
        }

        // 2. map DTO → Entity
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordHashUtil.hashPassword(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setRole(
                (request.getRole() != null && !"".equalsIgnoreCase(request.getRole().toString())) ?
                        request.getRole() : Role.CUSTOMER
        );
        user.setCreatedBy(0);
        user.setUpdatedBy(0);
        user.setActive(true);

        // 3. save
        User savedUser = userRepository.save(user);

        // 4. response
        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    public LoginResponse login(LoginRequest request) {
        String hasedPass = passwordHashUtil.hashPassword(request.getPassword());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new RuntimeException("User is inactive");
        }

        if (!hasedPass.equals(user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }


        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
