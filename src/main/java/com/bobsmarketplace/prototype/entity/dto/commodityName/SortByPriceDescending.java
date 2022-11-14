package com.bobsmarketplace.prototype.entity.dto.commodityName;

import java.util.Comparator;

public class SortByPriceDescending implements Comparator<CommodityNameResponse> {
    public int compare(CommodityNameResponse a, CommodityNameResponse b) {
        return a.getCommodity().getPrice() > b.getCommodity().getPrice() ? -1 : 1;
    }
}
