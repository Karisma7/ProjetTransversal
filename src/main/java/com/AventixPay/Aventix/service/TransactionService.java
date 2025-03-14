package com.AventixPay.Aventix.service;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;

public interface TransactionService {
    @Transactional
    //debiter un compte
    BigDecimal debiter(BigDecimal usersolde, BigDecimal montant);

    //crediter un compte
    BigDecimal crediter(BigDecimal solde, BigDecimal montant);

}
