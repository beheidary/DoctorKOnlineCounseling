package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.Counseling.OnlineCounselingRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.CounselingNotfoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PriceNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Counseling.OnlineCounselingEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Patient.PatientEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Price.PriceEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.OnlineCounselingMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PatientMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PhysicianMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PriceMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.CounselingEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;
import com.doctork.doctorkonlinecounseling.domain.Enums.CounselingState;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class OnlineCounselingRepositoryImpl implements OnlineCounselingRepository {


    private final PriceMySqlRepository priceMySqlRepository;
    private final PhysicianMySqlRepository physicianMySqlRepository;
    private final PatientMySqlRepository patientMySqlRepository;
    private final CounselingEntityMapper counselingEntityMapper;

    private final OnlineCounselingMySqlRepository onlineCounselingMySqlRepository;


    public OnlineCounselingRepositoryImpl(CounselingEntityMapper counselingEntityMapper, OnlineCounselingMySqlRepository onlineCounselingMySqlRepository, PriceMySqlRepository priceMySqlRepository, PhysicianMySqlRepository physicianMySqlRepository, PatientMySqlRepository patientMySqlRepository) {
        this.priceMySqlRepository = priceMySqlRepository;
        this.onlineCounselingMySqlRepository = onlineCounselingMySqlRepository;
        this.counselingEntityMapper = counselingEntityMapper;
        this.physicianMySqlRepository = physicianMySqlRepository;
        this.patientMySqlRepository = patientMySqlRepository;
    }

    @Override
    @Transactional
    public OnlineCounseling createCounseling(String patientId, String physicianId, Long priceId) {

        try{

            PriceEntity priceEntity = priceMySqlRepository.findById(priceId)
                    .orElseThrow(PriceNotFoundException::new);
            PatientEntity patientEntity = patientMySqlRepository.findById(patientId)
                    .orElseThrow(PriceNotFoundException::new);
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId)
                    .orElseThrow(PriceNotFoundException::new);
            UserEntity creatorUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            OnlineCounselingEntity onlineCounselingEntity = new OnlineCounselingEntity(creatorUser,patientEntity,physicianEntity,priceEntity, CounselingState.created);
            onlineCounselingEntity = onlineCounselingMySqlRepository.save(onlineCounselingEntity);

            return counselingEntityMapper.onlineCounselingEntityToModel(onlineCounselingEntity);

        }catch (QueryTimeoutException ex){

            throw new DatabaseTimeOutException();

        }
        catch (DataIntegrityViolationException ex){

            throw new InvalidDataException();

        }
        catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
            
        }


    }

    @Override
    @Transactional(readOnly = true)
    public OnlineCounseling findCounseling(Long counselingId) {

        try {

            OnlineCounselingEntity onlineCounselingEntity = onlineCounselingMySqlRepository.findById(counselingId).orElseThrow(CounselingNotfoundException::new);

            return counselingEntityMapper.onlineCounselingEntityToModel(onlineCounselingEntity);

            }catch (QueryTimeoutException ex){

                throw new DatabaseTimeOutException();

            }
            catch (DataIntegrityViolationException ex){

            throw new InvalidDataException();

        }
            catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
            
        }

    }
}
