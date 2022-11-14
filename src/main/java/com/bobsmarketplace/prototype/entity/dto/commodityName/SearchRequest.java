package com.bobsmarketplace.prototype.entity.dto.commodityName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchRequest {
    String searchTerm;
    String sort;
    List<String> categories;
}
