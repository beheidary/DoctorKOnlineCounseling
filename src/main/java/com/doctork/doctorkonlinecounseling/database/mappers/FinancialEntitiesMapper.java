package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.Financial.WalletEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Financial.WalletToBankTransferEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Financial.WalletToWalletTransferEntity;
import com.doctork.doctorkonlinecounseling.domain.Financial.Wallet;
import com.doctork.doctorkonlinecounseling.domain.Financial.WalletToBankTransfer;
import com.doctork.doctorkonlinecounseling.domain.Financial.WalletToWalletTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FinancialEntitiesMapper {
    WalletEntity walletModelToEntity(Wallet wallet);

    Wallet walletEntityToModel(WalletEntity walletEntity);

    WalletToWalletTransferEntity walletToWalletTransferModelToEntity(WalletToWalletTransfer walletToWalletTransfer);
    WalletToWalletTransfer walletToWalletTransferEntityToModel(WalletToWalletTransferEntity walletToWalletTransferEntity);

    WalletToBankTransfer walletToBankTransferEntityToModel(WalletToBankTransferEntity walletToBankTransferEntity);
    WalletToBankTransferEntity walletToBankTransferModelToEntity(WalletToBankTransfer walletToBankTransfer);



}
