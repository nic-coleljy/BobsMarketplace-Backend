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
public class CommodityName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cnid;
    @Column(name="name", nullable = false, unique = true)
    private String name;
    @Column(name="pictureurl", nullable = false)
    private String pictureUrl;
    @Column(name="uom", nullable = false)
    private String uom;

    @JsonIgnore
    @OneToMany(mappedBy = "commodityNameEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commodity> commodities;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "catid_of_category", foreignKey = @ForeignKey(name = "fk1_commodityName"))
    private Category categoryEntity;
}
