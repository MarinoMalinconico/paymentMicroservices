package com.companyName.paymentMicroservices.repository;

import com.companyName.paymentMicroservices.repository.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    @Query(value="SELECT * FROM payments WHERE FK_USER=:FkUser", nativeQuery = true)
    List<Payment> getAllPaymentPerUser(@Param("FkUser")String FkUser);

    List<Payment> findByfkUser(@Param("FkUser")String FkUser);

    List<Payment> findAll();

}
