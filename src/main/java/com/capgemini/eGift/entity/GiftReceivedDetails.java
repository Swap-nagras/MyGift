package com.capgemini.eGift.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name="gift_received_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftReceivedDetails {
    @Id
    @GeneratedValue
    private int giftRecievedId;
    private LocalDate giftRecievedDate;
    private boolean giftRedeemStatus;
    private int userId;
}
