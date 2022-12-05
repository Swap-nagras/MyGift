package com.capgemini.eGift.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityInputDto {
	@NotNull
	@NotEmpty(message= "Password cannot be empty")
	@NotBlank
	private String firstName;
	
	@NotNull
	@NotEmpty(message= "Password cannot be empty")
	@NotBlank
	private String lastName;
	
	@Email
	private String email;
	
	
	@Size(min=10,max=10,message= "Enter a 10 digit number")
	@Digits(message="10 digit number",fraction=0,integer=10)
	private String mobile;
	
	@NotEmpty(message= "Password cannot be empty")
	@Pattern(regexp = "[a-zA-Z0-9_@!#$%*\"]{5,16}", message="Password length should be 5-16 characters")
	private String password;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String address;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Pattern(regexp = "admin|Admin|user|User|ADMIN|USER", message="Specify your role as Admin or User")
	private String role;

}
