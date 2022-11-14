package com.bobsmarketplace.prototype.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Error
public class TransactionNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TransactionNotFoundException(Long tid) {
        super("Could not find category with tid " + tid);
    }
}
