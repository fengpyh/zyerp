package com.cryptomind.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cryptomind.entity.CmAccountAssetArch;

@Repository
public interface CmAccountAssetArchDao extends JpaRepository<CmAccountAssetArch, Long> {

}
