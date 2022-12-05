package com.capgemini.eGift.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.eGift.entity.GiftReceivedDetails;

@Repository
public interface IGiftReceivedDetailsRepo extends JpaRepository<GiftReceivedDetails, Integer> {

	List<GiftReceivedDetails> findByGiftRecievedDate(LocalDate giftRecievedDate);

	List<GiftReceivedDetails> findByUserId(int userId);

}
