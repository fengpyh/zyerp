package com.cryptomind.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cryptomind.entity.OAuth;
import com.cryptomind.entity.OAuthSecurityKey;

import java.util.List;

/**
 *
 */
public interface OAuthSecurityKeyDao extends JpaRepository<OAuthSecurityKey, Integer> {

    @Query(value = "select * from oauth_security_key where user_id=:user_id and api_key =:api_key ",nativeQuery = true)
    List<OAuthSecurityKey> findByKey(@Param("user_id") Long user_id, @Param("api_key") String api_key);
}
