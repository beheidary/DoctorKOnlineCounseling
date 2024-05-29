package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Financial.WalletToBankTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletToBankTransferMySqlRepository extends JpaRepository<WalletToBankTransferEntity, Long> {
}
