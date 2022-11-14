package com.bobsmarketplace.prototype.serviceImplementation;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Admin;
import com.bobsmarketplace.prototype.repository.AdminRepository;
import com.bobsmarketplace.prototype.service.AdminService;

import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepo;

    public AdminServiceImpl(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public List<Admin> listAdmins() {
        return adminRepo.findAll();
    }

    @Override
    public Admin getAdmins(String email) {
        return adminRepo.findByEmail(email).orElse(null);
    }

    @Override
    public Admin addAdmins(Admin buyer) {
        Admin existing = adminRepo.findByEmail(buyer.getEmail()).orElse(null);
        if (existing != null) {
            return null;
        }
        return adminRepo.save(buyer);
    }

    @Override
    public Admin updateAdmins(String email, Admin admin) {
        return adminRepo.findByEmail(email).map(a -> {
            a.setEmail(email);
            return adminRepo.save(a);
        }).orElse(null);
    }

    @Override
    public void deleteAdmin(String email) {
        adminRepo.delete(adminRepo.findByEmail(email).orElse(null));
    }
    
}
