package com.bobsmarketplace.prototype.service;

import java.util.List;

import com.bobsmarketplace.prototype.entity.License;

public interface LicenseService {
    List<License> listAll();
    List<License> listAllBySeller(Long sellerId);
    License getLicenseById(Long id);
    License addLicense(Long sellerId, License license);
    License updateLicense(Long sellerId, License license);
    boolean deleteLicense(Long id);
}
