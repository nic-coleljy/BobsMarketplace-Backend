package com.bobsmarketplace.prototype.exception.buyer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Buyer with the specified email doesn't exist.")
public class BuyerNotFoundException extends RuntimeException {
    public BuyerNotFoundException() {
        super("Buyer not found");
    }

    public BuyerNotFoundException(String message) {
        super(message);
    }
}
