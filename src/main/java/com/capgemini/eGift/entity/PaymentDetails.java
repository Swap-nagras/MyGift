package com.capgemini.eGift.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "payment_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {
	@Id
	@GeneratedValue
	private int paymentId;
	private LocalDate paymentDate;
	@NotEmpty(message="card name is required")
	private String nameOnCard;
	@Digits(message="enter valid 16 digit card number", integer=16, fraction = 0)
	@Size(min=16,max=16,message= "enter valid 16 digit card number")
	private String cardNumber;
	@Future
	private LocalDate cardExpiry;
	@Digits(message="enter valid cvv", integer=3, fraction = 0)
	@Min(100)
	@Max(999)
	private int cvv;
}
