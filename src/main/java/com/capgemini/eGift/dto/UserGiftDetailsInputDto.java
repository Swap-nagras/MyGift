package com.capgemini.eGift.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.entity.PaymentDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGiftDetailsInputDto {
	
	private double giftCardAmount;
	private String deliveryType, scheduleDelivery;
	private boolean isReloadable;
	private List<PaymentDetails> paymentDetails;
	private GiftCard giftCard;


}
