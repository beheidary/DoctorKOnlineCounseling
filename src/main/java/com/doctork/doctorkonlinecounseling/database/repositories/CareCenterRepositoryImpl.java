package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.CareCenterRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.Duplicate.DuplicateException;
import com.doctork.doctorkonlinecounseling.common.exceptions.Duplicate.DuplicateExpertiseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.ExpertiseNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.NotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PhysicianNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.CareCenter.*;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.*;
import com.doctork.doctorkonlinecounseling.database.mappers.CareCenterEntityMapper;
import com.doctork.doctorkonlinecounseling.database.mappers.PhysicianEntityMapper;
import com.doctork.doctorkonlinecounseling.database.mongoRepositories.RequestedCareCenterMongoRepository;
import com.doctork.doctorkonlinecounseling.domain.CareCenter.*;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CareCenterRepositoryImpl implements CareCenterRepository {

    private final RequestedCareCenterMongoRepository requestedCareCenterMongoRepository;
    private final ProvinceCitiesMySqlRepository provinceCitiesMySqlRepository;
    private final CareCenterMySqlRepository careCenterMySqlRepository;
    private final PhysicianCareCenterMySqlRepository physicianCareCenterMySqlRepository;
    private final PhysicianMySqlRepository physicianMySqlRepository;
    private final PhysicianEntityMapper physicianEntityMapper;


    private final CareCenterTypeMySqlRepository careCenterTypeMySqlRepository;
    private final CareCenterEntityMapper careCenterEntityMapper;

    public CareCenterRepositoryImpl(PhysicianEntityMapper physicianEntityMapper , PhysicianMySqlRepository physicianMySqlRepository,PhysicianCareCenterMySqlRepository physicianCareCenterMySqlRepository,CareCenterTypeMySqlRepository careCenterTypeMySqlRepository,ProvinceCitiesMySqlRepository provinceCitiesMySqlRepository,CareCenterMySqlRepository careCenterMySqlRepository,RequestedCareCenterMongoRepository requestedCareCenterMongoRepository, CareCenterEntityMapper careCenterEntityMapper) {
        this.requestedCareCenterMongoRepository = requestedCareCenterMongoRepository;
        this.physicianCareCenterMySqlRepository = physicianCareCenterMySqlRepository;
        this.physicianMySqlRepository = physicianMySqlRepository;
        this.physicianEntityMapper = physicianEntityMapper;
        this.careCenterTypeMySqlRepository = careCenterTypeMySqlRepository;
        this.provinceCitiesMySqlRepository = provinceCitiesMySqlRepository;
        this.careCenterEntityMapper = careCenterEntityMapper;
        this.careCenterMySqlRepository = careCenterMySqlRepository;
    }



    @Override
    public void requestToAddCareCenter(RequestedCareCenter requestedCareCenter) {
        try {

            if (requestedCareCenterMongoRepository.findByCenterName(requestedCareCenter.getCenterName()) == null){
                ProvinceCitiesEntity provinceCitiesEntity = provinceCitiesMySqlRepository.findById(requestedCareCenter.getCityId()).orElseThrow(NotFoundException::new);
                ProvinceCitiesEntity parentProvinceCitiesEntity = provinceCitiesMySqlRepository.findById(provinceCitiesEntity.getParent().longValue()).orElseThrow(NotFoundException::new);
                RequestedCareCenterMongoEntity requestedCareCenterMongoEntity = careCenterEntityMapper.mongoModelToEntity(requestedCareCenter);
                requestedCareCenterMongoEntity.setCityTitle(provinceCitiesEntity.getTitle());
                requestedCareCenterMongoEntity.setProvinceTitle(parentProvinceCitiesEntity.getTitle());
                requestedCareCenterMongoRepository.save(requestedCareCenterMongoEntity);
            }
            else
                throw new DuplicateException();
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public List<RequestedCareCenter> waitingCareCenters() {
        try {

            List<RequestedCareCenterMongoEntity> requestedCareCenterMongoEntities = requestedCareCenterMongoRepository.findByState(State.Waiting);
            return careCenterEntityMapper.mongoEntityToModel(requestedCareCenterMongoEntities);
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional
    public PhysicianCareCenter CareCenterAcceptanceDecision(RequestedCareCenter requestedCareCenter , Long careCenterTypeId) {
        try {
            RequestedCareCenterMongoEntity requestedCareCenterMongoEntity = requestedCareCenterMongoRepository.findById(requestedCareCenter.get_id()).orElseThrow(ExpertiseNotFoundException::new);
            if(requestedCareCenterMongoEntity.getState() == State.Waiting && (requestedCareCenter.getState() == State.Rejected || requestedCareCenter.getState() == State.Approved)) {
                requestedCareCenterMongoEntity.setState(requestedCareCenter.getState());
                requestedCareCenterMongoEntity.setCenterName(requestedCareCenter.getCenterName());
                requestedCareCenterMongoEntity.setCallNumber(requestedCareCenter.getCallNumber());
                requestedCareCenterMongoEntity.setAddress(requestedCareCenter.getAddress());
                requestedCareCenterMongoEntity.setUpdatedAt(requestedCareCenter.getUpdatedAt());
                requestedCareCenterMongoEntity.setDescriptions(requestedCareCenter.getDescriptions());
                requestedCareCenterMongoEntity.setImageName(requestedCareCenter.getImageName());
                requestedCareCenterMongoEntity.setLongitude(requestedCareCenter.getLongitude());
                requestedCareCenterMongoEntity.setLatitude(requestedCareCenter.getLatitude());

                requestedCareCenter = careCenterEntityMapper.mongoEntityToModel(requestedCareCenterMongoRepository.save(requestedCareCenterMongoEntity));
                if (requestedCareCenter.getState() == State.Approved) {
                    CareCenterTypeEntity careCenterTypeEntity = careCenterTypeMySqlRepository.findById(careCenterTypeId).orElseThrow(NotFoundException::new);
                    CareCenterEntity careCenterEntity = careCenterEntityMapper.mongoModelToMySqlEntity(requestedCareCenter);
                    careCenterEntity.setCareCenterType(careCenterTypeEntity);
                    careCenterEntity.setStatus(Status.Active);
                    careCenterEntity = careCenterMySqlRepository.save(careCenterEntity);
                    return new PhysicianCareCenter(careCenterEntityMapper.careCenterEntityToModel(careCenterEntity ), requestedCareCenter.getPid(), requestedCareCenter.getDays());
                }
                return new PhysicianCareCenter(careCenterEntityMapper.careCenterEntityToModel(careCenterEntityMapper.mongoModelToMySqlEntity(requestedCareCenter)) , requestedCareCenter.getPid(), requestedCareCenter.getDays());
            }else
                throw new InvalidDataException();
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateExpertiseException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public PhysicianCareCenter addPhysicianToCareCenter(String physicianId, Long careCenterId, Set<WeekDay> days) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
            CareCenterEntity careCenterEntity = careCenterMySqlRepository.findById(careCenterId).orElseThrow(NotFoundException::new);

            PhysicianCareCenterEntity physicianCareCenterEntity = new PhysicianCareCenterEntity();
            physicianCareCenterEntity.setCareCenter(careCenterEntity);
            physicianCareCenterEntity.setCareCenterId(careCenterId);
            physicianCareCenterEntity.setDays(days);
            physicianCareCenterEntity.setPhysicianId(physicianId);
            physicianCareCenterEntity.setPhysician(physicianEntity);
            return careCenterEntityMapper.physicianCareCenterEntityToModel(physicianCareCenterMySqlRepository.save(physicianCareCenterEntity));

        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateExpertiseException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }


    @Override
    public PhysicianCareCenter editPhysicianWorkingDaysWithCareCenter(String physicianId, Long careCenterId, Set<WeekDay> days) {
        try{
            PhysicianCareCenterId physicianCareCenterId = new PhysicianCareCenterId(physicianId,careCenterId);
            PhysicianCareCenterEntity physicianCareCenterEntity = physicianCareCenterMySqlRepository.findById(physicianCareCenterId).orElseThrow(NotFoundException::new);
            physicianCareCenterEntity.setDays(days);
            return careCenterEntityMapper.physicianCareCenterEntityToModel(physicianCareCenterMySqlRepository.save(physicianCareCenterEntity));
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateExpertiseException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void TerminationOfCooperationPhysicianWithCareCenter(String physicianId, Long careCenterId) {
        try{
            PhysicianCareCenterId physicianCareCenterId = new PhysicianCareCenterId(physicianId,careCenterId);
            physicianCareCenterMySqlRepository.deleteById(physicianCareCenterId);
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateExpertiseException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public Set<PhysicianCareCenter> AllTheCareCentersWhereThePhysicianWorks(String physicianId) {
        try{
            Set<PhysicianCareCenter> physicianCareCenter = careCenterEntityMapper.physicianCareCenterEntityToModel(physicianCareCenterMySqlRepository.findAllByPhysicianId(physicianId));

            return physicianCareCenter;
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateExpertiseException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public List<CareCenterTypes> allActiveCenterTypes() {
       try{
           return careCenterEntityMapper.careCenterTypesEntityToModel(careCenterTypeMySqlRepository.findAllByStatus(Status.Active));
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateExpertiseException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public List<CareCenter> allActiveCareCenter() {

     try {

         List<CareCenterEntity> careCenterEntities = careCenterMySqlRepository.findAllByStatus(Status.Active);
         return careCenterEntityMapper.careCenterEntityToModel(careCenterEntities);

    } catch (
    QueryTimeoutException ex) {

        throw new DatabaseTimeOutException();

    } catch (DataIntegrityViolationException ex) {

        throw new DuplicateExpertiseException();

    }catch (Exception ex){
        if(ex instanceof BaseException)
            throw ex;
        throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

    }
    }

    @Override
    @Transactional
    public CareCenterWithPhysicians allPhysiciansOfCareCenter(Long centerId) {
        try {
            List<PhysicianCareCenterEntity> physicianCareCenterEntities = physicianCareCenterMySqlRepository.findAllByCareCenterId(centerId);
            List<Physician> physicians = new ArrayList<>();

            for (PhysicianCareCenterEntity physicianCareCenterEntity : physicianCareCenterEntities)
                physicians.add(physicianEntityMapper.entityToModel(physicianCareCenterEntity.getPhysician()));
            if (physicianCareCenterEntities.size() > 0){
                CareCenterWithPhysicians careCenterWithPhysicians = careCenterEntityMapper.careCenterEntityToModelWithPhysicians(physicianCareCenterEntities.get(0).getCareCenter());
                careCenterWithPhysicians.setPhysicians(physicians);
                return careCenterWithPhysicians;}
            return null;

        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateExpertiseException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional
    public CareCenterTypesWithCareCenters allCareCentersOfCareCenterType(Long careCenterTypeId) {
        try {

            CareCenterTypeEntity careCenterTypeEntity = careCenterTypeMySqlRepository.findById(careCenterTypeId).orElseThrow(NotFoundException::new);
            return careCenterEntityMapper.careCenterTypeEntityToModelWithCareCenters(careCenterTypeEntity);

        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateExpertiseException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public List<ProvinceCities> GetProvincesOrCities(Integer provinceId) {
        try {
            List<ProvinceCitiesEntity> provinceCitiesEntities = provinceCitiesMySqlRepository.findAllByParent(provinceId);
            return careCenterEntityMapper.provinceCitiesEntityToModel(provinceCitiesEntities);

        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateExpertiseException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

}
