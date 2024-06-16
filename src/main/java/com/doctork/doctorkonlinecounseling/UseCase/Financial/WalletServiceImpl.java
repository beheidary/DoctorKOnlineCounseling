package com.doctork.doctorkonlinecounseling.UseCase.Financial;

import com.doctork.doctorkonlinecounseling.boundary.exit.Financial.WalletRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.Financial.WalletService;
import com.doctork.doctorkonlinecounseling.domain.Enums.TransactionStatus;
import com.doctork.doctorkonlinecounseling.domain.Financial.Wallet;
import com.doctork.doctorkonlinecounseling.domain.Financial.WalletToWalletTransfer;
import org.springframework.stereotype.Service;



import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;
    public WalletServiceImpl(WalletRepository walletRepository,WalletTransactionService walletTransactionService) {
        this.walletRepository = walletRepository;
        this.walletTransactionService = walletTransactionService;
    }

    @Override
    public void performWalletToWalletTransfer(Long sourceWalletId, Long destinationWalletId, Double amount) {

       Wallet sourceWallet = walletRepository.findWalletById(sourceWalletId);
       Wallet destinationWallet = walletRepository.findWalletById(destinationWalletId);

        if (sourceWallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds in source wallet");
        }

        WalletToWalletTransfer transaction = new WalletToWalletTransfer();
        transaction.setSourceWallet(sourceWallet);
        transaction.setDestinationWallet(destinationWallet);
        transaction.setAmount(amount);
        transaction.setStatus(TransactionStatus.PENDING);
        transaction = walletRepository.saveWalletToWalletTransaction(transaction);

        try {
            walletTransactionService.executeWalletToWalletTransaction(sourceWallet, destinationWallet, amount);
            transaction.setStatus(TransactionStatus.COMPLETED);
            walletRepository.saveWalletToWalletTransaction(transaction);
        } catch (Exception e) {
            walletTransactionService.updateTransactionStatus(transaction, TransactionStatus.FAILED);
        }

    }
}
