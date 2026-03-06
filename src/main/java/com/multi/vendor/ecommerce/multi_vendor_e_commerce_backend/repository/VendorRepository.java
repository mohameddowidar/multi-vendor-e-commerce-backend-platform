package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.repository;

import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.entity.VendorProfile;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.enums.VendorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<VendorProfile, Long> {

    Optional<VendorProfile> findByUserId(Long userId);
    Optional<VendorProfile> findByIdAndStatus(Long id, VendorStatus status);

}