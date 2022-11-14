package com.bobsmarketplace.prototype.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT, reason = "HO HO HO")
public class HohohoException extends RuntimeException {
    public HohohoException() {
        super("HO HO HO");
    }
}
