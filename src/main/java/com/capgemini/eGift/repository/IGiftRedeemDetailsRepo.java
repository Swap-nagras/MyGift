package com.capgemini.eGift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.eGift.entity.GiftRedeemDetails;
@Repository
public interface IGiftRedeemDetailsRepo extends JpaRepository<GiftRedeemDetails, Integer> {

}
