package com.AventixPay.Aventix.service.serviceImpl;
import com.AventixPay.Aventix.repositories.UserRepository;
import com.AventixPay.Aventix.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public class TransactionServiceImpl implements TransactionService {

    //service pour verifier le solde et effectuer l'operation de debit du compte employé
    @Autowired
    private UserRepository userRepository;
    @Transactional
    @Override
    public BigDecimal debiter(BigDecimal usersolde, BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant à débiter doit être supérieur à zéro.");
        }
        if (usersolde.compareTo(montant) >= 0) {
            return usersolde.subtract(montant);
        } else {
            throw new RuntimeException("Fonds insuffisants !");
        }
    }
    @Override
    public BigDecimal crediter(BigDecimal commercantsolde, BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant à créditer doit être supérieur à zéro.");
        }
        return commercantsolde.add(montant);
    }
}
