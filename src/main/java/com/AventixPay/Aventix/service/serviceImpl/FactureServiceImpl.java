package com.AventixPay.Aventix.service.serviceImpl;
import com.AventixPay.Aventix.entities.Facture;
import com.AventixPay.Aventix.repositories.FactureRepository;
import com.AventixPay.Aventix.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FactureServiceImpl implements FactureService {
    @Autowired
    private FactureRepository factureRepository;


    //recuperer une facture via son id
    @Override
    public Optional<Facture> getFactureById(Long id) {
        return factureRepository.findById(id);
    }

    //sauvegarder une facture
    @Override
    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }
}
