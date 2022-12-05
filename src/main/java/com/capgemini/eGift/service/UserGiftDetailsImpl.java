package com.capgemini.eGift.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.eGift.dto.UserEntityOutputDto;
import com.capgemini.eGift.dto.UserGiftDetailsInputDto;
import com.capgemini.eGift.dto.UserGiftDetailsOutputDto;
import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.entity.GiftReceivedDetails;
import com.capgemini.eGift.entity.Login;
import com.capgemini.eGift.entity.PaymentDetails;
import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.entity.UserEntity;
import com.capgemini.eGift.entity.UserGiftDetails;
import com.capgemini.eGift.exceptions.InvalidEntryException;
import com.capgemini.eGift.exceptions.ResourceNotFoundException;
import com.capgemini.eGift.repository.IUserGiftDetailsRepo;
import com.capgemini.eGift.repository.IUserRepo;

@Service
public class UserGiftDetailsImpl implements IUserGiftDetailsService {
	@Autowired
	IUserGiftDetailsRepo userGiftDetailsRepo;
	@Autowired
	IGiftReceivedDetails grdService;
	
	@Autowired
	IUserService userService;
	//creating and adding new user gift details into database 
	//Remove later
	@Override
	public UserGiftDetails addUserGiftDetails(UserGiftDetails userGiftDetails) {
		UserGiftDetails newUserGiftDetails = userGiftDetailsRepo.save(userGiftDetails);
		return newUserGiftDetails;
	}
	//finding user gift details from database by id
	@Override
	public UserGiftDetails getUserGiftDetailsById(int userGiftDetailsId) {
		Optional<UserGiftDetails> opt = userGiftDetailsRepo.findById(userGiftDetailsId);
		UserGiftDetails userGiftDetails=null;
		if(opt.isPresent()) {
			userGiftDetails=opt.get();
		}
		return userGiftDetails;
	}
	//deleting user gift details from database by id
	@Override
	public UserGiftDetails deleteUserGiftDetailsById(int userGiftDetailsId) {
		Optional<UserGiftDetails> opt = userGiftDetailsRepo.findById(userGiftDetailsId);
		UserGiftDetails userGiftDetails=null;
		if(opt.isPresent()) {
			userGiftDetails=opt.get();
			userGiftDetailsRepo.deleteById(userGiftDetailsId);
	       }else throw new ResourceNotFoundException("user gift details with id not found " + userGiftDetailsId);
		return userGiftDetails;
	}
	//finding and updating user gift details from database by id
	@Override
	public UserGiftDetails updateUserGiftDetails(UserGiftDetails newUserGiftDetails) {
		Optional<UserGiftDetails> opt = userGiftDetailsRepo.findById(newUserGiftDetails.getUserGiftDetailsId());
        if (opt.isPresent()) {
            userGiftDetailsRepo.save(newUserGiftDetails);
       }		else throw new ResourceNotFoundException("user gift details with id not found " + newUserGiftDetails.getUserGiftDetailsId());

		return newUserGiftDetails;
	}
	//finding all user gift details from database
	@Override
	public List<UserGiftDetails> getAllUserGiftDetails() {
		List<UserGiftDetails> userGiftDetailsList = userGiftDetailsRepo.findAll();
		return userGiftDetailsList;
	}
	
	//finding user gift details from database by id and changing the amount
	@Override
	public UserGiftDetails updateUserGiftDetailsAmount(UserGiftDetails userGiftDetails, double amount) {
		userGiftDetails.setGiftCardAmount(amount);
		return userGiftDetails;
	}
	//finding user gift details from database by id and changing gift card
	@Override
	public UserGiftDetails updateUserGiftDetailsGiftCard(UserGiftDetails userGiftDetails, GiftCard giftCard) {
		// TODO Auto-generated method stub
		userGiftDetails.setGiftCard(giftCard);
		return userGiftDetails;
	}
	//finding user gift details from database by id and personalizing it
	@Override
	public UserGiftDetails updateUserGiftDetailsPersonalize(UserGiftDetails userGiftDetails, Personalize personalize) {
		userGiftDetails.setPersonalize(personalize);
		return userGiftDetails;
	}
	//finding user gift details from database by id and making new payment
	@Override
	public UserGiftDetails updateUserGiftDetailsPayment(UserGiftDetails userGiftDetails,
			PaymentDetails paymentDetails) {
		userGiftDetails.getPaymentDetails().add(paymentDetails);
		return userGiftDetails;
	}
	//placing a new order
	@Override
	public UserGiftDetails placeGiftOrder(int userId, UserGiftDetails userGiftDetails){
		userGiftDetails.getGiftReceivedDetails().get(userGiftDetails.getGiftReceivedDetails().size()-1).setUserId(userId);
		UserEntity user = userService.getUserEntityById(userId);
		user.getUserGiftDetails().add(userGiftDetails); 
		//userGiftDetails.getGiftReceivedDetails().add(giftReceivedDetails);
		//user.getUserGiftDetails().get(user.getUserGiftDetails().size()-1).getGiftReceivedDetails().get(user.getUserGiftDetails().get(user.getUserGiftDetails().size()-1).getGiftReceivedDetails().size()-1).setUserId(userId);
		
		//user.getUserGiftDetails().get(user.getUserGiftDetails().size()-1).getPaymentDetails().get(user.getUserGiftDetails().get(user.getUserGiftDetails().size()-1).getPaymentDetails().size()-1).set
		UserGiftDetails newUserGiftDetails = userGiftDetailsRepo.save(userGiftDetails);
		return newUserGiftDetails;
	}
	public UserGiftDetails reloadGiftCard(int userGiftDetailsId,PaymentDetails paymentDetails) {
		UserGiftDetails userGiftDetails= getUserGiftDetailsById(userGiftDetailsId);
		if(userGiftDetails.isReloadable()) {
			userGiftDetails.getPaymentDetails().add(paymentDetails);

		}else throw new InvalidEntryException("the current gift card is not reloadable");
		return userGiftDetails;
	}
	@Override
	public UserGiftDetailsOutputDto placeOrderDto(UserGiftDetailsInputDto userGiftDetailsInputDto, int userId) {
		UserEntity user= userService.getUserEntityById(userId);
		UserGiftDetails userGiftDetails = new UserGiftDetails();
		userGiftDetails.setGiftCardAmount(userGiftDetailsInputDto.getGiftCardAmount());
		userGiftDetails.setDeliveryType(userGiftDetailsInputDto.getDeliveryType());
		userGiftDetails.setGiftCard(userGiftDetailsInputDto.getGiftCard());
		userGiftDetails.setReloadable(userGiftDetailsInputDto.isReloadable());
		userGiftDetails.setScheduleDelivery(userGiftDetailsInputDto.getScheduleDelivery());
		user.getUserGiftDetails().add(userGiftDetails);
		userGiftDetails.setRecipientEmail(user.getLogin().getEmail());
		userGiftDetails.setRecipientName(user.getFirstName());
		UserEntity newUser= userService.updateUserEntity(user);
//		UserGiftDetails newUserGiftDetails= userGiftDetailsRepo.save(userGiftDetails);
	
		// converting userEntity to UserEntityOutputDto type
		UserGiftDetailsOutputDto newUserGiftDetailsOutputDto = new UserGiftDetailsOutputDto();
		newUserGiftDetailsOutputDto.setGiftCardAmount(userGiftDetails.getGiftCardAmount());		
		newUserGiftDetailsOutputDto.setDeliveryType(userGiftDetails.getDeliveryType());
		newUserGiftDetailsOutputDto.setGiftCard(userGiftDetails.getGiftCard());
		newUserGiftDetailsOutputDto.setReloadable(userGiftDetails.isReloadable());
		newUserGiftDetailsOutputDto.setScheduleDelivery(userGiftDetails.getScheduleDelivery());
		newUserGiftDetailsOutputDto.setRecipientEmail(userGiftDetails.getRecipientEmail());
		newUserGiftDetailsOutputDto.setRecipientName(userGiftDetails.getRecipientName());
		return newUserGiftDetailsOutputDto;
	}
	
}
