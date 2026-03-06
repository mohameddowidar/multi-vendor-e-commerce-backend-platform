package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.service;

import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.entity.User;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.entity.VendorProfile;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.enums.VendorStatus;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.repository.UserRepository;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.repository.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;

    public VendorService(VendorRepository vendorRepository,
                         UserRepository userRepository) {

        this.vendorRepository = vendorRepository;
        this.userRepository = userRepository;
    }

    public VendorProfile createVendorProfile(Long userId,
                                             String storeName,
                                             String description) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        VendorProfile vendor = new VendorProfile();

        vendor.setStoreName(storeName);
        vendor.setDescription(description);
        vendor.setStatus(VendorStatus.PENDING);
        vendor.setUser(user);

        return vendorRepository.save(vendor);
    }
    public VendorProfile approveVendor(Long vendorId) {
        VendorProfile vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        if (vendor.getStatus() != VendorStatus.PENDING) {
            throw new RuntimeException("Vendor already processed");
        }

        vendor.setStatus(VendorStatus.APPROVED);
        return vendorRepository.save(vendor);
    }
    public VendorProfile rejectVendor(Long vendorId) {
        VendorProfile vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        if (vendor.getStatus() != VendorStatus.PENDING) {
            throw new RuntimeException("Vendor already processed");
        }

        vendor.setStatus(VendorStatus.REJECTED);
        return vendorRepository.save(vendor);
    }
}