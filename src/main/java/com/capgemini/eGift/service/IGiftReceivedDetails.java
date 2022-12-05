package com.capgemini.eGift.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.eGift.entity.GiftReceivedDetails;
import com.capgemini.eGift.entity.UserEntity;

public interface IGiftReceivedDetails {

	//add gift received details
	GiftReceivedDetails generateGiftReceivedDetails(GiftReceivedDetails giftRecievedDetails);
	
	//find gift received details by giftId
	GiftReceivedDetails getGiftReceivedDetailsBygiftRecievedId(int giftRecievedId);
	
	//find gift received details by received date
	List<GiftReceivedDetails> getGiftReceivedDetailsBygiftRecievedDate(LocalDate giftRecievedDate);

	//find gift redeem status by giftId
	boolean giftRedeemStatusBygiftRecievedId(int giftRecievedId);

	//find gift received details by user ID
	List<GiftReceivedDetails> getGiftReceivedDetailsByUserId(int userId);

}
