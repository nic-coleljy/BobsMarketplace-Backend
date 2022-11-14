package com.bobsmarketplace.prototype.controller;

import java.util.List;

import com.bobsmarketplace.prototype.entity.License;
import com.bobsmarketplace.prototype.exception.license.LicenseExistsException;
import com.bobsmarketplace.prototype.exception.license.LicenseNotFoundException;
import com.bobsmarketplace.prototype.service.LicenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping("")
    public List<License> getAllLicenses() {
        return licenseService.listAll();
    }

    @GetMapping("/seller/{sellerId}")
    public List<License> getSellerLicenses(@PathVariable long sellerId) {
        return licenseService.listAllBySeller(sellerId);
    }

    @GetMapping("/{id}")
    public License getLicenseById(@PathVariable long id) {
        License result = licenseService.getLicenseById(id);
        if (result == null) {
            throw new LicenseNotFoundException();
        }
        return result;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/seller/{sellerId}")
    public License addLicense(@PathVariable long sellerId, @RequestBody License license) {
        License result = licenseService.addLicense(sellerId, license);
        if (result == null) {
            throw new LicenseNotFoundException();
        }
        return result;
    }

    @PutMapping("/seller/{sellerId}")
    public License updateLicense(@PathVariable long sellerId, @RequestBody License license) {
        License result = licenseService.updateLicense(sellerId, license);
        if (result == null) {
            throw new LicenseNotFoundException();
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLicense(@PathVariable long id) {
        if (!licenseService.deleteLicense(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("License not found");
        }
        return ResponseEntity.ok("Successful");
    }
}
