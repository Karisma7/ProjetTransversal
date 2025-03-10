package com.AventixPay.Aventix.repositories;

import com.AventixPay.Aventix.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findBySerialNumber(String Number);
}
