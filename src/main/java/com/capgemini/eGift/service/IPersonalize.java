package com.capgemini.eGift.service;

import java.util.List;

import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.entity.UserEntity;

public interface IPersonalize {
	Personalize addPersonalize(Personalize user);

	Personalize getPersonalizeById(int personalizeId);

	Personalize deletePersonalizeById(int personalizeId);

	Personalize updatePersonalize(Personalize personalize);

	List<Personalize> getAllPersonalize();
}
