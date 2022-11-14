package com.bobsmarketplace.prototype.exception.commodity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Error
public class CommodityNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CommodityNotFoundException(Long cid) {
        super("Could not find commodity with cid " + cid);
    }

    public CommodityNotFoundException(String name) {
        super("Could not find commodity with name " + name);
    }
}