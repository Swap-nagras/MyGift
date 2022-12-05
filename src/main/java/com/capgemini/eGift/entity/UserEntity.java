//package com.example.demo.entity;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity(name = "user_table")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserEntity {
//	@Id
//	@GeneratedValue
//	private int userId;
//	private String firstName;
//	private String lastName;
//	private String email;
//	private String mobile;
//	private String password;
//	private String address;
//
//	// Relationships
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id_fk")
//	private List<UserGiftDetails> userGiftDetails;
//
//}

package com.capgemini.eGift.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue
	private int userId;
	private String firstName;
	private String lastName;
	private String mobile;
	private String address;

	// one to one relationship
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_login_fk")
	private Login login;

	// Relationships
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id_fk")
	private List<UserGiftDetails> userGiftDetails;

}

