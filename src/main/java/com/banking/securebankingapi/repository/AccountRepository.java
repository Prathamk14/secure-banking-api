package com.banking.securebankingapi.repository;

import com.banking.securebankingapi.entity.Account;
import com.banking.securebankingapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUser(User user);
}