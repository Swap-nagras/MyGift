package com.capgemini.eGift.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.eGift.entity.GiftReceivedDetails;
import com.capgemini.eGift.service.IGiftReceivedDetails;

@RestController
@CrossOrigin
public class GiftReceivedDetailsController {
	@Autowired
	IGiftReceivedDetails grdServ;

	// end-point for adding gift received details
	@PostMapping("/user/giftRecievedDetails/add")
	ResponseEntity<GiftReceivedDetails> generateGiftReceivedDetails(
			@RequestBody GiftReceivedDetails giftRecievedDetails) {
		GiftReceivedDetails grd = grdServ.generateGiftReceivedDetails(giftRecievedDetails);
		return new ResponseEntity<>(grd, HttpStatus.OK);
	}

	// end-point for finding gift received details by gift ID
	@GetMapping("/giftRecievedDetails/byId/{giftRecievedId}")
	ResponseEntity<GiftReceivedDetails> getGiftReceivedDetailsBygiftRecievedId(@PathVariable int giftRecievedId) {
		GiftReceivedDetails grd = grdServ.getGiftReceivedDetailsBygiftRecievedId(giftRecievedId);
		return new ResponseEntity<>(grd, HttpStatus.OK);
	}

	// end-point for finding gift received details by date
	@GetMapping("/giftRecievedDetails/byDate/{giftRecievedDate}")
	ResponseEntity<List<GiftReceivedDetails>> getGiftReceivedDetailsBygiftRecievedDate(
			@PathVariable String giftRecievedDate) {
		LocalDate myDate = LocalDate.parse(giftRecievedDate);
		List<GiftReceivedDetails> grd = grdServ.getGiftReceivedDetailsBygiftRecievedDate(myDate);
		return new ResponseEntity<>(grd, HttpStatus.OK);
	}

	// end-point for finding gift redeem status details by gift ID
	@GetMapping("/giftRedeemStatus/byId/{giftRecievedId}")
	ResponseEntity giftRedeemStatusBygiftRecievedId(@PathVariable int giftRecievedId) {
		boolean status = grdServ.giftRedeemStatusBygiftRecievedId(giftRecievedId);

		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	// end-point for finding gift received details by user ID
	@GetMapping("/giftRecievedDetails/byUserId/{userId}")
	ResponseEntity<List<GiftReceivedDetails>> getGiftReceivedDetailsByUserId(int userId) {
		List<GiftReceivedDetails> grd = grdServ.getGiftReceivedDetailsByUserId(userId);
		return new ResponseEntity<>(grd, HttpStatus.OK);
	}

}
