package com.capgemini.eGift.service;

import java.util.List;

import com.capgemini.eGift.dto.UserEntityInputDto;
import com.capgemini.eGift.dto.UserEntityOutputDto;
import com.capgemini.eGift.entity.GiftRedeemDetails;
import com.capgemini.eGift.entity.UserEntity;
import com.capgemini.eGift.entity.UserGiftDetails;

public interface IUserService {

	UserEntity getUserEntityById(int userId);
	
	List<UserEntity> getUserEntityByFirstName(String firstName);
	
	List<UserEntity> getUserEntityByLastName(String lastName);
	
	UserEntity getUserEntityByEmail(String email);

	UserEntity deleteUserEntityById(int userId);

	UserEntity updateUserEntity( UserEntity userEntity);
	
	List<UserEntity> getAllUserEntities();
	
	UserEntity forgotUserEntityPassword(String password, String lastName, String email);

	UserEntityOutputDto addUser(UserEntityInputDto userDto);


	UserEntityOutputDto getUserProfileById(int userId);

	List<UserGiftDetails> getOrderHistory(int userId);

	GiftRedeemDetails redeemGiftOrder(int userGiftDetailsId, int amount);

	UserEntity updateUserEntityPassword(UserEntity user, String password);
	
}
