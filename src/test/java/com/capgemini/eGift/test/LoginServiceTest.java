package com.capgemini.eGift.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.eGift.dto.LoginOutputDto;
import com.capgemini.eGift.entity.Login;
import com.capgemini.eGift.repository.ILoginRepo;
import com.capgemini.eGift.service.LoginServiceImpl;

@ExtendWith(SpringExtension.class)
class LoginServiceTest {
	@InjectMocks
	LoginServiceImpl loginServ;

	@MockBean
	ILoginRepo loginRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void loginTest() {
		Login log = new Login();
		log.setEmail("testMail@company.com");
		log.setPassword("mySecurePass123");
		log.setRole("user");

		Login logRes = new Login();
		logRes.setEmail("testMail@company.com");
		logRes.setPassword("mySecurePass123");
		logRes.setRole("user");
		logRes.setLogin(true);

		Mockito.when(loginRepo.findById("testMail@company.com")).thenReturn(Optional.of(log));
		Mockito.when(loginRepo.save(logRes)).thenReturn(logRes);

		Login response = loginServ.login(log);
		assertEquals("testMail@company.com", response.getEmail());
		assertEquals("mySecurePass123", response.getPassword());
		assertEquals("user", response.getRole());
		assertEquals(true, response.isLogin());
	}

	@Test
	void getLoginByUserIdTest() {
		Login login = new Login();
		login.setEmail("testMail@company.com");
		login.setPassword("mySecurePass123");
		login.setRole("User");
		login.setLogin(false);

		Mockito.when(loginRepo.getLoginByUserId(1)).thenReturn(Optional.of(login));

		Login response = loginServ.getLoginByUserId(1);

		assertEquals("testMail@company.com", response.getEmail());
		assertEquals("mySecurePass123", response.getPassword());
		assertEquals("User", response.getRole());
		assertEquals(false, response.isLogin());

	}

	@Test
	void logoutTest() {

		Login log = new Login();
		log.setEmail("testMail@company.com");
		log.setPassword("mySecurePass123");
		log.setRole("User");
		log.setLogin(true);

		Login logRes = new Login();
		logRes.setEmail("testMail@company.com");
		logRes.setPassword("mySecurePass123");
		logRes.setRole("User");
		logRes.setLogin(false);

		Mockito.when(loginRepo.findById("testMail@company.com")).thenReturn(Optional.of(log));
		Mockito.when(loginRepo.save(logRes)).thenReturn(logRes);

		LoginOutputDto response = loginServ.logout("testMail@company.com");

		assertEquals("testMail@company.com", response.getEmail());
		assertEquals("User", response.getRole());
		assertEquals(false, response.isLogin());

	}

}
