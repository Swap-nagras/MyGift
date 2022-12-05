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

import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.service.IGiftCard;
@RestController
@CrossOrigin
public class GiftCardController {
	@Autowired
	IGiftCard giftCardService;
	// endpoint for adding giftcard into giftcard list 
	@PostMapping("/giftCard/add")
	ResponseEntity<GiftCard> addGiftCard(@RequestBody GiftCard giftCard1) {
		GiftCard giftCard = giftCardService.addGiftCard(giftCard1);
		return new ResponseEntity<>(giftCard, HttpStatus.OK);
	}
	// endpoint for getting a gift card by ID
	@GetMapping("/giftCard/get/{giftCardId}")
	ResponseEntity<GiftCard> getGiftCardById(@PathVariable int giftCardId){
		ResponseEntity<GiftCard> re = new ResponseEntity<GiftCard>(giftCardService.getGiftCardById(giftCardId), HttpStatus.OK);
		return re;
	}
	// endpoint for deleting gift card
	@DeleteMapping("/giftCard/delete/{giftCardId}")
	ResponseEntity<GiftCard> deleteGiftCard(@PathVariable int giftCardId) {
		ResponseEntity<GiftCard> re = new ResponseEntity<GiftCard>(giftCardService.deleteGiftCardById(giftCardId), HttpStatus.OK);
		return re;
	}
	// end point for changing the attributes of the giftcard
	@PatchMapping("giftCard/update")
	ResponseEntity<GiftCard> updateGiftCard( @RequestBody GiftCard giftCard) {
		return new ResponseEntity<GiftCard>(giftCardService.updateGiftCard(giftCard), HttpStatus.OK);
	}
//	endpoint for getting the gift card list
	@GetMapping("giftCard/all")
	ResponseEntity<List<GiftCard>> getAllGiftCards(){
		return new ResponseEntity<>(giftCardService.getAllGiftCards(), HttpStatus.OK);
	}
}