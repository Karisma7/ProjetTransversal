package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumClass.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private Date date;
    private TransactionType type;
    private String description;

    @OneToOne(mappedBy = "transaction")
    private Notification notification;

    @ManyToOne
    @JoinColumn(name="id_user_payer")
    private User payer;

    @ManyToOne
    @JoinColumn(name="id_user_receiver")
    private User receiver;
}
