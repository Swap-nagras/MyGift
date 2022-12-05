package com.capgemini.eGift.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.exceptions.ResourceNotFoundException;
import com.capgemini.eGift.repository.IGiftCardRepo;

@Service
public class GiftCardImpl implements IGiftCard {
	@Autowired
	IGiftCardRepo giftCardRepo;
	// create and adding a new gift card
	@Override
	public GiftCard addGiftCard(GiftCard giftCard) {
		GiftCard newgiftCard = giftCardRepo.save(giftCard);
		return giftCard;
	}
	// get a grft card from database from id
	@Override
	public GiftCard getGiftCardById(int giftCardId) {
		Optional<GiftCard> opt = giftCardRepo.findById(giftCardId);
		GiftCard giftCard=null;
		if(opt.isPresent()) {
			giftCard=opt.get();
		} else {
			throw new ResourceNotFoundException("Gift card not found with id:"+giftCardId);
		}
		return giftCard;
	}
	// delete gift card from database by id
	@Override
	public GiftCard deleteGiftCardById(int giftCardId) {
		Optional<GiftCard> opt = giftCardRepo.findById(giftCardId);
		GiftCard giftCard=null;
		if(opt.isPresent()) {
			giftCard=opt.get();
			giftCardRepo.deleteById(giftCardId);
		} else {
			throw new ResourceNotFoundException("Gift card not found with id:"+giftCardId);
		}
		return giftCard;
	}
	// updating gift card from database by id
	@Override
	public GiftCard updateGiftCard(GiftCard newGiftCard) {
		Optional<GiftCard> opt = giftCardRepo.findById(newGiftCard.getGiftCardId());
        if (opt.isPresent()) {
        	giftCardRepo.save(newGiftCard);
       } else {
			throw new ResourceNotFoundException("Gift card not found with id:"+newGiftCard.getGiftCardId());
		}
		return newGiftCard;
	}
	// get all gift card from database
	@Override
	public List<GiftCard> getAllGiftCards() {
		List<GiftCard> giftCardList = giftCardRepo.findAll();
		return giftCardList;
	}

}
