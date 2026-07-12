package com.cryptomind.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cryptomind.entity.CmCoinType;


@Repository
public interface CmCoinTypeDao extends JpaRepository<CmCoinType, Long> {

}