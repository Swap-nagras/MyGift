package com.capgemini.eGift.service;

import java.util.List;

import com.capgemini.eGift.entity.GiftCard;

public interface IGiftCard {
	GiftCard addGiftCard(GiftCard giftCard);
	GiftCard getGiftCardById(int giftCardId);
	GiftCard deleteGiftCardById(int giftCardId);
	GiftCard updateGiftCard(GiftCard giftCard);
	List<GiftCard> getAllGiftCards();
}
