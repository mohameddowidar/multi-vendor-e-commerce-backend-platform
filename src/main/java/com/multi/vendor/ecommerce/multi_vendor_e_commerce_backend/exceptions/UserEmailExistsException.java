package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.exceptions;

public class UserEmailExistsException extends Exception{
    public UserEmailExistsException(String message){
        super(message);
    }
}
