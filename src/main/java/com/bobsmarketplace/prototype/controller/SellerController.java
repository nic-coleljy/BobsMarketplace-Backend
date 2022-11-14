package com.bobsmarketplace.prototype.controller;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Seller;
import com.bobsmarketplace.prototype.exception.seller.SellerExistsException;
import com.bobsmarketplace.prototype.exception.seller.SellerNotFoundException;
import com.bobsmarketplace.prototype.service.SellerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/seller")
public class SellerController {
    
    private SellerService sellerService;
    private BCryptPasswordEncoder encoder;

    public SellerController(SellerService sellerService, BCryptPasswordEncoder encoder) {
        this.sellerService = sellerService;
        this.encoder = encoder;
    }

    @GetMapping("")
    public List<Seller> getAllSellers() {
        return sellerService.listSellers();
    }

    @GetMapping("/email/{email}")
    public Seller getSellerByEmail(@PathVariable String email) {
        Seller result = sellerService.getSeller(email);
        if (result == null) {
            throw new SellerNotFoundException();
        }
        return result;
    }

    @GetMapping("/id/{id}")
    public Seller getSellerById(@PathVariable Long id) {
        Seller result = sellerService.getSeller(id);
        if (result == null) {
            throw new SellerNotFoundException();
        }
        return result;
    }

    @PostMapping("")
    public Seller addSeller(@RequestBody Seller seller) {
        seller.setPassword(encoder.encode(seller.getPassword()));
        Seller result = sellerService.addSeller(seller);
        if (result == null) {
            throw new SellerExistsException();
        }
        return result;
    }

    @PutMapping("")
    public Seller updateSeller(@RequestBody Seller seller) {
        seller.setPassword(encoder.encode(seller.getPassword()));
        Seller result = sellerService.updateSeller(seller.getEmail(), seller);
        if (result == null) {
            throw new SellerNotFoundException();
        }
        return result;
    }

    @DeleteMapping("/{email}")
    public void deleteSeller(@PathVariable String email) {
        try {
            sellerService.deleteSeller(email);
        } catch (Exception e) {
            throw new SellerNotFoundException();
        }
    }

    @GetMapping("/lock/{id}")
    public Seller lock(@PathVariable Long id) {
        return sellerService.lock(id);
    }

    @GetMapping("/unlock/{id}")
    public Seller unlock(@PathVariable Long id) {
        return sellerService.unlock(id);
    }
}
