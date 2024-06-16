package com.doctork.doctorkonlinecounseling.boundary.in.Financial;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface WalletService {

    void performWalletToWalletTransfer(Long sourceWalletId, Long destinationWalletId, Double amount);
}
