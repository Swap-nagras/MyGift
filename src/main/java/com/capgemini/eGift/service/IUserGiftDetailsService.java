package com.capgemini.eGift.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.capgemini.eGift.dto.UserGiftDetailsInputDto;
import com.capgemini.eGift.dto.UserGiftDetailsOutputDto;
import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.entity.GiftReceivedDetails;
import com.capgemini.eGift.entity.PaymentDetails;
import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.entity.UserGiftDetails;



public interface IUserGiftDetailsService {
	UserGiftDetails addUserGiftDetails(UserGiftDetails userGiftDetails);

	UserGiftDetails getUserGiftDetailsById(int userGiftDetailsId);

	UserGiftDetails deleteUserGiftDetailsById(int userGiftDetailsId);

	UserGiftDetails updateUserGiftDetails( UserGiftDetails userGiftDetails);

	List<UserGiftDetails> getAllUserGiftDetails();
	
	UserGiftDetails updateUserGiftDetailsAmount( UserGiftDetails userGiftDetails, double amount);
	
	UserGiftDetails updateUserGiftDetailsGiftCard( UserGiftDetails userGiftDetails, GiftCard giftCard);
	
	UserGiftDetails updateUserGiftDetailsPersonalize( UserGiftDetails userGiftDetails, Personalize personalize);
	
	UserGiftDetails updateUserGiftDetailsPayment( UserGiftDetails userGiftDetails, PaymentDetails paymentDetails);
	
	UserGiftDetails placeGiftOrder(int userId, UserGiftDetails userGiftDetails);

	UserGiftDetailsOutputDto placeOrderDto(UserGiftDetailsInputDto userGiftDetailsInputDto, int userId);

}
