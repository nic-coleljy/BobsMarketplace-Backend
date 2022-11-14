package com.bobsmarketplace.prototype.service;

import java.util.List;

import com.bobsmarketplace.prototype.entity.Admin;

public interface AdminService {
    List<Admin> listAdmins();
    Admin getAdmins(String email);
    Admin addAdmins(Admin admin);
    Admin updateAdmins(String email, Admin admin);
    void deleteAdmin(String email);
}