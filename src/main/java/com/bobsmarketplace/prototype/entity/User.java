package com.bobsmarketplace.prototype.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.Transient;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "users")
@DiscriminatorColumn(
    name = "role",
    discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("not null")
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false, unique = true)
    private Long id;
     
    @Column(nullable = false, unique = true, length = 100)
    private String email;
     
    @JsonIgnore
    @Column(nullable = false, length = 64)
    private String password;
     
    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    // this is the getter for role - currently either 'Buyer', 'Seller' or 'Admin' depending on user
    @Transient
    public String getDiscriminatorValue(){
        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);

        return val == null ? null : val.value();
    }

    @Column(name = "user_lock", nullable = false)
    private int lock;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(final String password) {
        this.password = password;
    }
}