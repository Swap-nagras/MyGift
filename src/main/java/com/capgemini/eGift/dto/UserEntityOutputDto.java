package com.capgemini.eGift.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityOutputDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String address;
	private String role;
}
