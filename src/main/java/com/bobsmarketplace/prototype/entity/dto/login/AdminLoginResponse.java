package com.bobsmarketplace.prototype.entity.dto.login;

public class AdminLoginResponse extends UserLoginResponse{
    public AdminLoginResponse(long id, String email, String companyName, String role) {
        super(id, email, companyName, role);
    }
}
