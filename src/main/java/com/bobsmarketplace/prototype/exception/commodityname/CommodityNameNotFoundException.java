package com.bobsmarketplace.prototype.exception.commodityname;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Error
public class CommodityNameNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CommodityNameNotFoundException(Long cnid) {
        super("Could not find commodity with id " + cnid);
    }

    public CommodityNameNotFoundException(String name) {
        super("Could not find commodity with name " + name);
    }
}