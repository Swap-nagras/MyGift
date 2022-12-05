package com.capgemini.eGift.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.eGift.entity.PaymentDetails;
import com.capgemini.eGift.exceptions.ResourceNotFoundException;
import com.capgemini.eGift.repository.IPaymentDetailsRepo;

@Service
public class PaymentDetailsImpl implements IPaymentDetails {
	@Autowired
	IPaymentDetailsRepo paymentRepo;

	//add a payment 
	@Override
	public PaymentDetails addPaymentDetails(PaymentDetails paymentDetails) {
		PaymentDetails newPaymentDetails = paymentRepo.save(paymentDetails);
		return newPaymentDetails;
	}

	//get a payment by id
	@Override
	public PaymentDetails getPaymentDetailsById(int paymentId) {
		Optional<PaymentDetails> opt = paymentRepo.findById(paymentId);
		PaymentDetails paymentDetails = null;
		if (opt.isPresent()) {
			paymentDetails = opt.get();
		} else {
			throw new ResourceNotFoundException("Payment details not found for id:" + paymentId);
		}
		return paymentDetails;
	}

	//delete a payment
	@Override
	public PaymentDetails deletePaymentDetailsById(int paymentId) {
		Optional<PaymentDetails> opt = paymentRepo.findById(paymentId);
		PaymentDetails paymentDetails = null;
		if (opt.isPresent()) {
			paymentDetails = opt.get();
			paymentRepo.deleteById(paymentId);
		} else {
			throw new ResourceNotFoundException("Payment details not found for id:" + paymentId);
		}
		return paymentDetails;
	}

	//update a payment
	@Override
	public PaymentDetails updatePaymentDetails(PaymentDetails newPaymentDetails) {
		Optional<PaymentDetails> opt = paymentRepo.findById(newPaymentDetails.getPaymentId());
		if (opt.isPresent()) {
			paymentRepo.save(newPaymentDetails);
		} else {
			throw new ResourceNotFoundException(
					"Payment details not found for id:" + newPaymentDetails.getPaymentId());
		}
		return newPaymentDetails;
	}
	
	//list all payments
	@Override
	public List<PaymentDetails> getAllPaymentDetails() {
		List<PaymentDetails> paymentDetailsList = paymentRepo.findAll();
		return paymentDetailsList;
	}

}
