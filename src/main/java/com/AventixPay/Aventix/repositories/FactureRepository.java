package com.AventixPay.Aventix.repositories;


import com.AventixPay.Aventix.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository

public interface FactureRepository extends JpaRepository<Facture, Long> {
}
