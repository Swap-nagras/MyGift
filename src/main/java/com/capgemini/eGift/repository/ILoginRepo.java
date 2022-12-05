package com.capgemini.eGift.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.eGift.entity.Login;

public interface ILoginRepo extends JpaRepository<Login, String> {
	
	//fetch login details from login based on user Id
	@Query(value="select login.email, login.password, login.role, login.is_login from login join user_table on user_table.user_login_fk=login.email where user_table.user_id=:userId", nativeQuery=true)
	
	Optional<Login> getLoginByUserId(@Param("userId") int userId);
	Optional<Login> getLoginByEmail(@Param("email") String email);

}
