package com.capgemini.eGift.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.capgemini.eGift.service.IPaymentDetails;

@RestController
@CrossOrigin
public class PaymentDetailsController {
	@Autowired
	IPaymentDetails paymentDetailsService;

	// adding a payment details object
	@PostMapping("/payment/add")
	ResponseEntity<PaymentDetails> addPaymentDetails(@Valid @RequestBody PaymentDetails paymentDetails) {
		PaymentDetails newPaymentDetails = paymentDetailsService.addPaymentDetails(paymentDetails);
		return new ResponseEntity<>(newPaymentDetails, HttpStatus.OK);
	}

	// get payment details object by id
	@GetMapping("/payment/get/{paymentDetailsId}")
	ResponseEntity<PaymentDetails> getPaymentDetailsById(@PathVariable int paymentDetailsId) {
		ResponseEntity<PaymentDetails> re = new ResponseEntity<PaymentDetails>(
				paymentDetailsService.getPaymentDetailsById(paymentDetailsId), HttpStatus.OK);
		return re;
	}

	// deleting a payment details object
	@DeleteMapping("/payment/delete/{paymentDetailsId}")
	ResponseEntity<PaymentDetails> deletePaymentDetails(@PathVariable int paymentDetailsId) {
		ResponseEntity<PaymentDetails> re = new ResponseEntity<PaymentDetails>(
				paymentDetailsService.deletePaymentDetailsById(paymentDetailsId), HttpStatus.OK);
		return re;
	}

	// updating a payment details object
	@PutMapping("payment/update")
	ResponseEntity<PaymentDetails> updatePaymentDetails(@Valid @RequestBody PaymentDetails paymentDetails) {
		return new ResponseEntity<PaymentDetails>(paymentDetailsService.updatePaymentDetails(paymentDetails),
				HttpStatus.OK);
	}

	// get all payment details
	@GetMapping("payment/all")
	ResponseEntity<List<PaymentDetails>> getAllPaymentDetails() {
		return new ResponseEntity<>(paymentDetailsService.getAllPaymentDetails(), HttpStatus.OK);
	}
}
