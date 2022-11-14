package com.bobsmarketplace.prototype.repository;

import com.bobsmarketplace.prototype.entity.Commodity;
import com.bobsmarketplace.prototype.entity.Seller;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommodityRepository extends JpaRepository<Commodity, Long> {
    @Query(value = "select * from bobsmarketplace.commodity where cnid_of_commodityname = :cnid", nativeQuery = true)
    public List<Commodity> findAllCommodityOfCommodityName(@Param("cnid") Long cnid);

    @Query(value = "select * from bobsmarketplace.commodity where id_of_seller = :id", nativeQuery = true)
    public List<Commodity> findAllCommodityOfSeller(@Param("id") Long id);

    //call min price -> Among the "cid"s with the same "name_of_commodity", return the commodity with the min price out of that list
    @Query(value = "select * from bobsmarketplace.commodity where cnid_of_commodityname = :commodityNameId and price = (SELECT MIN(price) FROM bobsmarketplace.commodity where cnid_of_commodityname = :commodityNameId and quantity <> 0);", nativeQuery = true)
    public List<Commodity> findAllCommodityWithMinPrice(@Param("commodityNameId") Long commodityNameId);
    @Query(value = "SELECT * FROM bobsmarketplace.commodity where id_of_seller = ?;", nativeQuery = true)
    public List<Commodity> findAllCommodityWithSellerId(Long sellerId);
    @Query(value = "SELECT * FROM bobsmarketplace.commodity where cnid_of_commodityname = ?;", nativeQuery = true)
    public List<Commodity> findAllCommodityWithCommodityNameId(Long commodityNameId);
}