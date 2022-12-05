package com.capgemini.eGift.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.eGift.entity.GiftCard;
import com.capgemini.eGift.repository.IGiftCardRepo;
import com.capgemini.eGift.service.GiftCardImpl;
@ExtendWith(SpringExtension.class)
class GiftCardServiceTest {
	@InjectMocks
	GiftCardImpl giftCardService;

	@MockBean
	IGiftCardRepo giftCardRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void addGiftCardByTest() {
		GiftCard giftCard = new GiftCard();
		giftCard.setGiftCardId(0);
		giftCard.setGiftCardName("birthday");
		Mockito.when(giftCardRepo.save(giftCard)).thenReturn(giftCard);
		GiftCard response = giftCardService.addGiftCard(giftCard);
		assertEquals(0, response.getGiftCardId());
		assertEquals("birthday", response.getGiftCardName());
		
	}
	@Test
	void getGiftCardByIdTest() {
		GiftCard giftCard = new GiftCard();
		giftCard.setGiftCardId(0);
		giftCard.setGiftCardName("birthday");
		Mockito.when(giftCardRepo.findById(0)).thenReturn(Optional.of(giftCard));
		GiftCard response = giftCardService.getGiftCardById(0);
		assertEquals(0, response.getGiftCardId());
		assertEquals("birthday", response.getGiftCardName());
	}
	@Test
	void deleteGiftCardByTest() {
		GiftCard giftCard = new GiftCard();
		giftCard.setGiftCardId(0);
		giftCard.setGiftCardName("birthday");
		Mockito.when(giftCardRepo.findById(0)).thenReturn(Optional.of(giftCard));
		GiftCard response = giftCardService.deleteGiftCardById(0);
		assertEquals(0, response.getGiftCardId());
		assertEquals("birthday", response.getGiftCardName());
		
	}
	@Test
	void updateGiftCardByIdTest() {
		GiftCard giftCard1 = new GiftCard();
		giftCard1.setGiftCardId(0);
		giftCard1.setGiftCardName("birthday");
		GiftCard giftCard2 = new GiftCard();
		giftCard2.setGiftCardId(0);
		giftCard2.setGiftCardName("anniversary");
		Mockito.when(giftCardRepo.findById(0)).thenReturn(Optional.of(giftCard2));
		Mockito.when(giftCardService.updateGiftCard(giftCard2)).thenReturn(giftCard2);
		GiftCard response = giftCardService.getGiftCardById(0);
		assertEquals(0, response.getGiftCardId());
		assertEquals("anniversary", response.getGiftCardName());
	}
	@Test
	void getAllUserEntitiesTest() {
		GiftCard giftCard1 = new GiftCard();
		giftCard1.setGiftCardId(0);
		giftCard1.setGiftCardName("birthday");
		GiftCard giftCard2 = new GiftCard();
		giftCard2.setGiftCardId(1);
		giftCard2.setGiftCardName("anniversary");
		List<GiftCard> giftCardList = new ArrayList<>();
		giftCardList.add(giftCard1);
		giftCardList.add(giftCard2);
		
		Mockito.when(giftCardRepo.findAll()).thenReturn(giftCardList);
		
		List<GiftCard> newGiftCardLst = giftCardService.getAllGiftCards();
		assertEquals(2, newGiftCardLst.size());
		assertEquals("birthday", newGiftCardLst.get(0).getGiftCardName());
	}

}
