package com.bobsmarketplace.prototype.entity.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BuyerLoginResponse extends UserLoginResponse{
    private String businessProfileUrl;
    private double creditScore;
    private long creditLimit;

    public BuyerLoginResponse(long id, String email, String companyName, String role, String businessProfileUrl, double creditScore, long creditLimit) {
        super(id, email, companyName, role);
        this.businessProfileUrl = businessProfileUrl;
        this.creditScore = creditScore;
        this.creditLimit = creditLimit;
    }
}
