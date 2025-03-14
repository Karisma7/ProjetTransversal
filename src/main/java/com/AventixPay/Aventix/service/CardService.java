package com.AventixPay.Aventix.service;

import com.AventixPay.Aventix.entities.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    //rechercher une carte via son id
    Optional<Card> findBySerialNumber(String serialNumber);
    
    //enregistrer une carte
    Card saveCard(Card card);
    
    //supprimer une carte
    void deleteCard(Long id);
    
    //chercher toutes les cartes
    List<Card> getAllCards();

    //rechercher une carte via son id
    Optional<Card> getCardById(Long id);
    
}
