package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.Price.PriceRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PhysicianNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PriceNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Price.PriceEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Price.ServicesEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PhysicianMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PriceMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ServicesMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.PriceEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
    public Price addPrice(Price price, Long physicianId, Long servicesId) {




        try{
            PhysicianEntity physicianEntity = physicianMySqlRepository.findPhysicianEntityByNationalCode(physicianId);
            ServicesEntity servicesEntity = servicesMySqlRepository.findServicesEntityById(servicesId);

            if(physicianEntity != null){

                PriceEntity newPrice = priceEntityMapper.priceModelToEntity(price);
                newPrice.setDoctor(physicianEntity);
                newPrice.setService(servicesEntity);
                return  priceEntityMapper.priceEntityToModel(priceMySqlRepository.save(newPrice));

            }else {
                throw  new PhysicianNotFoundException();
            }
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
    public Price editPrice(Long id , Price price) {

        try{

            PriceEntity priceEntity = priceMySqlRepository.findById(id).orElseThrow(PriceNotFoundException::new);



                Long tokenNationalCode =((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNationalCode();
                if (!priceEntity.getDoctor().getNationalCode().equals(tokenNationalCode))
                    throw new AccessDeniedException("You do not have the required access");
                priceEntity.setCost(price.getCost());
                priceEntity.setState(price.getState());
                priceEntity.setStatus(price.getStatus());
                priceEntity.setTime(price.getTime());
                return  priceEntityMapper.priceEntityToModel(priceMySqlRepository.save(priceEntity));


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
    public List<Price> readPrices(Long physicianId) {

        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PriceEntity> criteriaQuery = criteriaBuilder.createQuery(PriceEntity.class);
            Root<PriceEntity> root = criteriaQuery.from(PriceEntity.class);

            criteriaQuery.select(root)
                    .where(criteriaBuilder.equal(root.get("doctor").get("id"), physicianId));
            List<PriceEntity> priceEntities = entityManager.createQuery(criteriaQuery).getResultList();
            if (priceEntities.size() > 0 ){

                List<Price> prices = new ArrayList<>();
                for (PriceEntity priceEntity : priceEntities)
                    prices.add(priceEntityMapper.priceEntityToModel(priceEntity));
                return prices;
                // Todo physician Entity just moved to model
            }
            throw new PhysicianNotFoundException();


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

    @Override
    public Long deletePrice(Long priceId) {
        try {

            PriceEntity priceEntity = priceMySqlRepository.findById(priceId).orElseThrow(PriceNotFoundException::new);

            Long tokenNationalCode =((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNationalCode();
            if (!priceEntity.getDoctor().getNationalCode().equals(tokenNationalCode))
                throw new AccessDeniedException("You do not have the required access");
            priceMySqlRepository.delete(priceEntity);
            return priceId;


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
