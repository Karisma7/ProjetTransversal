package com.AventixPay.Aventix.service.serviceImpl;

import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.repositories.CardRepository;
import com.AventixPay.Aventix.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    //rechercher une carte via son serialNumber
    @Override
    public Optional<Card> findBySerialNumber(String serialNumber) {
        return cardRepository.findBySerialNumber(serialNumber);
    }

    //sauvegarder une carte 
    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    //rechercher une carte via son id
    @Override
    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }
    
    //supprimer une carte via son id
    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
    
    //chercher toutes les cartes
    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }
}
