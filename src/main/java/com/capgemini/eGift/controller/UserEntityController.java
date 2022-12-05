package com.capgemini.eGift.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.capgemini.eGift.dto.UserEntityInputDto;
import com.capgemini.eGift.dto.UserEntityOutputDto;
import com.capgemini.eGift.entity.UserEntity;
import com.capgemini.eGift.entity.UserGiftDetails;
import com.capgemini.eGift.service.IUserService;

@RestController
@CrossOrigin

public class UserEntityController {
	@Autowired
	IUserService userService;

	// end point for registering new user
	@PostMapping("/user/add/dto")
	ResponseEntity<UserEntityOutputDto> addUser(@Valid @RequestBody UserEntityInputDto user) {
		UserEntityOutputDto newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	// end point for getting user details by id 
	@GetMapping("/user/get/{userId}")
	ResponseEntity<UserEntity> getUserEntityById(@PathVariable int userId) {
		ResponseEntity<UserEntity> re = new ResponseEntity<UserEntity>(userService.getUserEntityById(userId),
				HttpStatus.OK);
		return re;
	}
	// end point for getting user profile by id after login
		@GetMapping("/user/getProfile/{userId}")
		ResponseEntity<UserEntityOutputDto> getUserProfileById(@PathVariable int userId) {
			ResponseEntity<UserEntityOutputDto> re = new ResponseEntity<UserEntityOutputDto>(userService.getUserProfileById(userId),
					HttpStatus.OK);
			return re;
		}
	// end point for deleting a user by id
	@DeleteMapping("/user/delete/{userId}")
	ResponseEntity<UserEntity> deleteUserEntity(@PathVariable int userId) {
		ResponseEntity<UserEntity> re = new ResponseEntity<UserEntity>(userService.deleteUserEntityById(userId),
				HttpStatus.OK);
		return re;
	}
	// end point for changing the details of user by id
	@PutMapping("user/update")
	ResponseEntity<UserEntity> updateUserEntity(@RequestBody UserEntity userEntity) {
		return new ResponseEntity<UserEntity>(userService.updateUserEntity(userEntity), HttpStatus.OK);
	}
	// end point for getting all the user in the form of a list
	@GetMapping("user/all")
	ResponseEntity<List<UserEntity>> getAllUserEntities() {
		return new ResponseEntity<>(userService.getAllUserEntities(), HttpStatus.OK);
	}
	// endpoint for changing user password after logging in
	@PatchMapping("user/updatePassword/{userId}")
	ResponseEntity<UserEntity> updateUserEntityPassword(@PathVariable int userId ,@RequestBody String password) {
		UserEntity user= userService.getUserEntityById(userId);
		return new ResponseEntity<UserEntity>(userService.updateUserEntityPassword(user, password), HttpStatus.OK);
	}
	//endpoint to reset passowrd without login 
	@PatchMapping("user/forgotPassword")
	ResponseEntity<UserEntity> updateUserEntityForgotPassword(@RequestBody String password, String lastName, String email) {
		//UserEntity user= userService.getUserEntityById(userId);
		return new ResponseEntity<UserEntity>(userService.forgotUserEntityPassword(password,lastName,email), HttpStatus.OK);
	}
	@GetMapping("user/{userId}/getOrderHistory")
	ResponseEntity<List<UserGiftDetails>> getOrderHistory(@PathVariable int userId) {
		return new ResponseEntity<List<UserGiftDetails>>(userService.getOrderHistory(userId), HttpStatus.OK);
	}
}
