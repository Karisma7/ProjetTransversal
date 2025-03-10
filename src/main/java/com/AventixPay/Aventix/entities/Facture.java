package com.AventixPay.Aventix.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User factureRecues;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User factureEmues;


    @OneToOne
    private Transaction transaction;

    @Getter
    private Long idCommercant;
    @Getter
    private BigDecimal montant;
    private String article;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
