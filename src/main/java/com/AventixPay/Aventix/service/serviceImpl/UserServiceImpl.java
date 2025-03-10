package com.AventixPay.Aventix.service.serviceImpl;

import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.repositories.UserRepository;
import com.AventixPay.Aventix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public User updateSolde(Long userId, BigDecimal nouveauSolde) {
        if (nouveauSolde.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le solde ne peut pas être négatif.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        user.setSolde(nouveauSolde);
        return userRepository.save(user);
    }
}
