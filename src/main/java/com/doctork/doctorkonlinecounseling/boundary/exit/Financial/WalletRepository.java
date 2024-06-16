package com.doctork.doctorkonlinecounseling.boundary.exit.Financial;

import com.doctork.doctorkonlinecounseling.domain.Financial.Wallet;
import com.doctork.doctorkonlinecounseling.domain.Financial.WalletToWalletTransfer;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletRepository {

    Wallet createWallet(UUID userId);

    Wallet findWalletByUserId (UUID userId);

    Wallet getWalletByUserId(String userId);

    Wallet updateBalance(String userId, BigDecimal amount);

    Wallet findWalletById(Long walletId);

    WalletToWalletTransfer saveWalletToWalletTransaction (WalletToWalletTransfer walletToWalletTransfer);

    Wallet saveWallet (Wallet wallet);



}
