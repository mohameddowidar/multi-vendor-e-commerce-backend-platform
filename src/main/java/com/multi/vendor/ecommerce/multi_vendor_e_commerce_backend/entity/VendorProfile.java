package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.entity;
import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.enums.VendorStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Objects;

@Entity
@Table(name = "vendor_profiles")
public class VendorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    private String description;

    @Enumerated(EnumType.STRING)
    private VendorStatus status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VendorStatus getStatus() {
        return status;
    }

    public void setStatus(VendorStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorProfile that = (VendorProfile) o;
        return Objects.equals(id, that.id)
                && Objects.equals(storeName, that.storeName)
                && Objects.equals(description, that.description)
                && status == that.status && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storeName, description, status, user);
    }
}