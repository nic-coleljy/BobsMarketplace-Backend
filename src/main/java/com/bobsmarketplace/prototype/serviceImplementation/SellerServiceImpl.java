package com.bobsmarketplace.prototype.serviceImplementation;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Seller;
import com.bobsmarketplace.prototype.repository.SellerRepository;
import com.bobsmarketplace.prototype.service.SellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public List<Seller> listSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller getSeller(String email) {
        return sellerRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Seller getSeller(Long id) {
        return sellerRepository.findById(id).orElse(null);
    }

    @Override
    public Seller addSeller(Seller seller) {
        Seller result = sellerRepository.findById(seller.getId()).orElse(null);
        if (result != null) {
            return null;
        }
        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSeller(String email, Seller seller) {
        return sellerRepository.findByEmail(email).map(s -> {
            s.setEmail(seller.getEmail());
            s.setPassword(seller.getPassword());
            s.setCompanyName(seller.getCompanyName());
            s.setLicenses(seller.getLicenses());
            s.setCommodities(seller.getCommodities());
            return sellerRepository.save(s);
        }).orElse(null);
    }

    @Override
    public void deleteSeller(String email) {
        Seller seller = sellerRepository.findByEmail(email).orElse(null);
        seller.setLock(1);
        sellerRepository.save(seller);
    }

    @Override
    public Seller lock(Long id) {
        Seller seller = sellerRepository.getById(id);
        seller.setLock(1);
        return sellerRepository.save(seller);
    }

    @Override
    public Seller unlock(Long id) {
        Seller seller = sellerRepository.getById(id);
        seller.setLock(0);
        return sellerRepository.save(seller);
    }
}
