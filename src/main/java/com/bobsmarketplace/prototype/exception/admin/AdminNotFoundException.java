package com.bobsmarketplace.prototype.exception.admin;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Admin with the specified email doesn't exist.")
public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException() {
        super("Admin not found");
    }

    public AdminNotFoundException(String message) {
        super(message);
    }
}