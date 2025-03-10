package com.AventixPay.Aventix.service;

import com.AventixPay.Aventix.entities.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {
    Optional<Card> findBySerialNumber(String serialNumber);

    Card saveCard(Card card);

    void deleteCard(Long id);

    List<Card> getAllCards();
}
