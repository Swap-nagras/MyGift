package com.capgemini.eGift.service;

import com.capgemini.eGift.dto.LoginInputDto;
import com.capgemini.eGift.dto.LoginOutputDto;
import com.capgemini.eGift.entity.Login;

public interface ILoginService {

	//login
	Login login(Login login);
	
	//get login details by userId
	Login getLoginByUserId(int userId);
	
	//logout
	LoginOutputDto logout(String email);

	Login loginByDto(LoginInputDto loginInputDto);

}
