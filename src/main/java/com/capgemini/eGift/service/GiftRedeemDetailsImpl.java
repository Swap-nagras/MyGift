package com.capgemini.eGift.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.entity.GiftRedeemDetails;
import com.capgemini.eGift.repository.IGiftCardRepo;
import com.capgemini.eGift.repository.IGiftRedeemDetailsRepo;

@Service
public class GiftRedeemDetailsImpl implements IGiftRedeemDetails {
	@Autowired
	IGiftRedeemDetailsRepo giftRedeemDetailsRepo;
	//creating and adding new gift redeem details into database
	@Override
	public GiftRedeemDetails addGiftRedeemDetails(GiftRedeemDetails giftRedeemDetails) {
		GiftRedeemDetails newGiftRedeemDetails = giftRedeemDetailsRepo.save(giftRedeemDetails);
		return newGiftRedeemDetails;
	}
	//finding gift redeem details from database by id
	@Override
	public GiftRedeemDetails getGiftRedeemDetailsById(int giftRedeemDetailsId) {
		Optional<GiftRedeemDetails> opt = giftRedeemDetailsRepo.findById(giftRedeemDetailsId);
		GiftRedeemDetails giftRedeemDetails=null;
		if(opt.isPresent()) {
			giftRedeemDetails=opt.get();
		}
		return giftRedeemDetails;
	}
	//deleting gift redeem details from database by id
	@Override
	public GiftRedeemDetails deleteGiftRedeemDetailsById(int GiftRedeemDetailsId) {
		Optional<GiftRedeemDetails> opt = giftRedeemDetailsRepo.findById(GiftRedeemDetailsId);
		GiftRedeemDetails giftRedeemDetails=null;
		if(opt.isPresent()) {
			giftRedeemDetails=opt.get();
			giftRedeemDetailsRepo.deleteById(GiftRedeemDetailsId);
		}
		return giftRedeemDetails;
	}
	
	//finding and updating gift redeem details from database by id
	@Override
	public GiftRedeemDetails updateGiftRedeemDetails(GiftRedeemDetails newGiftRedeemDetails) {
		Optional<GiftRedeemDetails> opt = giftRedeemDetailsRepo.findById(newGiftRedeemDetails.getGiftRedeemId());
        if (opt.isPresent()) {
        	giftRedeemDetailsRepo.save(newGiftRedeemDetails);
       }
		return newGiftRedeemDetails;
	}
	// 	//finding all gift redeem details from database
	@Override
	public List<GiftRedeemDetails> getAllGiftRedeemDetails() {
		List<GiftRedeemDetails> giftRedeemDetailsList = giftRedeemDetailsRepo.findAll();
		return giftRedeemDetailsList;
	}
}
