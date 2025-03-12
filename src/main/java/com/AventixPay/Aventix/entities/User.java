package com.AventixPay.Aventix.entities;

import com.AventixPay.Aventix.enumClass.Role;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    @Setter
    @Getter
    private BigDecimal solde;
    private Role role;

    @OneToOne(mappedBy = "user")
    private Card card;

    @ManyToOne
    private Entreprise entreprise;

    @OneToMany(mappedBy = "user")
    private List<Roles> roles;

    @OneToMany(mappedBy = "demandeur")
    private List<Demand> demandeEmises;

    @OneToMany(mappedBy = "recepteur")
    private List<Demand> demandeRecues;

    @OneToMany(mappedBy = "payer")
    private List<Transaction> transactionsEffectuees;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> transactionsRecues;

    @OneToMany(mappedBy = "factureEmues")
    private List<Facture> facturesEmises;

    @OneToMany(mappedBy = "factureRecues")
    private List<Facture> facturesRecues;


    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }
}
