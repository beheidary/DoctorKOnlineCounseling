package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.Price.PriceRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.Duplicate.DuplicateActivePriceException;
import com.doctork.doctorkonlinecounseling.common.exceptions.Duplicate.DuplicateExpertiseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PhysicianNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PriceNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.CareCenter.CareCenterEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Price.PriceEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Price.ServicesEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PhysicianMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PriceMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ServicesMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.PriceEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PriceRepositoryImpl implements PriceRepository {


    private final ServicesMySqlRepository servicesMySqlRepository;

    private final PriceMySqlRepository priceMySqlRepository;

    private final PhysicianMySqlRepository physicianMySqlRepository;

    private final PriceEntityMapper priceEntityMapper;

    private final EntityManager entityManager;


    public PriceRepositoryImpl(EntityManager entityManager, ServicesMySqlRepository servicesMySqlRepository, PriceMySqlRepository priceMySqlRepository, PhysicianMySqlRepository physicianMySqlRepository, PriceEntityMapper priceEntityMapper) {
        this.servicesMySqlRepository = servicesMySqlRepository;
        this.entityManager = entityManager;
        this.priceMySqlRepository = priceMySqlRepository;
        this.physicianMySqlRepository = physicianMySqlRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    @Override
    public Services addServices(Services services) {



        try{


            ServicesEntity servicesEntity = priceEntityMapper.servicesModelToEntity(services);



            return priceEntityMapper.servicesEntityToModel(servicesMySqlRepository.save(servicesEntity));


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
    public Price addPrice(Price price, String physicianId, Long servicesId) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findPhysicianEntityByNationalCode(physicianId);
            ServicesEntity servicesEntity = servicesMySqlRepository.findServicesEntityById(servicesId);

            if (physicianEntity != null && servicesEntity != null) {
                Optional<PriceEntity> existingActivePrice = priceMySqlRepository.findByServiceIdAndTimeAndPriceStatus(
                        servicesEntity.getId(), price.getTime(), Status.Active);

                if (existingActivePrice.isPresent()) {
                    throw new DuplicateActivePriceException(43,"An active price with the same time already exists for this service.",HttpStatus.BAD_REQUEST);
                }
                if (!isValidTime(price.getTime(), servicesEntity.getTimeSlot(), servicesEntity.getSlotCount())) {
                    throw new InvalidDataException();
                }

                PriceEntity newPrice = priceEntityMapper.priceModelToEntity(price);
                newPrice.setPhysician(physicianEntity);
                newPrice.setService(servicesEntity);
                return priceEntityMapper.priceEntityToModel(priceMySqlRepository.save(newPrice));
            } else {
                throw new PhysicianNotFoundException();
            }
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex) {
            if (ex instanceof BaseException)
                throw ex;
            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    private boolean isValidTime(Long time, Integer timeSlot, Integer slotCount) {
        for (int i = 1; i <= slotCount; i++) {
            if (time == (long) i * timeSlot) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Price DeActivePrice(String physicianId, Long priceId) {
        try{

            PriceEntity priceEntity = priceMySqlRepository.findById(priceId).orElseThrow(PriceNotFoundException::new);

            if (!priceEntity.getPhysician().getNationalCode().equals(physicianId))
                throw new AccessDeniedException("You do not have the required access");
            if (priceEntity.getPriceStatus() == Status.Active){
                priceEntity.setPriceStatus(Status.DeActive);
                return  priceEntityMapper.priceEntityToModel(priceMySqlRepository.save(priceEntity));
            }else
                throw new InvalidDataException();
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
    public Price priceAcceptanceDecision(Long priceId, State state) {
        try {
            PriceEntity priceEntity = priceMySqlRepository.findById(priceId).orElseThrow(PriceNotFoundException::new);
            if (priceEntity.getState() == State.Waiting && (state == State.Approved || state == State.Rejected)) {
                priceEntity.setState(state);
                if(state == State.Rejected)
                    priceEntity.setPriceStatus(Status.DeActive);
                return priceEntityMapper.priceEntityToModel(priceMySqlRepository.save(priceEntity));
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
    public List<Services> AllActiveServices() {
        try {

            return priceEntityMapper.servicesEntityToModel(servicesMySqlRepository.findAllByStatus(Status.Active));

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
    public List<Price> readPrices(String physicianId) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<PriceEntity> cq = cb.createQuery(PriceEntity.class);
            Root<PriceEntity> root = cq.from(PriceEntity.class);

            Predicate statusCondition = cb.equal(root.get("priceStatus"), Status.Active);
            Predicate stateCondition = root.get("state").in(State.Waiting, State.Approved);

            Predicate combinedCondition = cb.and(statusCondition, stateCondition);

            Predicate physicianCondition = cb.equal(root.get("physician").get("id"), physicianId);

            cq.select(root).where(cb.and(combinedCondition, physicianCondition));

            Order approvedFirst = cb.desc(cb.equal(root.get("state"), State.Approved));
            Order waitingNext = cb.desc(cb.equal(root.get("state"), State.Waiting));
            cq.orderBy(approvedFirst, waitingNext);

            List<PriceEntity> priceEntities = entityManager.createQuery(cq).getResultList();

            if (!priceEntities.isEmpty()) {
                return priceEntityMapper.priceEntityToModel(priceEntities);
            }
            return new ArrayList<>();

        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex) {
            if (ex instanceof BaseException)
                throw ex;
            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override

    public Price findPriceById(Long priceId) {
        try {

            PriceEntity priceEntity = priceMySqlRepository.findById(priceId).orElseThrow(PriceNotFoundException::new);
            return priceEntityMapper.priceEntityToModel(priceEntity);



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
