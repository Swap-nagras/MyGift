package com.capgemini.eGift.dto;

import lombok.Data;

@Data
public class LoginInputDto {
	private String email;
	private String password;
	private String role;
}
