package com.AventixPay.Aventix.service;

import com.AventixPay.Aventix.entities.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);

    User saveUser(User user);

    //Service pour mettre à jour le solde d'un utilisateur
    User updateSolde(Long userId, BigDecimal nouveauSolde);
}
