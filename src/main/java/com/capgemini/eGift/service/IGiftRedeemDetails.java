package com.capgemini.eGift.service;

import java.util.List;

import com.capgemini.eGift.entity.GiftRedeemDetails;

public interface IGiftRedeemDetails {
	GiftRedeemDetails addGiftRedeemDetails(GiftRedeemDetails giftRedeemDetails);

	GiftRedeemDetails getGiftRedeemDetailsById(int giftRedeemDetailsId);

	GiftRedeemDetails deleteGiftRedeemDetailsById(int giftRedeemDetailsId);

	GiftRedeemDetails updateGiftRedeemDetails(GiftRedeemDetails giftRedeemDetails);

	List<GiftRedeemDetails> getAllGiftRedeemDetails();
}
