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
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeCard type;
    private String message;
    private String description;

    @ManyToOne
    @JoinColumn(name="id_user_demandeur")
    private User demandeur;

    @ManyToOne
    @JoinColumn(name="id_user_recepteur")
    private User recepteur;

    @OneToOne
    @JoinColumn(name="demande_notification")
    private Notification notification;


}
