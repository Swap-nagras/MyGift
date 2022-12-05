package com.capgemini.eGift.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.eGift.entity.UserGiftDetails;
@Repository
public interface IUserGiftDetailsRepo extends JpaRepository<UserGiftDetails, Integer> {

}
