package com.capgemini.eGift.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "personalize_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personalize {
	@Id
	@GeneratedValue
	private int personalizeId;
	private String personalizeType;
}
