package com.doctork.doctorkonlinecounseling.domain.Financial;

import com.doctork.doctorkonlinecounseling.domain.Enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
public class WalletToWalletTransfer {

    private Long id;
    private Wallet sourceWallet;

    private Wallet destinationWallet;
    private Double amount;
    private LocalDateTime transferDate;
    private TransactionStatus status;

    public WalletToWalletTransfer(Long id, Wallet sourceWallet, Wallet destinationWallet, Double amount, LocalDateTime transferDate, TransactionStatus status) {
        this.id = id;
        this.sourceWallet = sourceWallet;
        this.destinationWallet = destinationWallet;
        setAmount(amount);
        this.transferDate = transferDate;
        this.status = status;
    }

    public WalletToWalletTransfer(Wallet sourceWallet, Wallet destinationWallet, Double amount, TransactionStatus status) {
        this(null, sourceWallet, destinationWallet, amount, LocalDateTime.now(), status);
    }

    public WalletToWalletTransfer() {
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wallet getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(Wallet sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    public Wallet getDestinationWallet() {
        return destinationWallet;
    }

    public void setDestinationWallet(Wallet destinationWallet) {
        this.destinationWallet = destinationWallet;
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
}
