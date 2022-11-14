package com.bobsmarketplace.prototype.entity.dto.commodityName;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Category;
import com.bobsmarketplace.prototype.entity.Commodity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommodityNameResponse {
    private Long cnid;
    private String name;
    private String pictureUrl;
    private String uom;
    private Category categoryEntity;
    private Commodity commodity;
    private List<DateTimePrice> pastPrices;
}
