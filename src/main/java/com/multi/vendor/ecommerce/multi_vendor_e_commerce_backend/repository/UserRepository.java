package com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.repository;

import com.multi.vendor.ecommerce.multi_vendor_e_commerce_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    List<User> findAllByEmail(String email);
    @Query("select a from User a where a.email like '%:email%' ")
    List<User> getALlUsersByEmail(@Param("email") String email);
    @Query(value = "select a from User a where a.full_name= :firstName ", nativeQuery = true)
    List<User> getALlUsersByFirstNameNative(String firstName);

    Optional<User> findByEmail(String email);

}
