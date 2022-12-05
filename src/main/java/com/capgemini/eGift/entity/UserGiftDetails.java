package com.capgemini.eGift.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "user_gift_details")
public class UserGiftDetails {
	@Id
	@GeneratedValue
	private int userGiftDetailsId;
	private double giftCardAmount;
	private String recipientMobile;
	private String recipientName, recipientEmail, deliveryType, scheduleDelivery;
	private boolean isReloadable;
	// Relationships
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_gift_id_fk")
	private List<PaymentDetails> paymentDetails;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personalize_id_fk")
	private Personalize personalize;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_gift_id_fk")
	private List<GiftRedeemDetails> giftRedeemDetails;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_gift_id_fk")
	private List<GiftReceivedDetails> giftReceivedDetails;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gift_card_id_fk")
	private GiftCard giftCard;
	// front-end->controller->service->repo->db
}