package com.cryptomind.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cryptomind.entity.CmAccountAsset;

@Repository
public interface CmAccountAssetDao extends JpaRepository<CmAccountAsset, Long> {

	  @Modifying(flushAutomatically = true, clearAutomatically = true)
	  @Query(value =" update account_asset set avail_amount = avail_amount - :amount ,frozen_amount = frozen_amount + :amount, upd_dt=now() where user_account_id =:user_account_id and coin_nm = :coin_nm", nativeQuery = true)
	  int orderPlaceFrozen(@Param("user_account_id") Long user_account_id, @Param("coin_nm") String coin_nm, @Param("amount") BigDecimal amount);

	  
	
	  //@Modifying(flushAutomatically = true, clearAutomatically = true)
	  @Transactional
	  @Modifying
	  @Query(value =" update account_asset set frozen_amount = frozen_amount - :amount ,upd_dt=now() where user_account_id =:user_account_id and coin_nm = :coin_nm", nativeQuery = true)
	  int dealFrozenSub(@Param("user_account_id") Long user_account_id, @Param("coin_nm") String coin_nm, @Param("amount") BigDecimal amount);

	  @Transactional
	  @Modifying
	  //@Modifying(flushAutomatically = true, clearAutomatically = true)
	  @Query(value =" update account_asset set avail_amount = avail_amount + :amount ,upd_dt=now() where user_account_id =:user_account_id and coin_nm = :coin_nm", nativeQuery = true)
	  int dealAmountAdd(@Param("user_account_id") Long user_account_id, @Param("coin_nm") String coin_nm, @Param("amount") BigDecimal amount);
	  
	  @Modifying(flushAutomatically = true, clearAutomatically = true)
	  @Query(value =" update account_asset set avail_amount = avail_amount + :amount ,frozen_amount = frozen_amount - :amount, upd_dt=now() where user_account_id =:user_account_id and coin_nm = :coin_nm", nativeQuery = true)
	  int dealAmountCancel(@Param("user_account_id") Long user_account_id, @Param("coin_nm") String coin_nm, @Param("amount") BigDecimal amount);
	  
	  //@Modifying(flushAutomatically = true, clearAutomatically = true)
	  @Transactional
	  @Modifying
	  @Query(value =" delete from account_asset where user_account_id =:user_account_id and user_account_id in(2)", nativeQuery = true)
	  int testAdminDelete(@Param("user_account_id") Long user_account_id);

}
