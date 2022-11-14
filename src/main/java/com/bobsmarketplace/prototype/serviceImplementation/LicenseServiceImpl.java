package com.bobsmarketplace.prototype.serviceImplementation;

import java.util.List;

import com.bobsmarketplace.prototype.entity.License;
import com.bobsmarketplace.prototype.repository.LicenseRepository;
import com.bobsmarketplace.prototype.repository.SellerRepository;
import com.bobsmarketplace.prototype.service.LicenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseServiceImpl implements LicenseService {
    
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private LicenseRepository licenseRepository;

    @Override
    public List<License> listAll() {
        return licenseRepository.findAll();
    }

    @Override
    public List<License> listAllBySeller(Long sellerId) {
        return licenseRepository.findBySellerId(sellerId);
    }

    @Override
    public License getLicenseById(Long id) {
        return licenseRepository.findById(id).orElse(null);
    }

    @Override
    public License addLicense(Long sellerId, License license) {
        return sellerRepository.findById(sellerId).map(seller -> {
            license.setSeller(seller);
            return licenseRepository.save(license);
        }).orElse(null);
    }

    @Override
    public License updateLicense(Long sellerId, License license) {
        return licenseRepository.findBySellerIdAndTypeOfGood(sellerId, license.getTypeOfGood()).map(current -> {
            current.setIssuedBy(license.getIssuedBy());
            current.setTypeOfGood(license.getTypeOfGood());
            current.setExpiryDate(license.getExpiryDate());
            return licenseRepository.save(current);
        }).orElse(null);
    }

    @Override
    public boolean deleteLicense(Long id) {
        License found = licenseRepository.findById(id).orElse(null);
        if (found == null) {
            return false;
        }
        licenseRepository.delete(found);
        return true;
    }

    
}
