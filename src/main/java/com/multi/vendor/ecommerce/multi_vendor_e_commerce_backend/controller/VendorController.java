package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.controller;

import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto.CreateVendorRequest;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.entity.VendorProfile;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.service.VendorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/create")
    public VendorProfile createVendor(@RequestBody CreateVendorRequest request) {

        return vendorService.createVendorProfile(
                request.getUserId(),
                request.getStoreName(),
                request.getDescription()
        );
    }
    @PatchMapping("/{id}/approve")
    public VendorProfile approveVendor(@PathVariable Long id) {
        return vendorService.approveVendor(id);
    }
    @PatchMapping("/{id}/reject")
    public VendorProfile rejectVendor(@PathVariable Long id) {
        return vendorService.rejectVendor(id);
    }
}