package com.bobsmarketplace.prototype.repository;

import com.bobsmarketplace.prototype.entity.CommodityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommodityNameRepository extends JpaRepository<CommodityName, Long> {
    @Query(value = "SELECT * FROM bobsmarketplace.commodity_name where name = ?;", nativeQuery = true)
    public CommodityName findCommodityNameByName(String name);
}
