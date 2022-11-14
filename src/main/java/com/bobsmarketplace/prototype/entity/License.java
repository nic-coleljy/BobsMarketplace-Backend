package com.bobsmarketplace.prototype.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "license")
public class License {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="license_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "type_of_good", nullable = false, unique = true)
    private String typeOfGood;

    @Column(name = "issued_by", nullable = false)
    private String issuedBy;

    @Column(name = "expiry_date")
    private String expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Seller seller;
}
