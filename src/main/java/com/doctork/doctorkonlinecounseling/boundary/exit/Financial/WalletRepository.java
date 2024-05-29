package com.doctork.doctorkonlinecounseling.boundary.exit.Financial;

import com.doctork.doctorkonlinecounseling.domain.Financial.Wallet;
import com.doctork.doctorkonlinecounseling.domain.Financial.WalletToWalletTransfer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public interface WalletRepository {

    Wallet createWallet(String userId);

    Wallet getWalletByUserId(String userId);

    Wallet updateBalance(String userId, BigDecimal amount);

    Wallet findWalletById(Long walletId);

    WalletToWalletTransfer saveWalletToWalletTransaction (WalletToWalletTransfer walletToWalletTransfer);

    Wallet saveWallet (Wallet wallet);



}
