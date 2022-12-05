package com.capgemini.eGift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.eGift.entity.PaymentDetails;
@Repository
public interface IPaymentDetailsRepo extends JpaRepository<PaymentDetails, Integer> {

}
