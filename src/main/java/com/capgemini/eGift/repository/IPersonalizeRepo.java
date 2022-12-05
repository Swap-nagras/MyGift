package com.capgemini.eGift.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.eGift.entity.Personalize;
import com.capgemini.eGift.entity.UserEntity;
@Repository
public interface IPersonalizeRepo extends JpaRepository<Personalize, Integer> {
}
