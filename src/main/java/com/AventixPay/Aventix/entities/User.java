package com.AventixPay.Aventix.entities;

import com.AventixPay.Aventix.enumClass.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Role role;

    @OneToOne
    private Card card;

    @ManyToOne
    private Entreprise entreprise;

    @OneToMany
    private List<Demand> listDemand;
}
