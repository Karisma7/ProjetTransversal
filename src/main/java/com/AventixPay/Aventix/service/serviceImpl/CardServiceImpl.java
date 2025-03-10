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
    @Override
    public Optional<Card> findBySerialNumber(String serialNumber) {
        return cardRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }
}
