package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend;

import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto.RegisterRequest;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto.RegisterResponse;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.entity.User;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        // 1. business rule
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // 2. map DTO → Entity
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setRole(request.getRole());
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
}
