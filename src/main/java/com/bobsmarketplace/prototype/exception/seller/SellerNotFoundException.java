package com.bobsmarketplace.prototype.exception.seller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Seller with specified email doesn't exist.")
public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException() {
        super("Seller company not found.");
    }
}
