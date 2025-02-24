package com.AventixPay.Aventix.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amountToPay;

    @ManyToOne
    private User factureRecues;

    @ManyToOne
    private User factureEmues;


    @OneToOne
    private Transaction transaction;



}
