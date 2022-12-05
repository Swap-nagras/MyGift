package com.capgemini.eGift.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.eGift.entity.GiftReceivedDetails;
import com.capgemini.eGift.entity.UserEntity;
import com.capgemini.eGift.exceptions.ResourceNotFoundException;
import com.capgemini.eGift.repository.IGiftReceivedDetailsRepo;

@Service
public class GiftReceivedDetailsImpl implements IGiftReceivedDetails {
	@Autowired
	IGiftReceivedDetailsRepo grdRepo;
	
	//add received gift details
	@Override
	public GiftReceivedDetails generateGiftReceivedDetails(GiftReceivedDetails giftRecievedDetails) {
		return grdRepo.save(giftRecievedDetails);
	}

	//finding gift received details by giftID
	@Override
	public GiftReceivedDetails getGiftReceivedDetailsBygiftRecievedId(int giftRecievedId) {
		Optional<GiftReceivedDetails> opt = grdRepo.findById(giftRecievedId);
		GiftReceivedDetails grd = null;
		if (opt.isPresent()) {
			grd = opt.get();
		} else {
			throw new ResourceNotFoundException("Gift Details for the given ID not found:" + giftRecievedId);
		}
		return grd;
	}
	
	//finding gift received details by received date
	@Override
	public List<GiftReceivedDetails> getGiftReceivedDetailsBygiftRecievedDate(LocalDate giftRecievedDate) {
		//check if the returned list is not empty
		if (!grdRepo.findByGiftRecievedDate(giftRecievedDate).isEmpty()) {
			return grdRepo.findByGiftRecievedDate(giftRecievedDate);
		} else {
			throw new ResourceNotFoundException(
					"Gift Details for the given date not found:" + giftRecievedDate);
		}

	}
	
	//finding gift redeem status by giftID
	@Override
	public boolean giftRedeemStatusBygiftRecievedId(int giftRecievedId) {
		Optional<GiftReceivedDetails> opt = grdRepo.findById(giftRecievedId);
		GiftReceivedDetails grd = null;
		if (opt.isPresent()) {
			grd = opt.get();
		} else {
			throw new ResourceNotFoundException(
					"Gift redeem status for the given ID not found:" + giftRecievedId);
		}
		return grd.isGiftRedeemStatus();
	}

	//finding gift received details by userID
	@Override
	public List<GiftReceivedDetails> getGiftReceivedDetailsByUserId(int userId) {
		// check if the returned list is not empty
		if (!grdRepo.findByUserId(userId).isEmpty()) {
			return grdRepo.findByUserId(userId);
		} else {
			throw new ResourceNotFoundException("Gift Status for the given userID not found:" + userId);
		}

	}

}
