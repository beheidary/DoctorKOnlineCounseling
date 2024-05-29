package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Financial.WalletToWalletTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletToWalletTransferMySqlRepository extends JpaRepository<WalletToWalletTransferEntity, Long> {
}
