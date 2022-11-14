package com.bobsmarketplace.prototype.repository;

import java.util.List;
import java.util.Optional;

import com.bobsmarketplace.prototype.entity.Seller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository <Seller, Long> {
    public Optional<Seller> findByEmail(String email);
    public Seller findById(String id);
    public List<Seller> findByCompanyName(String companyName);
    public void deleteByEmail(String email);
}
