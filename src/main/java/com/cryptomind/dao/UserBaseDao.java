package com.cryptomind.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cryptomind.entity.UserBase;


public interface UserBaseDao extends JpaRepository<UserBase, Long> {

	
	@Query("select u from UserBase u where u.email= :email")
	UserBase findByEmail(@Param("email") String email);
	
	@Query("select u from UserBase u where u.phone= :phone")
	UserBase findByPhone(@Param("phone") String phone);
}
