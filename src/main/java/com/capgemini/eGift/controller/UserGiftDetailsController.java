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

import com.capgemini.eGift.dto.UserGiftDetailsInputDto;
import com.capgemini.eGift.dto.UserGiftDetailsOutputDto;
import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.entity.GiftReceivedDetails;
import com.capgemini.eGift.entity.PaymentDetails;
import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.entity.UserGiftDetails;
import com.capgemini.eGift.service.IUserGiftDetailsService;
import com.capgemini.eGift.service.IUserService;

@RestController
@CrossOrigin

public class UserGiftDetailsController {
	@Autowired
	IUserGiftDetailsService userGiftDetailsService;
	// adding new user gift details by dto
	@PostMapping("/userGiftDetails/placeOrder/dto/{userId}")
	ResponseEntity<UserGiftDetailsOutputDto> addUserGiftDetailsDto(@PathVariable int userId,@RequestBody UserGiftDetailsInputDto userGiftDetailsInputDto) {
		UserGiftDetailsOutputDto newuserGiftDetails = userGiftDetailsService.placeOrderDto(userGiftDetailsInputDto,userId);
		return new ResponseEntity<UserGiftDetailsOutputDto>(newuserGiftDetails, HttpStatus.OK);
	}
	// adding new user gift details
	@PostMapping("/userGiftDetails/add")
	ResponseEntity<UserGiftDetails> addUserGiftDetails(@RequestBody UserGiftDetails userGiftDetails) {
		UserGiftDetails newuserGiftDetails = userGiftDetailsService.addUserGiftDetails(userGiftDetails);
		return new ResponseEntity<>(userGiftDetails, HttpStatus.OK);
	}
	// get user gift details by id
	@GetMapping("/userGiftDetails/get/{userGiftDetailsId}")
	ResponseEntity<UserGiftDetails> getUserGiftDetailsById(@PathVariable int userGiftDetailsId) {
		ResponseEntity<UserGiftDetails> re = new ResponseEntity<UserGiftDetails>(
				userGiftDetailsService.getUserGiftDetailsById(userGiftDetailsId), HttpStatus.OK);
		return re;
	}
	// delete user gift details by id
	@DeleteMapping("/userGiftDetails/delete/{userGiftDetailsId}")
	ResponseEntity<UserGiftDetails> deleteUserGiftDetails(@PathVariable int userGiftDetailsId) {
		ResponseEntity<UserGiftDetails> re = new ResponseEntity<UserGiftDetails>(
				userGiftDetailsService.deleteUserGiftDetailsById(userGiftDetailsId), HttpStatus.OK);
		return re;
	}
	// end point to change attributes of user gift details by id
	@PatchMapping("userGiftDetails/update")
	ResponseEntity<UserGiftDetails> updateUserGiftDetails(@RequestBody UserGiftDetails userGiftDetails) {
		return new ResponseEntity<UserGiftDetails>(userGiftDetailsService.updateUserGiftDetails(userGiftDetails),
				HttpStatus.OK);
	}

	// end point to get all user gift details
	@GetMapping("userGiftDetails/all")
	ResponseEntity<List<UserGiftDetails>> getAllUserEntities() {
		return new ResponseEntity<>(userGiftDetailsService.getAllUserGiftDetails(), HttpStatus.OK);
	}
	// end point to order a gift
	@PatchMapping("userGiftDetails/placeOrder/{userId}")
	ResponseEntity<UserGiftDetails> placeGiftOrder(@PathVariable int userId,
			@RequestBody UserGiftDetails userGiftDetails) {
		UserGiftDetails newUserGiftDetails = userGiftDetailsService.placeGiftOrder(userId, userGiftDetails);
		return new ResponseEntity<>(newUserGiftDetails, HttpStatus.OK);

	}
}
