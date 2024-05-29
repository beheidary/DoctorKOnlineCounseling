package com.doctork.doctorkonlinecounseling.domain.Financial;

import com.doctork.doctorkonlinecounseling.domain.Enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WalletToBankTransfer {

    private Long id;
    private Wallet wallet;
    private String bankAccountNumber;
    private BigDecimal amount;
    private LocalDateTime transferDate;
    private TransactionStatus status;

    public WalletToBankTransfer(Long id, Wallet wallet, String bankAccountNumber, BigDecimal amount, LocalDateTime transferDate, TransactionStatus status) {
        this.id = id;
        this.wallet = wallet;
        this.bankAccountNumber = bankAccountNumber;
        setAmount(amount);
        this.transferDate = transferDate;
        this.status = status;
    }

    public WalletToBankTransfer(Wallet wallet, String bankAccountNumber, BigDecimal amount, TransactionStatus status) {
        this(null, wallet, bankAccountNumber, amount, LocalDateTime.now(), status);
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
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
}
