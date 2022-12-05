package com.capgemini.eGift.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
	@Id
	@Email
	private String email;
	@NotEmpty(message= "Password cannot be empty")
	@Pattern(regexp = "[a-zA-Z0-9_@!#$%*\"]{5,16}", message="Password length should be 5-16 characters")
	private String password;
	@NotEmpty(message= "Role cannot be empty")
	@Pattern(regexp = "admin|Admin|user|User|ADMIN|USER", message="Specify your role as Admin or User")
	private String role;
	private boolean isLogin;

}
