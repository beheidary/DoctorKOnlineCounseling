package com.doctork.doctorkonlinecounseling.domain.Financial;

import com.doctork.doctorkonlinecounseling.domain.user.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wallet {

    private Long id;
    private User user;
    private Double balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Wallet(Long id, User user, Double balance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Wallet(User user, Double balance) {
        this(null, user, balance, LocalDateTime.now(), LocalDateTime.now());
    }

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Double getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setBalance(Double balance) {
        if (balance.compareTo(0.0) < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public void updateBalance(Double amount) {
        if (this.balance+amount.compareTo(0.0) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance = this.balance+amount;
        this.updatedAt = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}