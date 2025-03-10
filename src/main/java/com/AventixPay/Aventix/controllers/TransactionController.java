package com.AventixPay.Aventix.controllers;

import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.entities.Facture;
import com.AventixPay.Aventix.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private final CardService cardService;
    private final RFIDService rfidService;
    private final TransactionService transactionService;
    private  FactureService factureService;
    private  UserService userService;

    // Injection des services via le constructeur
    public TransactionController(RFIDService rfidService, CardService cardService, FactureService factureService, TransactionService transactionService, UserService userService) {
        this.rfidService = rfidService;
        this.cardService = cardService;
        this.factureService = factureService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping("/paiement")
    public ResponseEntity<?> paiement(Facture facture) {
        Facture savedFacture = factureService.saveFacture(facture);
        Long idCommercant = facture.getIdCommercant();
        BigDecimal montant = facture.getMontant();
        // Recherche de la carte RFID
        String uidString = rfidService.readSerialNumberFromRFID();
        logger.info("Carte RFID détectée : {}", uidString);
        // Recherche de la carte dans la base
        Optional<Card> optionalCard = cardService.findBySerialNumber(uidString);
        if (optionalCard.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Carte avec le numéro de série " + uidString + " non trouvée"));
        }
        Card card = optionalCard.get();  // Extraire la carte
        User userCard = card.getUser();  // Accéder à l'utilisateur
        BigDecimal userSolde = userCard.getSolde();
        // Débiter l'utilisateur
        BigDecimal newUserSolde = transactionService.debiter(userSolde, montant);
        userService.updateSolde(userCard.getId(), newUserSolde);
        // Mettre à jour le solde du commerçant
        return userService.getUserById(idCommercant)
                .map(userCommercant -> {
                    BigDecimal userCommercantNewSolde = transactionService.crediter(userCommercant.getSolde(), montant);
                    userService.updateSolde(userCommercant.getId(), userCommercantNewSolde);
                    return ResponseEntity.ok(Map.of("message", "Transaction réussie"));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Utilisateur commerçant non trouvé")));
    }
        /* Ajouter le service de persistance des valeures dans l'entité transaction*****************
        ***************************************************************************************** */
}
