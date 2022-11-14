package com.bobsmarketplace.prototype.serviceImplementation;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Buyer;
import com.bobsmarketplace.prototype.entity.CreditScore;
import com.bobsmarketplace.prototype.exception.buyer.BuyerNotFoundException;
import com.bobsmarketplace.prototype.repository.BuyerRepository;
import com.bobsmarketplace.prototype.service.BuyerService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BuyerServiceImpl implements BuyerService {

    private final String creditScoreURL = "http://13.229.231.81:5000/api/credit-score?file=";
    private BuyerRepository buyerRepo;

    public BuyerServiceImpl(BuyerRepository buyerRepo) {
        this.buyerRepo = buyerRepo;
    }

    @Override
    public List<Buyer> listBuyers() {
        return buyerRepo.findAll();
    }

    @Override
    public Buyer getBuyer(String email) {
        return buyerRepo.findByEmail(email).orElse(null);
    }

    @Override
    public Buyer getBuyerById(long id) {
        return buyerRepo.findById(id).orElse(null);
    }

    @Override
    public Buyer addBuyer(Buyer buyer) {
        Buyer existing = buyerRepo.findByEmail(buyer.getEmail()).orElse(null);
        if (existing != null) {
            return null;
        }

        String url = creditScoreURL + buyer.getBusinessProfileUrl();
        RestTemplate restTemplate = new RestTemplate();

        // update credit score of buyer
        ResponseEntity<CreditScore> response = restTemplate.getForEntity(url, CreditScore.class);
        if (response.getStatusCode().value() != 200) {
            throw new BuyerNotFoundException("Business profile URL doesn't exist");
        }
        buyer.setCreditScore(response.getBody().getCreditworthiness());

        return buyerRepo.save(buyer);
    }

    @Override
    public Buyer updateBuyer(String email, Buyer buyer) {
        return buyerRepo.findByEmail(email).map(b -> {
            String url = creditScoreURL + buyer.getBusinessProfileUrl();
            RestTemplate restTemplate = new RestTemplate();
            
            ResponseEntity<CreditScore> response = restTemplate.getForEntity(url, CreditScore.class);
            if (response.getStatusCode().value() != 200) {
                throw new BuyerNotFoundException("Business profile URL doesn't exist");
            }

            b.setEmail(buyer.getEmail());
            b.setCompanyName(buyer.getCompanyName());
            b.setBusinessProfileUrl(buyer.getBusinessProfileUrl());
            b.setCreditScore(response.getBody().getCreditworthiness());

            return buyerRepo.save(b);
        }).orElse(null);
    }

    @Override
    public void deleteBuyer(String email) {
        Buyer buyer = buyerRepo.findByEmail(email).orElse(null);
        buyer.setLock(1);
        buyerRepo.save(buyer);
    }

    @Override
    public long getCreditLimit(Buyer buyer) {
        long creditScore = Math.round(buyer.getCreditScore());
        return creditScore;
    }

    @Override
    public Buyer lock(Long id) {
        Buyer buyer = buyerRepo.getById(id);
        buyer.setLock(1);
        return buyerRepo.save(buyer);
    }

    @Override
    public Buyer unlock(Long id) {
        Buyer buyer = buyerRepo.getById(id);
        buyer.setLock(0);
        return buyerRepo.save(buyer);
    }
}
