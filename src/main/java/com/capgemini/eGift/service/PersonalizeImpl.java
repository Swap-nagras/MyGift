package com.capgemini.eGift.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.eGift.entity.PaymentDetails;
import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.exceptions.ResourceNotFoundException;
import com.capgemini.eGift.repository.IPersonalizeRepo;

@Service
public class PersonalizeImpl implements IPersonalize {
	@Autowired
	IPersonalizeRepo personalizeRepo;

	// add a personalize object
	@Override
	public Personalize addPersonalize(Personalize personalize) {
		Personalize newPersonalize = personalizeRepo.save(personalize);
		return newPersonalize;
	}

	// get personalize object by id
	@Override
	public Personalize getPersonalizeById(int personalizeId) {
		Optional<Personalize> opt = personalizeRepo.findById(personalizeId);
		Personalize personalize = null;
		if (opt.isPresent()) {
			personalize = opt.get();
		} else {
			throw new ResourceNotFoundException("Personalize entry not found for id:" + personalizeId);
		}
		return personalize;
	}

	// delete personalize object by id
	@Override
	public Personalize deletePersonalizeById(int personalizeId) {
		Optional<Personalize> opt = personalizeRepo.findById(personalizeId);
		Personalize personalize = null;
		if (opt.isPresent()) {
			personalize = opt.get();
			personalizeRepo.deleteById(personalizeId);
		} else {
			throw new ResourceNotFoundException("Personalize entry not found for id:" + personalizeId);
		}
		return personalize;
	}

	// update personalize object
	@Override
	public Personalize updatePersonalize(Personalize newPersonalize) {
		Optional<Personalize> opt = personalizeRepo.findById(newPersonalize.getPersonalizeId());
		if (opt.isPresent()) {
			personalizeRepo.save(newPersonalize);
		} else {
			throw new ResourceNotFoundException(
					"Personalize entry not found for id:" + newPersonalize.getPersonalizeId());
		}
		return newPersonalize;
	}

	// list all personalize entites
	@Override
	public List<Personalize> getAllPersonalize() {
		List<Personalize> personalizeList = personalizeRepo.findAll();
		return personalizeList;
	}

}
