package com.capgemini.eGift.service;

import java.util.List;

import com.capgemini.eGift.entity.PaymentDetails;

public interface IPaymentDetails {
	PaymentDetails addPaymentDetails(PaymentDetails paymentDetails);

	PaymentDetails getPaymentDetailsById(int paymentId);

	PaymentDetails deletePaymentDetailsById(int paymentId);

	PaymentDetails updatePaymentDetails(PaymentDetails paymentDetails);

	List<PaymentDetails> getAllPaymentDetails();

}
