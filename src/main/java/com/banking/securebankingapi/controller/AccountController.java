package com.banking.securebankingapi.controller;

import com.banking.securebankingapi.entity.User;
import com.banking.securebankingapi.service.AccountService;
import com.banking.securebankingapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    private User getCurrentUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByUsername(username);
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance() {
        return ResponseEntity.ok(accountService.getBalance(getCurrentUser()));
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody Map<String, Double> body) {
        accountService.deposit(getCurrentUser(), body.get("amount"));
        return ResponseEntity.ok("Deposit successful");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody Map<String, Double> body) {
        accountService.withdraw(getCurrentUser(), body.get("amount"));
        return ResponseEntity.ok("Withdraw successful");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody Map<String, Object> body) {
        accountService.transfer(getCurrentUser(), (String) body.get("toUsername"), (Double) body.get("amount"));
        return ResponseEntity.ok("Transfer successful");
    }
}