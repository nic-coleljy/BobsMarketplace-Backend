package com.bobsmarketplace.prototype.repository;

import java.util.Optional;

import com.bobsmarketplace.prototype.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Long> {
    public Optional<Admin> findByEmail(String email);
    public void deleteByEmail(String email);
}
