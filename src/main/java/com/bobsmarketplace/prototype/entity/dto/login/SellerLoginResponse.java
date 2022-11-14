package com.bobsmarketplace.prototype.entity.dto.login;

import java.util.List;

import com.bobsmarketplace.prototype.entity.License;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerLoginResponse extends UserLoginResponse {
    private double credibility;
    private List<License> licenseList;

    public SellerLoginResponse(long id, String email, String companyName, String role, double credibility,
            List<License> licenseList) {
        super(id, email, companyName, role);
        this.credibility = credibility;
        this.licenseList = licenseList;
    }
}
