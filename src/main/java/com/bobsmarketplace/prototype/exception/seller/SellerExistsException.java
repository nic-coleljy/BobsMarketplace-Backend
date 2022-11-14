package com.bobsmarketplace.prototype.exception.seller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "A seller already exists for that company.")
public class SellerExistsException extends RuntimeException {
    public SellerExistsException() {
        super("Company already exists.");
    }
}
