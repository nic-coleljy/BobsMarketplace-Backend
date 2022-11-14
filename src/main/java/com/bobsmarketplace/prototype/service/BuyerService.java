package com.bobsmarketplace.prototype.service;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Buyer;

public interface BuyerService {
    List<Buyer> listBuyers();
    Buyer getBuyer(String email);
    Buyer getBuyerById(long id);
    Buyer addBuyer(Buyer buyer);
    Buyer updateBuyer(String email, Buyer buyer);
    void deleteBuyer(String email);
    long getCreditLimit(Buyer buyer);
    Buyer lock(Long id);
    Buyer unlock(Long id);
}
