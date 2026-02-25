package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.repository;

import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
