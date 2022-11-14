package com.bobsmarketplace.prototype.entity.dto.creditLimit;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreditLimitResponse {
    private long id;
    private double requestAmount;
}

