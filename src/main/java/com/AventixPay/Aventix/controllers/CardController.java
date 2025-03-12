package com.AventixPay.Aventix.controllers;

import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.service.CardService;
import com.AventixPay.Aventix.service.RFIDService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/card")
public class  CardController {

    private final CardService cardService;
    private final RFIDService rfidService;

    // Injection du service RFID via le constructeur
    public CardController(RFIDService rfidService, CardService cardService) {
        this.rfidService = rfidService;
        this.cardService = cardService;
    }

    @PostMapping("/add")
    public ResponseEntity<Card> addCard(@RequestBody Card card) {
        Card savedCard = cardService.saveCard(card);
        return ResponseEntity.ok(savedCard);
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<?> getCardBySerial(@PathVariable String serialNumber) {
        Optional<Card> card = cardService.findBySerialNumber(serialNumber);
        if (card.isPresent()) {
            return ResponseEntity.ok(card.get());
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Carte avec le numéro de série " + serialNumber + " non trouvée");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok("Carte supprimée avec succès");
    }

    //Controller test: read serialNumber
    @GetMapping("/readCard")
    public ResponseEntity<String> readSerialNumber() {
        return ResponseEntity.ok(rfidService.readSerialNumberFromRFID());
    }

    //Controller test : trouver l'entité correspondante à partir du serial number
    @GetMapping("/read")
    public ResponseEntity<?> readCard() {
            String uidString = rfidService.readSerialNumberFromRFID();
            // Recherche de la carte en base
            Optional<Card> card = cardService.findBySerialNumber(uidString);
            if (card.isPresent()) {
                return ResponseEntity.ok(card.get());
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Carte avec le numéro de série " + uidString + " non trouvée");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
    }
}
