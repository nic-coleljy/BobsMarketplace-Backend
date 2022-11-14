package com.bobsmarketplace.prototype.entity.dto.register;

import java.util.Optional;

public class UserRegister {
    String email;
    String password;
    String companyName;
    String role;
    Optional<String> businessProfileUrl;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Optional<String> getBusinessProfileUrl() {
        return businessProfileUrl;
    }

    public void setBusinessProfileUrl(Optional<String> businessProfileUrl) {
        this.businessProfileUrl = businessProfileUrl;
    }
}
