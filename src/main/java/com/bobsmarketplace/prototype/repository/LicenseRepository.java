package com.bobsmarketplace.prototype.repository;

import java.util.List;
import java.util.Optional;

import com.bobsmarketplace.prototype.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository <License, Long> {
    public List<License> findBySellerId(long sellerId);
    public Optional<License> findBySellerIdAndTypeOfGood(long sellerId, String typeOfGood);
}
