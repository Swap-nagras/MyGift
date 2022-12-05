package com.capgemini.eGift.entity;



import java.time.LocalDate;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



import lombok.Data;
@Data
@Entity(name="gift_redeem_details")
public class GiftRedeemDetails {
    @Id
    @GeneratedValue
    private int giftRedeemId;
    private boolean redeemStatus;
    private LocalDate redeemDate;
    private double redeemAmount, balance;
    
}