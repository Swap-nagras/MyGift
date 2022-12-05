package com.capgemini.eGift.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "gift_card_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftCard {
	@Id
	@GeneratedValue
	private int giftCardId;
	private String giftCardName;
	@ElementCollection
	private List<String> brands;
	private String redemptionDetails;
	private static final double MinimumAmount = 100;
	private static final double MaximumAmount = 10000;
	private String giftCardDesc;
	// get all
	// getbyid
	// getbybrand
}
