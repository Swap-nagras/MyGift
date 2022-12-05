package com.capgemini.eGift.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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

import com.capgemini.eGift.entity.GiftReceivedDetails;
import com.capgemini.eGift.repository.IGiftReceivedDetailsRepo;
import com.capgemini.eGift.service.GiftReceivedDetailsImpl;

@ExtendWith(SpringExtension.class)
class GiftReceivedDetailsServiceTest {

	@InjectMocks
	GiftReceivedDetailsImpl grdServ;

	@MockBean
	IGiftReceivedDetailsRepo grdRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void generateGiftReceivedDetailsTest() {

		GiftReceivedDetails grd = new GiftReceivedDetails();
		grd.setGiftRecievedId(1000);
		LocalDate ld = LocalDate.of(2022, 11, 15);
		grd.setGiftRecievedDate(ld);
		grd.setGiftRedeemStatus(false);
		grd.setUserId(100);

		Mockito.when(grdRepo.save(grd)).thenReturn(grd);

		GiftReceivedDetails newGrd = grdServ.generateGiftReceivedDetails(grd);

		assertEquals(1000, newGrd.getGiftRecievedId());
		assertEquals(ld, newGrd.getGiftRecievedDate());
		assertEquals(false, newGrd.isGiftRedeemStatus());
		assertEquals(100, newGrd.getUserId());

	}

	@Test
	void getGiftReceivedDetailsBygiftRecievedIdTest() {

		GiftReceivedDetails grd = new GiftReceivedDetails();
		grd.setGiftRecievedId(1000);
		LocalDate ld = LocalDate.of(2022, 11, 15);
		grd.setGiftRecievedDate(ld);
		grd.setGiftRedeemStatus(false);
		grd.setUserId(100);

		Mockito.when(grdRepo.findById(1000)).thenReturn(Optional.of(grd));
		GiftReceivedDetails response = grdServ.getGiftReceivedDetailsBygiftRecievedId(1000);

		assertEquals(1000, response.getGiftRecievedId());
		assertEquals(ld, response.getGiftRecievedDate());
		assertEquals(false, response.isGiftRedeemStatus());
		assertEquals(100, response.getUserId());
	}

	@Test
	void getGiftReceivedDetailsBygiftRecievedDateTest() {
		GiftReceivedDetails grd = new GiftReceivedDetails();
		grd.setGiftRecievedId(1000);
		LocalDate ld = LocalDate.of(2022, 11, 15);
		grd.setGiftRecievedDate(ld);
		grd.setGiftRedeemStatus(false);
		grd.setUserId(100);

		List<GiftReceivedDetails> grdList = new ArrayList<>();
		grdList.add(grd);

		Mockito.when(grdRepo.findByGiftRecievedDate(ld)).thenReturn(grdList);

		List<GiftReceivedDetails> grdLst = grdServ.getGiftReceivedDetailsBygiftRecievedDate(ld);
		assertEquals(1, grdLst.size());
		assertEquals(1000, grdLst.get(0).getGiftRecievedId());
		assertEquals(ld, grdLst.get(0).getGiftRecievedDate());
		assertEquals(false, grdLst.get(0).isGiftRedeemStatus());
		assertEquals(100, grdLst.get(0).getUserId());

	}

	@Test
	void giftRedeemStatusBygiftRecievedIdTest() {
		GiftReceivedDetails grd = new GiftReceivedDetails();
		grd.setGiftRecievedId(1000);
		grd.setGiftRedeemStatus(false);
		
		Mockito.when(grdRepo.findById(1000)).thenReturn(Optional.of(grd));
		
		boolean flag = grdServ.giftRedeemStatusBygiftRecievedId(1000);
		assertEquals(flag, grd.isGiftRedeemStatus());
	}

	@Test
	void getGiftReceivedDetailsByUserId() {
		GiftReceivedDetails grd = new GiftReceivedDetails();
		grd.setGiftRecievedId(1000);
		LocalDate ld = LocalDate.of(2022, 11, 15);
		grd.setGiftRecievedDate(ld);
		grd.setGiftRedeemStatus(false);
		grd.setUserId(100);
		
		List<GiftReceivedDetails> grdList = new ArrayList<>();
		grdList.add(grd);
		
		Mockito.when(grdRepo.findByUserId(100)).thenReturn(grdList);
		List<GiftReceivedDetails> grdLst = grdServ.getGiftReceivedDetailsByUserId(100);
		assertEquals(1, grdLst.size());
		assertEquals(1000, grdLst.get(0).getGiftRecievedId());
		assertEquals(ld, grdLst.get(0).getGiftRecievedDate());
		assertEquals(false, grdLst.get(0).isGiftRedeemStatus());
		assertEquals(100, grdLst.get(0).getUserId());

	}

}
