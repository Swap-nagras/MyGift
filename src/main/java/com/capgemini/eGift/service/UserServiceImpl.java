package com.capgemini.eGift.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.eGift.dto.UserEntityInputDto;
import com.capgemini.eGift.dto.UserEntityOutputDto;
import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.entity.GiftReceivedDetails;
import com.capgemini.eGift.entity.GiftRedeemDetails;
import com.capgemini.eGift.entity.Login;
import com.capgemini.eGift.entity.PaymentDetails;
import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.entity.UserEntity;
import com.capgemini.eGift.entity.UserGiftDetails;
import com.capgemini.eGift.exceptions.InvalidEntryException;
import com.capgemini.eGift.exceptions.LoginException;
import com.capgemini.eGift.exceptions.ResourceNotFoundException;
import com.capgemini.eGift.repository.ILoginRepo;
import com.capgemini.eGift.repository.IUserRepo;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	IUserRepo userRepo;
	@Autowired
	ILoginRepo loginRepo;
	UserGiftDetailsImpl userGiftServ;
	// returns user entity
	@Override
	public UserEntity getUserEntityById(int userId) {
		Optional<UserEntity> opt = userRepo.findById(userId);
		UserEntity user = null;
		if (opt.isPresent()) {
			user = opt.get();
		} else
			throw new ResourceNotFoundException("user with id not found " + userId);
		return user;
	}
	// deletes user entity
	@Override
	public UserEntity deleteUserEntityById(int userId) {
		Optional<UserEntity> opt = userRepo.findById(userId);
		UserEntity user = null;
		if (opt.isPresent()) {
			user = opt.get();
			userRepo.deleteById(userId);
			
		} else
			throw new ResourceNotFoundException("user with id not found " + userId);
		return user;
	}
	// updates user entity
	@Override
	public UserEntity updateUserEntity(UserEntity newUser) {
		Optional<UserEntity> opt = userRepo.findById(newUser.getUserId());
		if (opt.isPresent()) {
			userRepo.save(newUser);
		} else
			throw new ResourceNotFoundException("user with id not found " + newUser.getUserId());
		return newUser;
	}
	// returns a list of all users
	@Override
	public List<UserEntity> getAllUserEntities() {
		List<UserEntity> userList = userRepo.findAll();
		return userList;
	}
	// returns list of user entities with the same first name
	@Override
	public List<UserEntity> getUserEntityByFirstName(String firstName) {
		List<UserEntity> userList = userRepo.findByFirstName(firstName);
		return userList;
	}
	//getting list of user entities with the same last name
	@Override
	public List<UserEntity> getUserEntityByLastName(String lastName) {
		List<UserEntity> userList = userRepo.findByLastName(lastName);
		return userList;
	}
	// finding user by email
	@Override
	public UserEntity getUserEntityByEmail(String email) {
		Optional<Login> optLogin = loginRepo.getLoginByEmail(email);
		if(optLogin.isPresent()) {
			Login login = optLogin.get();
		}else throw new ResourceNotFoundException("No user with that email");
		UserEntity user = userRepo.findByLogin(optLogin.get());
		System.out.println(email);
		return user;
	}
	// changind user password afyer logging in
	@Override
	public UserEntity updateUserEntityPassword(UserEntity user, String password) {
		Optional<Login> optLogin=loginRepo.findById(user.getLogin().getEmail());
		if(optLogin.isPresent()) {
			Login userLogin=optLogin.get();
			//System.out.println("login found");
			if(userLogin.isLogin()) {
				//System.out.println("logged in");
				user.getLogin().setPassword(password);
				userRepo.save(user);
				//System.out.println("done");
				return user;
			}else throw new LoginException("user not logged in");
		}else throw new ResourceNotFoundException("user not found");
		
		
	}
	// returning user profile by id
	@Override
	public UserEntityOutputDto getUserProfileById(int userId) {
		UserEntity userEntity= getUserEntityById(userId);
		Optional<Login> optLogin=loginRepo.findById(userEntity.getLogin().getEmail());
		if(optLogin.isPresent()) {
			Login userLogin=optLogin.get();
			System.out.println("login found");
			if(userLogin.isLogin()) {
		UserEntityOutputDto uOutputDto= convertoUserEntityDto(userEntity);
		return uOutputDto;
				}else throw new LoginException("user not logged in");
			}else throw new ResourceNotFoundException("user not found");
		}
	//method to change user passowrd without logging in
	@Override
	public UserEntity forgotUserEntityPassword(String password, String lastName, String email) {
		UserEntity userEntity = getUserEntityByEmail(email);
		if (!(userEntity.getLastName().equals(lastName))) {
			throw new InvalidEntryException("invalid last name or email");
		}
		userEntity.getLogin().setPassword(password);
		userRepo.save(userEntity);
		return userEntity;
	}
	// endpoint to get order history of user
	@Override
	public List<UserGiftDetails> getOrderHistory(int userId) {
		Optional<UserEntity> opt = userRepo.findById(userId);
		UserEntity user = null;
		if (opt.isPresent()) {
			user = opt.get();
		}else throw new ResourceNotFoundException("user with id not found " + userId);
		Optional<Login> optLogin=loginRepo.findById(user.getLogin().getEmail());
		if(optLogin.isPresent()) {
			Login userLogin=optLogin.get();
			System.out.println("login found");
			if(userLogin.isLogin()) {
		return user.getUserGiftDetails();
				}else throw new LoginException("user not logged in");
			}else throw new ResourceNotFoundException("user not found");
		}

	@Override
	public UserEntityOutputDto addUser(UserEntityInputDto userDto) {
		// converting UserEntityInputDto to UserEntity

		UserEntity user = new UserEntity();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setMobile(userDto.getMobile());
		user.setAddress(userDto.getAddress());

		Login login = new Login();
		login.setEmail(userDto.getEmail());
		login.setPassword(userDto.getPassword());
		login.setRole(userDto.getRole());

		user.setLogin(login);
		Optional<Login> optLogin = loginRepo.getLoginByEmail(userDto.getEmail());
		if(!(optLogin.isPresent())){
			UserEntity newUser = userRepo.save(user);
		}
		else throw new InvalidEntryException("user already exists");
		// converting userEntity to UserEntityOutputDto type
		UserEntity newUser = user;
		UserEntityOutputDto userOutputDto = new UserEntityOutputDto();

		userOutputDto.setFirstName(newUser.getFirstName());
		userOutputDto.setLastName(newUser.getLastName());
		userOutputDto.setEmail(newUser.getLogin().getEmail());
		userOutputDto.setMobile(newUser.getMobile());
		userOutputDto.setAddress(newUser.getAddress());
		userOutputDto.setRole(newUser.getLogin().getRole());

		return userOutputDto;
	}
	// converting user entity to dto
	UserEntityOutputDto convertoUserEntityDto(UserEntity userEntity) {
		UserEntityOutputDto userOutputDto = new UserEntityOutputDto();

		userOutputDto.setFirstName(userEntity.getFirstName());
		userOutputDto.setLastName(userEntity.getLastName());
		userOutputDto.setEmail(userEntity.getLogin().getEmail());
		userOutputDto.setMobile(userEntity.getMobile());
		userOutputDto.setAddress(userEntity.getAddress());
		userOutputDto.setRole(userEntity.getLogin().getRole());

		return userOutputDto;
	}
	@Override
	public GiftRedeemDetails redeemGiftOrder(int userGiftDetailsId, int amount) {
		GiftRedeemDetails giftRedeemDetails= new GiftRedeemDetails();
		UserGiftDetails userGiftDetails= userGiftServ.getUserGiftDetailsById(userGiftDetailsId);
		giftRedeemDetails.setBalance(giftRedeemDetails.getBalance()-amount);
		giftRedeemDetails.setRedeemStatus(true);
		giftRedeemDetails.setRedeemAmount(amount);
		giftRedeemDetails.setRedeemDate(LocalDate.now());
		userGiftDetails.getGiftRedeemDetails().add(giftRedeemDetails);
		return giftRedeemDetails;
	}
	public UserGiftDetails sendGift(String email, UserGiftDetails userGiftDetails) {
		UserEntity userEntity= getUserEntityByEmail(email);
		GiftReceivedDetails giftReceivedDetails =new GiftReceivedDetails();
		giftReceivedDetails.setUserId(userEntity.getUserId());
		giftReceivedDetails.setGiftRecievedDate(LocalDate.now());
		userGiftDetails.getGiftReceivedDetails().add(giftReceivedDetails);
		return userGiftDetails;
	}
}
