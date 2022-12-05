package com.capgemini.eGift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.eGift.entity.PaymentDetails;
import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.service.IPersonalize;

@RestController
@CrossOrigin
public class PersonalizeController {
	@Autowired
	IPersonalize personalizeService;

	// adding a personalize object
	@PostMapping("/personalize/add")
	ResponseEntity<Personalize> addPersonalize(@RequestBody Personalize personalize) {
		Personalize newPersonalize = personalizeService.addPersonalize(personalize);
		return new ResponseEntity<>(newPersonalize, HttpStatus.OK);
	}

	// get payment personalize by id
	@GetMapping("/personalize/get/{personalizeId}")
	ResponseEntity<Personalize> getPersonalizeById(@PathVariable int personalizeId) {
		ResponseEntity<Personalize> re = new ResponseEntity<Personalize>(
				personalizeService.getPersonalizeById(personalizeId), HttpStatus.OK);
		return re;
	}

	// deleting a personalize object
	@DeleteMapping("/personalize/delete/{personalizeId}")
	ResponseEntity<Personalize> deletePersonalize(@PathVariable int personalizeId) {
		ResponseEntity<Personalize> re = new ResponseEntity<Personalize>(
				personalizeService.deletePersonalizeById(personalizeId), HttpStatus.OK);
		return re;
	}

	// updating a personalize object
	@PutMapping("personalize/update")
	ResponseEntity<Personalize> updatePersonalize(@RequestBody Personalize personalize) {
		return new ResponseEntity<Personalize>(personalizeService.updatePersonalize(personalize), HttpStatus.OK);
	}

	// get all personalize
	@GetMapping("personalize/all")
	ResponseEntity<List<Personalize>> getAllPersonalize() {
		return new ResponseEntity<>(personalizeService.getAllPersonalize(), HttpStatus.OK);
	}
}
