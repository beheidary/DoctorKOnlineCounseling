package com.doctork.doctorkonlinecounseling.domain.Financial;

import com.doctork.doctorkonlinecounseling.domain.Enums.TransactionStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WalletToBankTransfer {

    private Long id;
    private Wallet wallet;
    private String bankAccountNumber;
    private Double amount;
    private LocalDateTime transferDate;
    private TransactionStatus status;
    private TransactionType type;


    public WalletToBankTransfer(Long id,TransactionType type, Wallet wallet, String bankAccountNumber, Double amount, LocalDateTime transferDate, TransactionStatus status) {
        this.id = id;
        this.type = type;
        this.wallet = wallet;
        this.bankAccountNumber = bankAccountNumber;
        setAmount(amount);
        this.transferDate = transferDate;
        this.status = status;
    }

    public WalletToBankTransfer(TransactionType type,Wallet wallet, String bankAccountNumber, Double amount, TransactionStatus status) {
        this(null,type, wallet, bankAccountNumber, amount, LocalDateTime.now(), status);
    }

    public WalletToBankTransfer() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        if (amount.compareTo(0.0) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
