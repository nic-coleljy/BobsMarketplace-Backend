package com.bobsmarketplace.prototype.exception.license;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "License not found.")
public class LicenseNotFoundException extends RuntimeException {
    public LicenseNotFoundException() {
        super("License not found.");
    }
}
