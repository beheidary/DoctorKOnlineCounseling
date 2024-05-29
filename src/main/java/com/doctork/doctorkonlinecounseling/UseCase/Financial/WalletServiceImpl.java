package com.doctork.doctorkonlinecounseling.UseCase.Financial;

import com.doctork.doctorkonlinecounseling.boundary.exit.Financial.WalletRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.Financial.WalletService;
import com.doctork.doctorkonlinecounseling.database.entities.Financial.WalletEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.WalletMySqlRepository;
import com.doctork.doctorkonlinecounseling.domain.Enums.TransactionStatus;
import com.doctork.doctorkonlinecounseling.domain.Financial.Wallet;
import com.doctork.doctorkonlinecounseling.domain.Financial.WalletToWalletTransfer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }





    @Override
    @Transactional
    public void performWalletToWalletTransaction(Long sourceWalletId, Long destinationWalletId, BigDecimal amount) {

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

        sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
        walletRepository.saveWallet(sourceWallet);

        // Todo Simulate buffer
        // Todo store this in an intermediate table or service

        destinationWallet.setBalance(destinationWallet.getBalance().add(amount));
        walletRepository.saveWallet(destinationWallet);

        transaction.setStatus(TransactionStatus.COMPLETED);
        walletRepository.saveWalletToWalletTransaction(transaction);

        //Todo fail state and rollback


    }
}
