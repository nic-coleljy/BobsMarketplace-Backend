package com.bobsmarketplace.prototype.entity.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserLoginResponse {
    private long id;
    private String email;
    private String companyName;
    private String role;

    public UserLoginResponse(long id, String email, String companyName, String role) {
        this.id = id;
        this.email = email;
        this.companyName = companyName;
        this.role = role;
    }
}
