package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.Financial.WalletRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.UserNotfoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.WalletNotfoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Financial.WalletEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Financial.WalletToWalletTransferEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.UserMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.WalletMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.WalletToWalletTransferMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.FinancialEntitiesMapper;
import com.doctork.doctorkonlinecounseling.domain.Financial.Wallet;
import com.doctork.doctorkonlinecounseling.domain.Financial.WalletToWalletTransfer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class WalletRepositoryImpl implements WalletRepository {

    private final WalletMySqlRepository walletMySqlRepository;
    private final UserMySqlRepository userMySqlRepository;
    private final FinancialEntitiesMapper financialEntitiesMapper;

    private final WalletToWalletTransferMySqlRepository walletToWalletTransferMySqlRepository;


    public WalletRepositoryImpl(UserMySqlRepository userMySqlRepository,WalletMySqlRepository walletMySqlRepository, FinancialEntitiesMapper financialEntitiesMapper, WalletToWalletTransferMySqlRepository walletToWalletTransferMySqlRepository) {
        this.walletMySqlRepository = walletMySqlRepository;
        this.userMySqlRepository = userMySqlRepository;
        this.walletToWalletTransferMySqlRepository = walletToWalletTransferMySqlRepository;
        this.financialEntitiesMapper = financialEntitiesMapper;
    }


    @Override
    public Wallet createWallet(UUID userId) {

        try{

            WalletEntity walletEntity = new WalletEntity();

            UserEntity userEntity = userMySqlRepository.findById(userId)
                    .orElseThrow(UserNotfoundException::new);

            walletEntity.setUser(userEntity);
            walletEntity = walletMySqlRepository.save(walletEntity);

            return financialEntitiesMapper.walletEntityToModel(walletEntity);

        }catch (QueryTimeoutException ex){

            throw new DatabaseTimeOutException();

        }
        catch (DataIntegrityViolationException ex){

            throw new InvalidDataException();

        }
        catch (Exception ex){

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    @Transactional(readOnly = true)
    public Wallet findWalletByUserId(UUID userId) {

        try {

            WalletEntity walletEntity = walletMySqlRepository.findByUserId(userId)
                    .orElseThrow(UserNotfoundException::new);
            return financialEntitiesMapper.walletEntityToModel(walletEntity);


        } catch (QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex) {

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public Wallet getWalletByUserId(String userId) {
        return null;
    }

    @Override
    public Wallet updateBalance(String userId, BigDecimal amount) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Wallet findWalletById(Long walletId) {

        try {

            WalletEntity walletEntity = walletMySqlRepository.findById(walletId)
                    .orElseThrow(WalletNotfoundException::new);
            return financialEntitiesMapper.walletEntityToModel(walletEntity);


        } catch (QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex) {

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }


    }

    @Override
    @Transactional
    public WalletToWalletTransfer saveWalletToWalletTransaction(WalletToWalletTransfer walletToWalletTransfer) {
        try {

            WalletToWalletTransferEntity walletToWalletTransferEntity = financialEntitiesMapper.walletToWalletTransferModelToEntity(walletToWalletTransfer);
            walletToWalletTransferEntity = walletToWalletTransferMySqlRepository.save(walletToWalletTransferEntity);
            return financialEntitiesMapper.walletToWalletTransferEntityToModel(walletToWalletTransferEntity);

        } catch (QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex) {

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        try {

            WalletEntity walletEntity = financialEntitiesMapper.walletModelToEntity(wallet);
                    walletEntity = walletMySqlRepository.save(walletEntity);
            return financialEntitiesMapper.walletEntityToModel(walletEntity);


        } catch (QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex) {

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

}
