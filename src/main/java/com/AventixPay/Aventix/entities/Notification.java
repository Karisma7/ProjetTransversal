package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumClass.TypeCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeCard type;
    private String message;

    @OneToOne(mappedBy = "notification")
    private Demand demand;

    @OneToOne
    @JoinColumn(name="notification_transaction_id")
    private Transaction transaction;


}
