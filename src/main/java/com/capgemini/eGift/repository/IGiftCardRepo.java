package com.capgemini.eGift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.eGift.entity.GiftCard;
@Repository
public interface IGiftCardRepo extends JpaRepository<GiftCard, Integer> {

}
