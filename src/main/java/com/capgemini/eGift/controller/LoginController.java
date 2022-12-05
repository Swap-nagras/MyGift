package com.capgemini.eGift.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.eGift.dto.LoginInputDto;
import com.capgemini.eGift.dto.LoginOutputDto;
import com.capgemini.eGift.entity.Login;
import com.capgemini.eGift.service.ILoginService;

@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	ILoginService loginServ;
	
	// end-point for login
	@PostMapping("/login")
	ResponseEntity<Login> login(@Valid @RequestBody Login login) {
		Login log = loginServ.login(login);
		return new ResponseEntity<>(log, HttpStatus.OK);
	}
	// end-point for login with dto
		@PostMapping("/login/dto")
		ResponseEntity<Login> login(@Valid @RequestBody LoginInputDto loginInputDto) {
			Login log = loginServ.loginByDto(loginInputDto);
			return new ResponseEntity<>(log, HttpStatus.OK);
		}
	// end-point for logout
	@GetMapping("/logout/{email}")
	ResponseEntity<LoginOutputDto> logout(@PathVariable("email") String email) {
		LoginOutputDto outputDto = loginServ.logout(email);
		return new ResponseEntity<>(outputDto, HttpStatus.OK);
	}
	
	// end-point for finding login details by userId
	@GetMapping("/login/getLogin/byUserId/{userId}")
	ResponseEntity<Login> getLoginByUserId(@PathVariable int userId) {
		Login login = loginServ.getLoginByUserId(userId);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

}
