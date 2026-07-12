package com.cryptomind.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cryptomind.entity.OAuth;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 */
public interface OAuthDao extends JpaRepository<OAuth, Integer> {

    @Query(value = "select * from oauth where user_id=:user_id and api_key =:api_key ",nativeQuery = true)
    List<OAuth> findByUserIdKey(@Param("user_id") Long user_id, @Param("api_key") String api_key);

    @Query(value = "select * from oauth where user_id=:user_id ",nativeQuery = true)
    List<OAuth> findByUserId(@Param("user_id") Long user_id);

    @Query(value = "select * from oauth where api_key=:api_key ",nativeQuery = true)
    List<OAuth> findByKey(@Param("api_key") String api_key);
}
