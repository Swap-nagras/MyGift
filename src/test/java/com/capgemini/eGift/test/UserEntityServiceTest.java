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

import com.capgemini.eGift.entity.Login;
import com.capgemini.eGift.entity.UserEntity;
import com.capgemini.eGift.repository.IUserRepo;
import com.capgemini.eGift.service.UserServiceImpl;


@ExtendWith(SpringExtension.class)
public class UserEntityServiceTest {
	// @InjectMock - Creates instance of a class and injects mocks that are created
	// with @Mock

	@InjectMocks
	UserServiceImpl userServ;

	// @MockBean - Creates Mock of a class or interface
	@MockBean
	IUserRepo userRepo;

	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

//	@Test
//	void addUserEntityTest() {
//		UserEntity user = new UserEntity();
//		Login login= new Login();
//		user.setUserId(100);
//		login.setEmail("user@abc.com");
//		user.setFirstName("Ram");
//		user.setLogin(login);
//		user.setMobile("1111111111");
//		
//		
//		Mockito.when(userRepo.save(user)).thenReturn(user);
//		
//		UserEntity newUser = userServ.addUser(user);
//		assertEquals(100, newUser.getUserId());
//		assertEquals("Ram", newUser.getFirstName());
//		assertEquals("user@abc.com", newUser.getLogin().getEmail());
//	}
	@Test
	void getUserEntityByIdTest() {
		UserEntity user = new UserEntity();
		Login login= new Login();
		user.setUserId(1);
		user.setFirstName("Ram");
		user.setLastName("Rama");
		login.setEmail("abc@abc.com");
		login.setPassword("password");
		user.setLogin(login);
		
		user.setMobile("0000000000");
		user.setAddress("address");
		Mockito.when(userRepo.findById(1)).thenReturn(Optional.of(user));
		UserEntity response = userServ.getUserEntityById(1);

		assertEquals(1, response.getUserId());
		assertEquals("Ram", response.getFirstName());
		assertEquals("abc@abc.com", response.getLogin().getEmail());
	}
	
	@Test
	void deleteUserEntityByIdTest() {
		UserEntity user = new UserEntity();
		Login login= new Login();
		user.setUserId(1);
		user.setFirstName("Ram");
		user.setLastName("Rama");
		login.setEmail("abc@abc.com");
		login.setPassword("password");
		user.setLogin(login);
		user.setMobile("0000000000");
		user.setAddress("address");
		Mockito.when(userRepo.findById(1)).thenReturn(Optional.of(user));
		UserEntity response = userServ.deleteUserEntityById(1);

		assertEquals(1, response.getUserId());
		assertEquals("Ram", response.getFirstName());
		assertEquals("abc@abc.com", response.getLogin().getEmail());
	}
	
	@Test
	void updateUserEntityByIdTest() {
		UserEntity user1 = new UserEntity();
		Login login= new Login();
		user1.setUserId(1);
		user1.setFirstName("Ram");
		user1.setLastName("Rama");
		login.setEmail("abc@abc.com");
		login.setPassword("password1");
		user1.setLogin(login);
		user1.setMobile("0000000000");
		user1.setAddress("address1");
		UserEntity user2= new UserEntity();
		user2.setUserId(1);
		user2.setFirstName("Sam");
		user2.setLastName("Jack");
		login.setEmail("def@abc.com");
		login.setPassword("password2");
		user2.setLogin(login);
		user2.setMobile("1111111111");
		user2.setAddress("address2");
		Mockito.when(userRepo.findById(1)).thenReturn(Optional.of(user1)); 
		Mockito.when(userRepo.save(user1)).thenReturn(user2);
		UserEntity response = userServ.updateUserEntity(user2);

		assertEquals(1, response.getUserId());
		assertEquals("Sam", response.getFirstName());
		assertEquals("def@abc.com", response.getLogin().getEmail());
	}
	@Test
	void updateUserEntityTest() {
		UserEntity oldUser = new UserEntity();
		Login login= new Login();
		oldUser.setUserId(101);
		oldUser.setFirstName("Krish");
		login.setEmail("oldEmp@abc.com");
		oldUser.setLogin(login);
		UserEntity updatedUser = new UserEntity();
		updatedUser.setUserId(101);
		updatedUser.setFirstName("Krishna");
		login.setEmail("NewEmp@abc.com");
		updatedUser.setLogin(login);
		Mockito.when(userRepo.findById(oldUser.getUserId())).thenReturn(Optional.of(oldUser)); 
		Mockito.when(userRepo.save(oldUser)).thenReturn(updatedUser);
		
		
		UserEntity response = userServ.updateUserEntity(updatedUser);
		assertEquals(101, response.getUserId());
		assertEquals("Krishna", response.getFirstName());
		assertEquals("NewEmp@abc.com", response.getLogin().getEmail());
	}
	@Test
	void getAllUserEntitiesTest() {
		UserEntity user1 = new UserEntity();
		Login login= new Login();
		user1.setUserId(100);
		user1.setFirstName("Ram");
		login.setEmail("user1@abc.com");
		user1.setLogin(login);
		UserEntity user2 = new UserEntity();
		user2.setUserId(101);
		user2.setFirstName("Sam");
		login.setEmail("user2@abc.com");
		user2.setLogin(login);
		List<UserEntity> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		
		Mockito.when(userRepo.findAll()).thenReturn(userList);
		
		List<UserEntity> empLst = userServ.getAllUserEntities();
		assertEquals(2, empLst.size());
		assertEquals("Ram", empLst.get(0).getFirstName());
	}
	@Test//failed
	void getUserEntityByFirstNameTest() {
		UserEntity user1 = new UserEntity();
		Login login= new Login();
		user1.setUserId(100);
		user1.setFirstName("Sam");
		login.setEmail("user1@abc.com");
		user1.setLogin(login);
		UserEntity user2 = new UserEntity();
		user2.setUserId(101);
		user2.setFirstName("Sam");
		login.setEmail("user2@abc.com");
		user1.setLogin(login);
		List<UserEntity> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		
		Mockito.when(userRepo.findByFirstName("Sam")).thenReturn(userList);
		
		List<UserEntity> empLst = userServ.getUserEntityByFirstName("Sam");
		assertEquals(2, empLst.size());
		assertEquals(101, empLst.get(1).getUserId());
	}
	
	@Test//failed
	void getUserEntityByLastNameTest() {
		UserEntity user1 = new UserEntity();
		Login login= new Login();
		user1.setUserId(100);
		user1.setLastName("Ram");
		login.setEmail("user1@abc.com");
		user1.setLogin(login);
		UserEntity user2 = new UserEntity();
		user2.setUserId(101);
		user2.setLastName("Ram");
		login.setEmail("user2@abc.com");
		user2.setLogin(login);
		List<UserEntity> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		
		Mockito.when(userRepo.findByLastName("Ram")).thenReturn(userList);
		
		List<UserEntity> empLst = userServ.getUserEntityByLastName("Ram");
		assertEquals(2, empLst.size());
		assertEquals(101, empLst.get(1).getUserId());
	}
	
}
