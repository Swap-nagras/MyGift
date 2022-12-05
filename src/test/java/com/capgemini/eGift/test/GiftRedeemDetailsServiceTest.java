package com.capgemini.eGift.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.eGift.entity.GiftRedeemDetails;
import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.repository.IGiftRedeemDetailsRepo;
import com.capgemini.eGift.repository.IPersonalizeRepo;
import com.capgemini.eGift.service.GiftRedeemDetailsImpl;
import com.capgemini.eGift.service.PersonalizeImpl;

@ExtendWith(SpringExtension.class)
class GiftRedeemDetailsServiceTest {

	@InjectMocks
	GiftRedeemDetailsImpl giftRedeemDetailsService;

	@MockBean
	IGiftRedeemDetailsRepo giftRedeemDetailsRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getGiftRedeemDetailsById() {
		GiftRedeemDetails giftRedeemDetails = new GiftRedeemDetails();
		giftRedeemDetails.setGiftRedeemId(0);
		giftRedeemDetails.setRedeemStatus(true);
		LocalDate ld = LocalDate.of(2022, 11, 15);
		giftRedeemDetails.setRedeemDate(ld);
		giftRedeemDetails.setRedeemAmount(400.0);
		giftRedeemDetails.setBalance(600.0);
		Mockito.when(giftRedeemDetailsRepo.findById(0)).thenReturn(Optional.of(giftRedeemDetails));
		GiftRedeemDetails response = giftRedeemDetailsService.getGiftRedeemDetailsById(0);
		assertEquals(true, response.isRedeemStatus());
		assertEquals(0, response.getGiftRedeemId());
		assertEquals(400.0, response.getRedeemAmount());
		assertEquals(600.0, response.getBalance());
	}

	@Test
	void addPersonalizeDetails() {
		GiftRedeemDetails giftRedeemDetails = new GiftRedeemDetails();
		giftRedeemDetails.setGiftRedeemId(0);
		giftRedeemDetails.setRedeemStatus(true);
		LocalDate ld = LocalDate.of(2022, 11, 15);
		giftRedeemDetails.setRedeemDate(ld);
		giftRedeemDetails.setRedeemAmount(400.0);
		giftRedeemDetails.setBalance(600.0);
		Mockito.when(giftRedeemDetailsRepo.save(giftRedeemDetails)).thenReturn(giftRedeemDetails);
		GiftRedeemDetails response = giftRedeemDetailsService.addGiftRedeemDetails(giftRedeemDetails);
		assertEquals(true, response.isRedeemStatus());
		assertEquals(0, response.getGiftRedeemId());
		assertEquals(400.0, response.getRedeemAmount());
		assertEquals(600.0, response.getBalance());
	}

	@Test
	void deletePersonalizeById() {

		GiftRedeemDetails giftRedeemDetails = new GiftRedeemDetails();
		giftRedeemDetails.setGiftRedeemId(0);
		giftRedeemDetails.setRedeemStatus(true);
		LocalDate ld = LocalDate.of(2022, 11, 15);
		giftRedeemDetails.setRedeemDate(ld);
		giftRedeemDetails.setRedeemAmount(400.0);
		giftRedeemDetails.setBalance(600.0);
		Mockito.when(giftRedeemDetailsRepo.findById(0)).thenReturn(Optional.of(giftRedeemDetails));
		GiftRedeemDetails response = giftRedeemDetailsService.deleteGiftRedeemDetailsById(0);
		assertEquals(true, response.isRedeemStatus());
		assertEquals(0, response.getGiftRedeemId());
		assertEquals(400.0, response.getRedeemAmount());
		assertEquals(600.0, response.getBalance());

	}

	@Test
	void updatePersonalizeDetails() {

		GiftRedeemDetails giftRedeemDetails = new GiftRedeemDetails();
		giftRedeemDetails.setGiftRedeemId(0);
		giftRedeemDetails.setRedeemStatus(true);
		LocalDate ld = LocalDate.of(2022, 11, 15);
		giftRedeemDetails.setRedeemDate(ld);
		giftRedeemDetails.setRedeemAmount(400.0);
		giftRedeemDetails.setBalance(600.0);
		Mockito.when(giftRedeemDetailsRepo.findById(0)).thenReturn(Optional.of(giftRedeemDetails));
		Mockito.when(giftRedeemDetailsRepo.save(giftRedeemDetails)).thenReturn(giftRedeemDetails);
		GiftRedeemDetails response = giftRedeemDetailsService.updateGiftRedeemDetails(giftRedeemDetails);
		assertEquals(true, response.isRedeemStatus());
		assertEquals(0, response.getGiftRedeemId());
		assertEquals(400.0, response.getRedeemAmount());
		assertEquals(600.0, response.getBalance());
	}

	@Test
	void getAllPaymentDetails() {
		GiftRedeemDetails giftRedeemDetails1 = new GiftRedeemDetails();
		giftRedeemDetails1.setGiftRedeemId(0);
		giftRedeemDetails1.setRedeemStatus(true);
		LocalDate ld1 = LocalDate.of(2022, 11, 15);
		giftRedeemDetails1.setRedeemDate(ld1);
		giftRedeemDetails1.setRedeemAmount(400.0);
		giftRedeemDetails1.setBalance(600.0);
		GiftRedeemDetails giftRedeemDetails2 = new GiftRedeemDetails();
		giftRedeemDetails2.setGiftRedeemId(1);
		giftRedeemDetails2.setRedeemStatus(true);
		LocalDate ld2 = LocalDate.of(2022, 11, 04);
		giftRedeemDetails2.setRedeemDate(ld2);
		giftRedeemDetails2.setRedeemAmount(500.0);
		giftRedeemDetails2.setBalance(500.0);
		List<GiftRedeemDetails> list = new ArrayList<>();
		list.add(giftRedeemDetails1);
		list.add(giftRedeemDetails2);
		Mockito.when(giftRedeemDetailsRepo.findAll()).thenReturn(list);
		List<GiftRedeemDetails> newList = giftRedeemDetailsService.getAllGiftRedeemDetails();
		assertEquals(2, newList.size());
		assertEquals(true, newList.get(1).isRedeemStatus());
		assertEquals(0, newList.get(0).getGiftRedeemId());
		assertEquals(400.0, newList.get(0).getRedeemAmount());
		assertEquals(500.0, newList.get(1).getBalance());

	}

}
