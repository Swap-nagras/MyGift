package com.capgemini.eGift.dto;

import java.util.List;

import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.entity.PaymentDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGiftDetailsOutputDto {
	private double giftCardAmount;
	private String recipientName, recipientEmail,deliveryType, scheduleDelivery;
	private boolean isReloadable;
	private List<PaymentDetails> paymentDetails;
	private GiftCard giftCard;
	
}
