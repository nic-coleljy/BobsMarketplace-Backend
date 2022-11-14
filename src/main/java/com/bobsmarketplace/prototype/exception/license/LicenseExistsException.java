package com.bobsmarketplace.prototype.exception.license;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "A license already exists for that company.")
public class LicenseExistsException extends RuntimeException {
    public LicenseExistsException() {
        super("License already exists for that company.");
    }
}
