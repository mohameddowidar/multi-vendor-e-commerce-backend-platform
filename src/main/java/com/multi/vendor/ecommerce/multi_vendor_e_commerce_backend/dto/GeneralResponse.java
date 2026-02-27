package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.dto;

public class GeneralResponse<T> {
    private T body;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
