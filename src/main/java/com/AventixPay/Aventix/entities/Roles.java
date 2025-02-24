package com.AventixPay.Aventix.entities;

import com.AventixPay.Aventix.enumClass.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
