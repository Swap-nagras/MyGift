package com.capgemini.eGift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.eGift.entity.GiftRedeemDetails;
import com.capgemini.eGift.service.IGiftRedeemDetails;

@RestController
@CrossOrigin
public class GiftRedeemDetailsController {
	@Autowired
	IGiftRedeemDetails giftRedeemDetailsService;
	//endpoint for adding gift redeem details 
	@PostMapping("/giftRedeemDetails/add")
	ResponseEntity<GiftRedeemDetails> addGiftRedeemDetails(@RequestBody GiftRedeemDetails giftRedeemDetails1) {
		GiftRedeemDetails giftRedeemDetails = giftRedeemDetailsService.addGiftRedeemDetails(giftRedeemDetails1);
		return new ResponseEntity<>(giftRedeemDetails, HttpStatus.OK);
	}
	// end point for getting gift redeem details by id
	@GetMapping("/giftRedeemDetails/get/{giftRedeemDetailsId}")
	ResponseEntity<GiftRedeemDetails> getGiftRedeemDetailsById(@PathVariable int giftRedeemDetailsId){
		ResponseEntity<GiftRedeemDetails> re = new ResponseEntity<GiftRedeemDetails>(giftRedeemDetailsService.getGiftRedeemDetailsById(giftRedeemDetailsId), HttpStatus.OK);
		return re;
	}
	// end point for deleting gift redeem details by id
	@DeleteMapping("/giftRedeemDetails/delete/{giftRedeemDetailsId}")
	ResponseEntity<GiftRedeemDetails> deleteGiftRedeemDetails(@PathVariable int giftRedeemDetailsId) {
		ResponseEntity<GiftRedeemDetails> re = new ResponseEntity<GiftRedeemDetails>(giftRedeemDetailsService.deleteGiftRedeemDetailsById(giftRedeemDetailsId), HttpStatus.OK);
		return re;
	}
	//end point for changing the attributes of gift redeem details by id
	@PatchMapping("giftRedeemDetails/update")
	ResponseEntity<GiftRedeemDetails> updateGiftRedeemDetails( @RequestBody GiftRedeemDetails giftRedeemDetails) {
		return new ResponseEntity<GiftRedeemDetails>(giftRedeemDetailsService.updateGiftRedeemDetails(giftRedeemDetails), HttpStatus.OK);
	}
	// end point for getting all gift redeem details
	@GetMapping("giftRedeemDetails/all")
	ResponseEntity<List<GiftRedeemDetails>> getAllGiftRedeemDetails(){
		return new ResponseEntity<>(giftRedeemDetailsService.getAllGiftRedeemDetails(), HttpStatus.OK);
	}

}