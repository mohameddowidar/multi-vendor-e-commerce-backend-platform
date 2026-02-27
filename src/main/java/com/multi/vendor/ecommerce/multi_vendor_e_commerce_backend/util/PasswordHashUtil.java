package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Component
public class PasswordHashUtil{

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8)
            );

            return bytesToHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error", e);
        }
    }

    public String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}