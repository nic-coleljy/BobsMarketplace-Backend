package com.bobsmarketplace.prototype.entity.dto.commodityName;

import java.util.Comparator;

public class SortByDateTimeAscending implements Comparator<DateTimePrice> {
    public int compare(DateTimePrice a, DateTimePrice b) {
        return a.getDateTime().compareTo(b.getDateTime());
    }
}
