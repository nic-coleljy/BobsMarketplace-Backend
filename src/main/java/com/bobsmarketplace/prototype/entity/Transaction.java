package com.bobsmarketplace.prototype.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid", nullable = false, unique = true)
    private Long tid;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "dateTime", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "commission", nullable = false)
    private double commission;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "minPrice", nullable = false)
    private double minPrice;
    @Column(name = "totalCost", nullable = false)
    private double totalCost;
    @Column(name = "comment")
    private String comment;
    @Column(name = "deliveryCharge", nullable = false)
    private Double deliveryCharge;
    @Column(name = "subTotal", nullable = false)
    private Double subTotal;
    @Column(name = "financing", nullable = false)
    private Double financing;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "zip", nullable = false)
    private String zip;
    @Column(name = "isExpress", nullable = false)
    private Boolean isExpress;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cid_of_commodity", foreignKey = @ForeignKey(name = "fk1_transaction"))
    private Commodity commodityEntity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_of_buyer", foreignKey = @ForeignKey(name = "fk2_transaction"))
    private Buyer buyerEntity;
}
