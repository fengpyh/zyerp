package com.cryptomind.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cryptomind.entity.PnlExchange;

@Repository
public interface PnlExchagneDao extends JpaRepository<PnlExchange, Long> {

}
