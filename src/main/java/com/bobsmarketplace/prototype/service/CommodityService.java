package com.bobsmarketplace.prototype.service;

import com.bobsmarketplace.prototype.entity.Commodity;
import java.util.List;

public interface CommodityService {
    //CREATE
    Commodity save(Commodity newCommodity);

    //READ
    List<Commodity> getAllCommodity();

    List<Commodity> getAllCommodityByCommodityName(Long cnid);

    List<Commodity> getAllCommodityBySeller(Long id);

    Commodity getCommodityById(Long cid);

    List<Commodity> getCommodityByCommodityNameId(Long commodityNameId);

    List<Commodity> getAllCommodityWithMinPrice(Long commodityId);

    List<Commodity> getCommodityBySellerId(Long sellerId);

    //UPDATE
    Commodity updateCommodity(Long cid, Commodity newCommodity);

    //DELETE
    void deleteCommodityById(Long cid);
}
