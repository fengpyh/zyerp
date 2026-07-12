package com.cryptomind.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cryptomind.entity.CmSymbol;

@Repository
public interface CmSymbolDao extends JpaRepository<CmSymbol, Long> {

    @Query(value = "select * from symbols where cd= :cd",nativeQuery = true)
    CmSymbol findByCd(@Param("cd") String cd);

    @Query(value = "select * from symbols where symbol= :symbol",nativeQuery = true)
    CmSymbol findBySymbol(@Param("symbol") String symbol);
}
