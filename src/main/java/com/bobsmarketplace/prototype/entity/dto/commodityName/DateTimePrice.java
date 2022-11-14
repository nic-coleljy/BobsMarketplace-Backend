package com.bobsmarketplace.prototype.entity.dto.commodityName;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class DateTimePrice {
    LocalDateTime dateTime;
    Double price;
}
