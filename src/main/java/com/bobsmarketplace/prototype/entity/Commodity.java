package com.bobsmarketplace.prototype.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "expressCharge", nullable = false)
    private double expressCharge;
    @Column(name = "standardCharge", nullable = false)
    private double standardCharge;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "cnid_of_commodityname", foreignKey = @ForeignKey(name = "fk1_commodity"))
    private CommodityName commodityNameEntity;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @JoinColumn(name = "id_of_seller", foreignKey = @ForeignKey(name = "fk3_commodity"))
    private Seller sellerEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "commodityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;
}
