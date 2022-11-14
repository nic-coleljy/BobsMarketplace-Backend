package com.bobsmarketplace.prototype.service;

import com.bobsmarketplace.prototype.entity.CommodityName;
import java.util.List;

public interface CommodityNameService {
    //CREATE
    CommodityName save(CommodityName newCommodityName);

    //READ
    List<CommodityName> getAllCommodityName();

    CommodityName getCommodityNameById(Long cnid);

    CommodityName getCommodityNameByName(String name);

    //UPDATE
    CommodityName updateCommodityName(Long cnid, CommodityName newCommodityName);

    //DELETE
    void deleteCommodityNameById(Long cnid);
}
