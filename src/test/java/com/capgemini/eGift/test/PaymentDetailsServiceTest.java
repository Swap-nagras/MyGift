package com.capgemini.eGift.test;

import static org.junit.jupiter.api.Assertions.*;

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

import com.capgemini.eGift.entity.PaymentDetails;
import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.repository.IPaymentDetailsRepo;
import com.capgemini.eGift.service.PaymentDetailsImpl;

@ExtendWith(SpringExtension.class)
class PaymentDetailsServiceTest {

	@InjectMocks
	PaymentDetailsImpl paymentDetailsService;

	@MockBean
	IPaymentDetailsRepo paymentDetailsRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getPaymentDetailsByIdTest1() {
		PaymentDetails paymentDetails = new PaymentDetails();
		paymentDetails.setPaymentId(0);
		paymentDetails.setNameOnCard("Swapnil");

		Mockito.when(paymentDetailsRepo.findById(0)).thenReturn(Optional.of(paymentDetails));

		PaymentDetails response = paymentDetailsService.getPaymentDetailsById(0);
		assertEquals("Swapnil", response.getNameOnCard());
		assertEquals(0, response.getPaymentId());
	}

	@Test
	void addPaymentDetails() {
		PaymentDetails paymentDetails = new PaymentDetails();
		paymentDetails.setPaymentId(0);
		paymentDetails.setNameOnCard("Swapnil");
		Mockito.when(paymentDetailsRepo.save(paymentDetails)).thenReturn(paymentDetails);
		PaymentDetails newPaymentDetails = paymentDetailsService.addPaymentDetails(paymentDetails);
		assertEquals("Swapnil", newPaymentDetails.getNameOnCard());
		assertEquals(0, newPaymentDetails.getPaymentId());
	}

	@Test
	void deletePaymentDetailsById() {
		PaymentDetails paymentDetails = new PaymentDetails();
		paymentDetails.setPaymentId(0);
		paymentDetails.setNameOnCard("Swapnil");
		Mockito.when(paymentDetailsRepo.findById(0)).thenReturn(Optional.of(paymentDetails));
		PaymentDetails newPaymentDetails = paymentDetailsService.deletePaymentDetailsById(0);
		assertEquals("Swapnil", newPaymentDetails.getNameOnCard());
		assertEquals(0, newPaymentDetails.getPaymentId());
	}

	@Test
	void updatePaymentDetails() {
		PaymentDetails paymentDetails = new PaymentDetails();
		paymentDetails.setPaymentId(0);
		paymentDetails.setNameOnCard("Swapnil");
		PaymentDetails newPaymentDetails = new PaymentDetails();
		newPaymentDetails.setPaymentId(0);
		newPaymentDetails.setNameOnCard("Swapnil Nagras");
		Mockito.when(paymentDetailsRepo.findById(0)).thenReturn(Optional.of(paymentDetails));
		Mockito.when(paymentDetailsRepo.save(paymentDetails)).thenReturn(paymentDetails);
		PaymentDetails response = paymentDetailsService.updatePaymentDetails(newPaymentDetails);
		assertEquals("Swapnil Nagras", response.getNameOnCard());
		assertEquals(0, response.getPaymentId());
	}

	@Test
	void getAllPaymentDetails() {
		PaymentDetails paymentDetails1 = new PaymentDetails();
		paymentDetails1.setPaymentId(0);
		paymentDetails1.setNameOnCard("Swapnil");
		PaymentDetails paymentDetails2 = new PaymentDetails();
		paymentDetails2.setPaymentId(2);
		paymentDetails2.setNameOnCard("Snehal");
		List<PaymentDetails> list = new ArrayList<>();
		list.add(paymentDetails1);
		list.add(paymentDetails2);

		Mockito.when(paymentDetailsRepo.findAll()).thenReturn(list);
		List<PaymentDetails> newList = paymentDetailsService.getAllPaymentDetails();
		assertEquals(2, newList.size());
		assertEquals("Snehal", newList.get(1).getNameOnCard());
	}

}
