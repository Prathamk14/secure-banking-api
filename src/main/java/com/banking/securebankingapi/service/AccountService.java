package com.banking.securebankingapi.service;

import com.banking.securebankingapi.entity.Account;
import com.banking.securebankingapi.entity.Transaction;
import com.banking.securebankingapi.entity.User;
import com.banking.securebankingapi.repository.AccountRepository;
import com.banking.securebankingapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public double getBalance(User user) {
        Account account = accountRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    @Transactional
    public void deposit(User user, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Deposit amount must be positive");
        }
        Account account = accountRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setType("DEPOSIT");
        tx.setAmount(amount);
        tx.setToAccount(account);
        tx.setTimestamp(LocalDateTime.now());
        transactionRepository.save(tx);
    }

    @Transactional
    public void withdraw(User user, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Withdraw amount must be positive");
        }
        Account account = accountRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setType("WITHDRAW");
        tx.setAmount(amount);
        tx.setFromAccount(account);
        tx.setTimestamp(LocalDateTime.now());
        transactionRepository.save(tx);
    }

    @Transactional
    public void transfer(User fromUser, String toUsername, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Transfer amount must be positive");
        }
        User toUser = userService.findByUsername(toUsername);
        if (toUser == null) {
            throw new RuntimeException("Recipient not found");
        }
        Account fromAccount = accountRepository.findByUser(fromUser).orElseThrow(() -> new RuntimeException("Account not found"));
        Account toAccount = accountRepository.findByUser(toUser).orElseThrow(() -> new RuntimeException("Recipient account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction tx = new Transaction();
        tx.setType("TRANSFER");
        tx.setAmount(amount);
        tx.setFromAccount(fromAccount);
        tx.setToAccount(toAccount);
        tx.setTimestamp(LocalDateTime.now());
        transactionRepository.save(tx);
    }
}