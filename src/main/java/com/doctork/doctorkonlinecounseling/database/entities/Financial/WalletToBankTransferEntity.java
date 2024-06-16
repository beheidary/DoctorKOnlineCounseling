package com.doctork.doctorkonlinecounseling.database.entities.Financial;

import com.doctork.doctorkonlinecounseling.domain.Enums.TransactionStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.TransactionType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet_to_bank_transfers")

public class WalletToBankTransferEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private WalletEntity wallet;

    @Column(nullable = false)
    private String bankAccountNumber;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime transferDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;


    public WalletToBankTransferEntity() {
    }

    public WalletToBankTransferEntity(TransactionType type,WalletEntity wallet, String bankAccountNumber, Double amount, LocalDateTime transferDate, TransactionStatus status) {
        this.wallet = wallet;
        this.type = type;
        this.bankAccountNumber = bankAccountNumber;
        setAmount(amount);
        this.transferDate = transferDate;
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        transferDate = LocalDateTime.now();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WalletEntity getWallet() {
        return wallet;
    }

    public void setWallet(WalletEntity wallet) {
        this.wallet = wallet;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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
