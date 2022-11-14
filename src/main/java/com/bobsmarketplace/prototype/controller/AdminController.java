package com.bobsmarketplace.prototype.controller;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Admin;
import com.bobsmarketplace.prototype.exception.admin.AdminExistsException;
import com.bobsmarketplace.prototype.exception.admin.AdminNotFoundException;
import com.bobsmarketplace.prototype.service.AdminService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private AdminService adminService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("")
    public List<Admin> getAllAdmins() {
        return adminService.listAdmins();
    }

    @GetMapping("/{email}")
    public Admin get(@PathVariable String email) {
        Admin result = adminService.getAdmins(email);
        if (result == null) {
            throw new AdminNotFoundException();
        }
        return result;
    }

    @PostMapping("")
    public Admin AddAdmin(@RequestBody Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        Admin added = adminService.addAdmins(admin);
        if (added == null) {
            throw new AdminExistsException();
        }
        return added;
    }

    @DeleteMapping("/{email}")
    public void deleteAdmin(@PathVariable String email) {
        try {
            adminService.deleteAdmin(email);
        } catch (Exception e) {
            throw new AdminNotFoundException();
        }
    }
}
