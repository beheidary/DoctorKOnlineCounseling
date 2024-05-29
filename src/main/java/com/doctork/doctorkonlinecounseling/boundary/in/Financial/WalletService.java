package com.doctork.doctorkonlinecounseling.boundary.in.Financial;

import java.math.BigDecimal;

public interface WalletService {

    void performWalletToWalletTransaction(Long sourceWalletId, Long destinationWalletId, BigDecimal amount);

}
