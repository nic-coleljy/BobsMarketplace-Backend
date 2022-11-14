package com.bobsmarketplace.prototype.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@DiscriminatorValue("Buyer")
public class Buyer extends User {
    @Column(name = "business_profile_url")
    private String businessProfileUrl;

    @Column(name = "credit_score")
    private double creditScore;

    @Column(name = "credit_limit")
    private long creditLimit;

    @JsonIgnore
    @OneToMany(mappedBy = "buyerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;
}
