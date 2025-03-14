package com.AventixPay.Aventix.service;

import com.AventixPay.Aventix.entities.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserService {
    //rechercher un utilisateur
    Optional<User> getUserById(Long id);
    
    //sauvegarder un utilisateur
    User saveUser(User user);

    //Service pour mettre à jour le solde d'un utilisateur
    void updateSolde(Long userId, BigDecimal nouveauSolde);
}
