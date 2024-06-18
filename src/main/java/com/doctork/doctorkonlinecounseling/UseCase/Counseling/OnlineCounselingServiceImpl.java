package com.doctork.doctorkonlinecounseling.UseCase.Counseling;

import com.doctork.doctorkonlinecounseling.UseCase.Security.MasterUserInfo;
import com.doctork.doctorkonlinecounseling.boundary.exit.Counseling.OnlineCounselingRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.Financial.WalletRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.Physician.PhysicianRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.Price.PriceRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.patient.PatientRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.Counseling.OnlineCounselingService;
import com.doctork.doctorkonlinecounseling.boundary.in.Financial.WalletService;
import com.doctork.doctorkonlinecounseling.boundary.internal.RedisService;
import com.doctork.doctorkonlinecounseling.common.exceptions.Expiration.BillExpiredException;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;
import com.doctork.doctorkonlinecounseling.domain.Counseling.RedisPaymentDetail;
import com.doctork.doctorkonlinecounseling.domain.Enums.CounselingState;
import com.doctork.doctorkonlinecounseling.domain.Financial.Wallet;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import org.springframework.stereotype.Service;


@Service
public class OnlineCounselingServiceImpl implements OnlineCounselingService {

    private final OnlineCounselingRepository onlineCounselingRepository;
    private final WalletRepository walletRepository;
    private final PhysicianRepository physicianRepository;
    private final PatientRepository patientRepository;

    private final PriceRepository priceRepository;
    private final MasterUserInfo masterUserInfo;

    private final WalletService walletService;

    private final RedisService redisService;

    public OnlineCounselingServiceImpl(PriceRepository priceRepository, PhysicianRepository physicianRepository, PatientRepository patientRepository, OnlineCounselingRepository onlineCounselingRepository, RedisService redisService,
                                       WalletRepository walletRepository, MasterUserInfo masterUserInfo, WalletService walletService) {
        this.onlineCounselingRepository = onlineCounselingRepository;
        this.walletService = walletService;
        this.masterUserInfo = masterUserInfo;
        this.priceRepository = priceRepository;
        this.physicianRepository = physicianRepository;
        this.patientRepository = patientRepository;
        this.walletRepository = walletRepository;
        this.redisService = redisService;
    }

    @Override
    public OnlineCounseling createCounseling(Long patientId, Long physicianId, Long priceId) {

        if(patientId == null | physicianId == null | priceId == null)
            throw new IdInputException();

        OnlineCounseling onlineCounseling = onlineCounselingRepository.createCounseling(patientId , physicianId , priceId);

            if (onlineCounseling.getCounselingState() == CounselingState.created){


            Patient patient = patientRepository.findPatientById(patientId);
            Wallet patientWallet = walletRepository.findWalletByUserId(patient.getUser().getId());
            Price price = priceRepository.findPriceById(priceId);
            onlineCounseling.setCounselingPrice(price.getCost());
            onlineCounseling.setUserWalletBalance(patientWallet.getBalance());
            if (patientWallet.getBalance().compareTo(onlineCounseling.getCounselingPrice()) < 0)
                onlineCounseling.setTotalPayablePrice(onlineCounseling.getCounselingPrice()-patientWallet.getBalance());
            else
                onlineCounseling.setTotalPayablePrice(0.0);

            RedisPaymentDetail redisPaymentDetail = new RedisPaymentDetail(onlineCounseling.getCounselingPrice(),onlineCounseling.getUserWalletBalance(),onlineCounseling.getTotalPayablePrice());

            onlineCounseling.setPaymentExpirationId(redisService.storeObject(redisPaymentDetail));
        }

        return onlineCounseling;

    }

    @Override
    public OnlineCounseling patientApproveCounseling(Long counselingId, String paymentExpirationId) {

        if(counselingId == null | paymentExpirationId == null)
            throw new IdInputException();


        RedisPaymentDetail redisPaymentDetail = redisService.getObject(paymentExpirationId,RedisPaymentDetail.class);
        if (redisPaymentDetail == null)
            throw new BillExpiredException();


        OnlineCounseling onlineCounseling = onlineCounselingRepository.findCounseling(counselingId);

        if (redisPaymentDetail.getTotalPayablePrice() == 0) {
            Wallet patientWallet = walletRepository.findWalletByUserId(onlineCounseling.getPatient().getUser().getId());

            walletService.performWalletToWalletTransfer(patientWallet.getId(),masterUserInfo.getAdminWalletId(),redisPaymentDetail.getCounselingPrice());
            onlineCounseling.setCounselingState(CounselingState.calling);
            patientWallet = walletRepository.findWalletByUserId(onlineCounseling.getPatient().getUser().getId());
            onlineCounseling.setCounselingPrice(0.0);
            onlineCounseling.setPaymentExpirationId(null);
            onlineCounseling.setUserWalletBalance(patientWallet.getBalance());
        }
        return onlineCounseling;
    }

    @Override
    public OnlineCounseling findCounseling(Long counselingId) {

        if(counselingId == null | counselingId == null)
            throw new IdInputException();

        //Todo check if bill is not Expired

        OnlineCounseling onlineCounseling = onlineCounselingRepository.findCounseling(counselingId);

        if (onlineCounseling.getCounselingState() == CounselingState.created){


            Wallet patientWallet = walletRepository.findWalletByUserId(onlineCounseling.getPatient().getUser().getId());
            onlineCounseling.setCounselingPrice(onlineCounseling.getPrice().getCost());
            onlineCounseling.setUserWalletBalance(patientWallet.getBalance());
            if (patientWallet.getBalance().compareTo(onlineCounseling.getCounselingPrice()) < 0)
                onlineCounseling.setTotalPayablePrice(onlineCounseling.getCounselingPrice()-patientWallet.getBalance());
            else
                onlineCounseling.setTotalPayablePrice(0.0);

            RedisPaymentDetail redisPaymentDetail = new RedisPaymentDetail(onlineCounseling.getCounselingPrice(),onlineCounseling.getUserWalletBalance(),onlineCounseling.getTotalPayablePrice());

            onlineCounseling.setPaymentExpirationId(redisService.storeObject(redisPaymentDetail));
        }


        return onlineCounseling;
    }
}
