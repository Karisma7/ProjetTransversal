package com.AventixPay.Aventix.service;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;

public interface TransactionService {
    @Transactional
    BigDecimal debiter(BigDecimal usersolde, BigDecimal montant);

    BigDecimal crediter(BigDecimal solde, BigDecimal montant);

}
