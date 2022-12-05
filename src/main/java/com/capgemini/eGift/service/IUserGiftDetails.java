package com.capgemini.eGift.service;

import java.util.List;

import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.entity.PaymentDetails;
import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.entity.UserGiftDetails;



public interface IUserGiftDetails {
	UserGiftDetails addUserGiftDetails(UserGiftDetails userGiftDetails);

	UserGiftDetails getUserGiftDetailsById(int userGiftDetailsId);

	UserGiftDetails deleteUserGiftDetailsById(int userGiftDetailsId);

	UserGiftDetails updateUserGiftDetails( UserGiftDetails userGiftDetails);

	List<UserGiftDetails> getAllUserGiftDetails();
	
	UserGiftDetails updateUserGiftDetailsAmount( UserGiftDetails userGiftDetails, double amount);
	
	UserGiftDetails updateUserGiftDetailsGiftCard( UserGiftDetails userGiftDetails, GiftCard giftCard);
	
	UserGiftDetails updateUserGiftDetailsPersonalize( UserGiftDetails userGiftDetails, Personalize personalize);
	
	UserGiftDetails updateUserGiftDetailsPayment( UserGiftDetails userGiftDetails, PaymentDetails paymentDetails);
	
	UserGiftDetails placeGiftOrder(GiftCard giftCard, double amount, PaymentDetails paymentDetails,Personalize personalize);
}
