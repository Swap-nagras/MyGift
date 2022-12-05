package com.capgemini.eGift.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.eGift.dto.LoginInputDto;
import com.capgemini.eGift.dto.LoginOutputDto;
import com.capgemini.eGift.entity.Login;
import com.capgemini.eGift.exceptions.LoginException;
import com.capgemini.eGift.repository.ILoginRepo;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	ILoginRepo loginRepo;
	//logging in
	@Override
	public Login login(Login login) {
		// find the credentials in the database
		Optional<Login> opt = loginRepo.findById(login.getEmail());
		Login dbLogin = null;
		if (opt.isPresent()) {

			dbLogin = opt.get();

			// compare the credentials to those in the database
			if (login.getPassword().equals(dbLogin.getPassword()) && login.getRole().toLowerCase().equals(dbLogin.getRole().toLowerCase())) {
				// set the login status flag
				dbLogin.setLogin(true);
				dbLogin = loginRepo.save(dbLogin);
			}else {
				// throw an exception if the match is not found
				throw new LoginException("Invalid Credentials");
			}
		} else {
			// throw an exception if the match is not found
			throw new LoginException("Invalid Credentials");
		}
		return dbLogin;

	}
	//login by dto
	@Override
	public Login loginByDto(LoginInputDto loginInputDto) {
		Login login= convertDtoToLogin(loginInputDto);
		// find the credentials in the database
		Optional<Login> opt = loginRepo.findById(login.getEmail());
		Login dbLogin = null;
		if (opt.isPresent()) {

			dbLogin = opt.get();

			// compare the credentials to those in the database
			if (login.getPassword().equals(dbLogin.getPassword()) && login.getRole().toLowerCase().equals(dbLogin.getRole().toLowerCase())) {
				// set the login status flag
				dbLogin.setLogin(true);
				dbLogin = loginRepo.save(dbLogin);
			}else {
				// throw an exception if the match is not found
				throw new LoginException("Invalid Credentials");
			}
		} else {
			// throw an exception if the match is not found
			throw new LoginException("Invalid Credentials");
		}
		return dbLogin;

	}
	// logging out
	@Override
	public LoginOutputDto logout(String email) {
		// find the credentials in the database
		Optional<Login> opt = loginRepo.findById(email);

		LoginOutputDto outputDto = null;

		// fetch the login details from the database
		if (opt.isPresent()) {

			Login dbLogin = opt.get();
			// clear the login status flag
			dbLogin.setLogin(false);
			Login login = loginRepo.save(dbLogin);

			outputDto = new LoginOutputDto();
			outputDto.setEmail(login.getEmail());
			outputDto.setLogin(login.isLogin());
			outputDto.setRole(login.getRole());
		} else {
			// throw an exception if login details not found
			throw new LoginException("Invalid Attempt");
		}

		return outputDto;
	}
	// getting login info of a user( only acessible to admin)
	@Override
	public Login getLoginByUserId(int userId) {

		// find login details in the database by userId
		Optional<Login> opt = loginRepo.getLoginByUserId(userId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			// throw an exception if userID not found
			throw new LoginException("Invalid user ID");
		}

	}
	public Login convertDtoToLogin(LoginInputDto loginInputDto) {
		Login login= new Login();
		login.setEmail(loginInputDto.getEmail());
		login.setPassword(loginInputDto.getPassword());
		login.setRole(loginInputDto.getRole());
		login.setLogin(false);
		return login;
	}

}
