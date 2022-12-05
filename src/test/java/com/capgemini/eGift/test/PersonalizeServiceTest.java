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

import com.capgemini.eGift.entity.PaymentDetails;
import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.repository.IPaymentDetailsRepo;
import com.capgemini.eGift.repository.IPersonalizeRepo;
import com.capgemini.eGift.service.PaymentDetailsImpl;
import com.capgemini.eGift.service.PersonalizeImpl;

@ExtendWith(SpringExtension.class)
class PersonalizeServiceTest {

	@InjectMocks
	PersonalizeImpl personalizeService;

	@MockBean
	IPersonalizeRepo personalizeRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getPersonalizeById() {
		Personalize personalize = new Personalize();
		personalize.setPersonalizeId(0);
		personalize.setPersonalizeType("Birthday");
		Mockito.when(personalizeRepo.findById(0)).thenReturn(Optional.of(personalize));
		Personalize response = personalizeService.getPersonalizeById(0);
		assertEquals("Birthday", response.getPersonalizeType());
		assertEquals(0, response.getPersonalizeId());
	}

	@Test
	void addPersonalizeDetails() {
		Personalize personalize = new Personalize();
		personalize.setPersonalizeId(0);
		personalize.setPersonalizeType("Birthday");
		Mockito.when(personalizeRepo.save(personalize)).thenReturn(personalize);
		Personalize response = personalizeService.addPersonalize(personalize);
		assertEquals("Birthday", response.getPersonalizeType());
		assertEquals(0, response.getPersonalizeId());
	}

	@Test
	void deletePersonalizeById() {
		Personalize personalize = new Personalize();
		personalize.setPersonalizeId(0);
		personalize.setPersonalizeType("Birthday");
		Mockito.when(personalizeRepo.findById(0)).thenReturn(Optional.of(personalize));
		Personalize response = personalizeService.deletePersonalizeById(0);
		assertEquals("Birthday", response.getPersonalizeType());
		assertEquals(0, response.getPersonalizeId());
	}

	@Test
	void updatePersonalizeDetails() {
		Personalize personalize = new Personalize();
		personalize.setPersonalizeId(0);
		personalize.setPersonalizeType("Birthday");
		Mockito.when(personalizeRepo.findById(0)).thenReturn(Optional.of(personalize));
		Mockito.when(personalizeRepo.save(personalize)).thenReturn(personalize);
		Personalize response = personalizeService.updatePersonalize(personalize);
		assertEquals("Birthday", response.getPersonalizeType());
		assertEquals(0, response.getPersonalizeId());
	}

	@Test
	void getAllPaymentDetails() {
		Personalize personalize1 = new Personalize();
		personalize1.setPersonalizeId(0);
		personalize1.setPersonalizeType("Birthday");
		Personalize personalize2 = new Personalize();
		personalize2.setPersonalizeId(1);
		personalize2.setPersonalizeType("Anniversary");
		List<Personalize> list = new ArrayList<>();
		list.add(personalize1);
		list.add(personalize2);

		Mockito.when(personalizeRepo.findAll()).thenReturn(list);
		List<Personalize> newList = personalizeService.getAllPersonalize();
		assertEquals(2, newList.size());
		assertEquals("Anniversary", newList.get(1).getPersonalizeType());
	}

}
