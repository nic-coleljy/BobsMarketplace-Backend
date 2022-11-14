package com.bobsmarketplace.prototype.serviceImplementation;

import com.bobsmarketplace.prototype.entity.*;
import com.bobsmarketplace.prototype.entity.dto.login.AdminLoginResponse;
import com.bobsmarketplace.prototype.entity.dto.login.BuyerLoginResponse;
import com.bobsmarketplace.prototype.entity.dto.login.SellerLoginResponse;
import com.bobsmarketplace.prototype.entity.dto.login.UserLogin;
import com.bobsmarketplace.prototype.entity.dto.login.UserLoginResponse;
import com.bobsmarketplace.prototype.entity.dto.register.UserRegister;
import com.bobsmarketplace.prototype.exception.buyer.BuyerNotFoundException;
import com.bobsmarketplace.prototype.exception.login.IncorrectPasswordException;
import com.bobsmarketplace.prototype.exception.login.UserLockedException;
import com.bobsmarketplace.prototype.exception.login.UserNotFoundException;
import com.bobsmarketplace.prototype.repository.*;
import com.bobsmarketplace.prototype.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BuyerRepository buyerRepo;

    @Autowired
    private SellerRepository sellerRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final String creditScoreURL = "http://13.229.231.81:5000/api/credit-score?file=";

    public void register(UserRegister userRegister) {
        if (userRegister.getRole().equals("Buyer")) {
            // Buyer
            Buyer buyer = new Buyer();
            buyer.setEmail(userRegister.getEmail());
            String hashedPassword = encoder.encode(userRegister.getPassword());
            buyer.setPassword(hashedPassword);
            buyer.setCompanyName(userRegister.getCompanyName());
            buyer.setBusinessProfileUrl(userRegister.getBusinessProfileUrl().orElse(null));

            String url = creditScoreURL + buyer.getBusinessProfileUrl();
            RestTemplate restTemplate = new RestTemplate();

            // update credit score of buyer
            ResponseEntity<CreditScore> response = restTemplate.getForEntity(url, CreditScore.class);
            if (response.getStatusCode().value() != 200) {
                throw new BuyerNotFoundException("Business profile URL doesn't exist");
            }
            buyer.setCreditScore(response.getBody().getCreditworthiness());
            buyerRepo.save(buyer);
        } else {
            // Seller
            Seller seller = new Seller();
            seller.setEmail(userRegister.getEmail());
            seller.setPassword(encoder.encode(userRegister.getPassword()));
            seller.setCompanyName(userRegister.getCompanyName());
            sellerRepo.save(seller);
        }
    }

    public <T extends UserLoginResponse> UserLoginResponse login(@RequestBody UserLogin userLogin) {
        User user = userRepo.findByEmail(userLogin.getEmail());
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (user.getLock() == 1) {
            throw new UserLockedException();
        }
        if (encoder.matches(userLogin.getPassword(), user.getPassword())) {
            if (user.getDiscriminatorValue().equals("Buyer")) {
                Buyer buyer = buyerRepo.findByEmail(user.getEmail()).orElse(null);
                return new BuyerLoginResponse(buyer.getId(), buyer.getEmail(), buyer.getCompanyName(),
                        buyer.getDiscriminatorValue(), buyer.getBusinessProfileUrl(), buyer.getCreditScore(),
                        buyer.getCreditLimit());
            } else if (user.getDiscriminatorValue().equals("Seller")) {
                Seller seller = sellerRepo.findByEmail(user.getEmail()).orElse(null);
                return new SellerLoginResponse(seller.getId(), seller.getEmail(), seller.getCompanyName(),
                        seller.getDiscriminatorValue(), seller.getCredibility(),
                        seller.getLicenses());
            } else {
                Admin admin = adminRepo.findByEmail(user.getEmail()).orElse(null);
                return new AdminLoginResponse(admin.getId(), admin.getEmail(), admin.getCompanyName(),
                        admin.getDiscriminatorValue());
            }
        } else {
            throw new IncorrectPasswordException();
        }
    }
}
