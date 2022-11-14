package com.bobsmarketplace.prototype.exception.login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason="Account is locked.")
public class UserLockedException extends RuntimeException{
    public UserLockedException() {
        super("Account is locked");
    }
}