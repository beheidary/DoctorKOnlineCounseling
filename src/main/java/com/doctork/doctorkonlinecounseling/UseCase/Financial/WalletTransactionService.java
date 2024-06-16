package com.doctork.doctorkonlinecounseling.UseCase.Financial;


import com.doctork.doctorkonlinecounseling.boundary.exit.Financial.WalletRepository;
import com.doctork.doctorkonlinecounseling.domain.Enums.TransactionStatus;
import com.doctork.doctorkonlinecounseling.domain.Financial.Wallet;
import com.doctork.doctorkonlinecounseling.domain.Financial.WalletToWalletTransfer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletTransactionService {

    private final WalletRepository walletRepository;

    public WalletTransactionService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void executeWalletToWalletTransaction(Wallet sourceWallet, Wallet destinationWallet, Double amount) {
        sourceWallet.setBalance(sourceWallet.getBalance()-amount);
        walletRepository.saveWallet(sourceWallet);
        destinationWallet.setBalance(destinationWallet.getBalance()+amount);
        walletRepository.saveWallet(destinationWallet);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateTransactionStatus(WalletToWalletTransfer transaction, TransactionStatus status) {
        transaction.setStatus(status);
        walletRepository.saveWalletToWalletTransaction(transaction);
    }
}
