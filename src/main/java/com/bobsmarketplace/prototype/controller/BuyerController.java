package com.bobsmarketplace.prototype.controller;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Buyer;
import com.bobsmarketplace.prototype.entity.dto.creditLimit.CreditLimitResponse;
import com.bobsmarketplace.prototype.exception.buyer.BuyerExistsException;
import com.bobsmarketplace.prototype.exception.buyer.BuyerNotFoundException;
import com.bobsmarketplace.prototype.service.BuyerService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/buyer")
public class BuyerController {

    private BuyerService buyerService;
    private BCryptPasswordEncoder encoder;

    public BuyerController(BuyerService buyerService, BCryptPasswordEncoder encoder) {
        this.buyerService = buyerService;
        this.encoder = encoder;
    }

    @GetMapping("")
    public List<Buyer> getAllBuyers() {
        return buyerService.listBuyers();
    }

    @GetMapping("/{email}")
    public Buyer getBuyerByEmail(@PathVariable String email) {
        Buyer result = buyerService.getBuyer(email);
        if (result == null) {
            throw new BuyerNotFoundException();
        }
        return result;
    }

    @PostMapping("")
    public Buyer addBuyer(@RequestBody Buyer buyer) {
        buyer.setPassword(encoder.encode(buyer.getPassword()));
        Buyer added = buyerService.addBuyer(buyer);
        if (added == null) {
            throw new BuyerExistsException();
        }
        return added;
    }

    @PutMapping("")
    public Buyer updateBuyer(@RequestBody Buyer buyer) {
        buyer.setPassword(encoder.encode(buyer.getPassword()));
        Buyer added = buyerService.updateBuyer(buyer.getEmail(), buyer);
        if (added == null) {
            throw new BuyerNotFoundException();
        }
        return added;
    }

    @DeleteMapping("/{email}")
    public void deleteBuyer(@PathVariable String email) {
        try {
            buyerService.deleteBuyer(email);
        } catch (Exception e) {
            throw new BuyerNotFoundException();
        }
    }

    @PostMapping("/credit-limit")
    public long getCreditLimit(@RequestBody CreditLimitResponse creditLimit) {
        Buyer buyer = buyerService.getBuyerById(creditLimit.getId());
        long loan_amount = Math.round((buyer.getCreditScore()/100) * (creditLimit.getRequestAmount()));
        return loan_amount;
    }

    @GetMapping("/lock/{id}")
    public Buyer lock(@PathVariable Long id) {
        return buyerService.lock(id);
    }

    @GetMapping("/unlock/{id}")
    public Buyer unlock(@PathVariable Long id) {
        return buyerService.unlock(id);
    }
}
