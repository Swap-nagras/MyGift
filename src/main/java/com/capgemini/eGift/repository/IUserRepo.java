package com.capgemini.eGift.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.eGift.entity.Login;
import com.capgemini.eGift.entity.UserEntity;
@Repository
public interface IUserRepo extends JpaRepository<UserEntity, Integer> {
	
	List<UserEntity>findByFirstName(String firstName);
	List<UserEntity>findByLastName(String lastName);
	UserEntity findByLogin(Login login);
	
}
