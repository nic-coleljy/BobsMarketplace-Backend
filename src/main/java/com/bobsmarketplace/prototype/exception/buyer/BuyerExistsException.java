package com.bobsmarketplace.prototype.exception.buyer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "A buyer already exists for that company.")
public class BuyerExistsException extends RuntimeException {
    public BuyerExistsException() {
        super("Company already exists.");
    }
}
