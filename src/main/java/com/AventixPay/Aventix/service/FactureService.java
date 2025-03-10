package com.AventixPay.Aventix.service;

import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.entities.Facture;

import java.util.Optional;

public interface FactureService {
    //recuperer l'id du commercant et le mantant en fonction de l'id de la facture
    Optional<Facture> getFactureById(Long id);

    Facture saveFacture(Facture facture);
}
