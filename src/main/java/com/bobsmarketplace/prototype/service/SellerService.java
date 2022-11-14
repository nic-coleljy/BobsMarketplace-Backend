package com.bobsmarketplace.prototype.service;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Seller;

public interface SellerService {
    List<Seller> listSellers();
    Seller getSeller(String email);
    Seller getSeller(Long id);
    Seller addSeller(Seller seller);
    Seller updateSeller(String email, Seller seller);
    void deleteSeller(String email);
    Seller lock(Long id);
    Seller unlock(Long id);
}
