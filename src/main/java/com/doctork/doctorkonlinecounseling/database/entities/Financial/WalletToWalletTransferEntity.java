package com.doctork.doctorkonlinecounseling.database.entities.Financial;

import com.doctork.doctorkonlinecounseling.domain.Enums.TransactionStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet_to_wallet_transfers")
public class WalletToWalletTransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_wallet_id", nullable = false)
    private WalletEntity sourceWallet;

    @ManyToOne
    @JoinColumn(name = "destination_wallet_id", nullable = false)
    private WalletEntity destinationWallet;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime transferDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    public WalletToWalletTransferEntity() {
    }

    public WalletToWalletTransferEntity(WalletEntity sourceWallet, WalletEntity destinationWallet, BigDecimal amount, LocalDateTime transferDate, TransactionStatus status) {
        this.sourceWallet = sourceWallet;
        this.destinationWallet = destinationWallet;
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

    public WalletEntity getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(WalletEntity sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    public WalletEntity getDestinationWallet() {
        return destinationWallet;
    }

    public void setDestinationWallet(WalletEntity destinationWallet) {
        this.destinationWallet = destinationWallet;
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

//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = LocalDateTime.now();
//    }

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
