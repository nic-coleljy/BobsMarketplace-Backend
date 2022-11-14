package com.bobsmarketplace.prototype.repository;

import java.util.Optional;

import com.bobsmarketplace.prototype.entity.Buyer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository <Buyer, Long> {
    public Optional<Buyer> findByEmail(String email);
    public Buyer findByCompanyName(String companyName);
    public void deleteByEmail(String email);
}
