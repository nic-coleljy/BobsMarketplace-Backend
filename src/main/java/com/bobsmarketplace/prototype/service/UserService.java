package com.bobsmarketplace.prototype.service;

import com.bobsmarketplace.prototype.entity.User;
import com.bobsmarketplace.prototype.entity.dto.login.UserLogin;
import com.bobsmarketplace.prototype.entity.dto.login.UserLoginResponse;
import com.bobsmarketplace.prototype.entity.dto.register.UserRegister;

public interface UserService {
    public void register(UserRegister userRegister);
    public <T extends UserLoginResponse> UserLoginResponse login(UserLogin userLogin);
}
