package com.doctork.doctorkonlinecounseling.UseCase.Counseling;

import com.doctork.doctorkonlinecounseling.boundary.exit.Counseling.OnlineCouneslingRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.Financial.WalletRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.Physician.PhysicianRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.Price.PriceRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.patient.PatientRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.Counseling.OnlineCounselingService;
import com.doctork.doctorkonlinecounseling.boundary.internal.RedisService;
import com.doctork.doctorkonlinecounseling.common.exceptions.Expiration.BillExpiredException;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;
import com.doctork.doctorkonlinecounseling.domain.Counseling.RedisPaymentDetail;
import com.doctork.doctorkonlinecounseling.domain.Enums.CounselingState;
import com.doctork.doctorkonlinecounseling.domain.Financial.Wallet;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class OnlineCounselingServiceImpl implements OnlineCounselingService {

    private final OnlineCouneslingRepository onlineCouneslingRepository;
    private final WalletRepository walletRepository;
    private final PhysicianRepository physicianRepository;
    private final PatientRepository patientRepository;

    private final PriceRepository priceRepository;

    private final RedisService redisService;

    public OnlineCounselingServiceImpl(PriceRepository priceRepository,PhysicianRepository physicianRepository,PatientRepository patientRepository,OnlineCouneslingRepository onlineCouneslingRepository,RedisService redisService,WalletRepository walletRepository) {
        this.onlineCouneslingRepository = onlineCouneslingRepository;
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

        OnlineCounseling onlineCounseling = onlineCouneslingRepository.createCounseling(patientId , physicianId , priceId);

            if (onlineCounseling.getCounselingState() == CounselingState.created){

            Patient patient = patientRepository.findPatientById(patientId);
            Wallet patientWallet = walletRepository.findWalletByUserId(patient.getUser().getId());
            Price price = priceRepository.findPriceById(priceId);
            onlineCounseling.setCounselingPrice(price.getCost());
            onlineCounseling.setUserWalletBalance(patientWallet.getBalance());
            if (patientWallet.getBalance().compareTo(price.getCost()) < 0)
                onlineCounseling.setTotalPayablePrice(price.getCost()-patientWallet.getBalance());
            else
                onlineCounseling.setTotalPayablePrice(0.0);

            RedisPaymentDetail redisPaymentDetail = new RedisPaymentDetail(onlineCounseling.getCounselingPrice(),onlineCounseling.getUserWalletBalance(),onlineCounseling.getTotalPayablePrice());

            onlineCounseling.setPaymentExpirationId(redisService.storeObject(redisPaymentDetail));

        }

        return onlineCounseling;

    }
}
