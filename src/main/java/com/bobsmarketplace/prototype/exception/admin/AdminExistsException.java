package com.bobsmarketplace.prototype.exception.admin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "An admin already exists for that company.")
public class AdminExistsException extends RuntimeException {
    public AdminExistsException() {
        super("Admin already exists.");
    }
}
