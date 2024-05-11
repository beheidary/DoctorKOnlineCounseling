package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.Miscellaneous.MiscellaneousRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.DoctorNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PriceNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.ArticleEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.PriceEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.ServicesEntity;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ArticleMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.DoctorMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PriceMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ServicesMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.MiscellaneousEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Article;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Price;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Services;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MiscellaneousRepositoryImpl implements MiscellaneousRepository {

    ArticleMySqlRepository articleMySqlRepository;
    MiscellaneousEntityMapper miscellaneousEntityMapper;
    ServicesMySqlRepository servicesMySqlRepository;
    PriceMySqlRepository priceMySqlRepository;
    DoctorMySqlRepository doctorMySqlRepository;

    EntityManager entityManager;

    public MiscellaneousRepositoryImpl(EntityManager entityManager , DoctorMySqlRepository doctorMySqlRepository, PriceMySqlRepository priceMySqlRepository,ServicesMySqlRepository servicesMySqlRepository,MiscellaneousEntityMapper miscellaneousEntityMapper, ArticleMySqlRepository articleMySqlRepository) {
        this.articleMySqlRepository = articleMySqlRepository;
        this.entityManager = entityManager;
        this.doctorMySqlRepository = doctorMySqlRepository;
        this.priceMySqlRepository = priceMySqlRepository;
        this.servicesMySqlRepository = servicesMySqlRepository;
        this.miscellaneousEntityMapper = miscellaneousEntityMapper;
    }

    @Override
    public List<Article> getAllArticles() {



        try{

            List<ArticleEntity> articleEntities = articleMySqlRepository.findAll();

           return miscellaneousEntityMapper.entityToModel(articleEntities);


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
    public Services addServices(Services services) {



        try{


            ServicesEntity servicesEntity = miscellaneousEntityMapper.servicesModelToEntity(services);



            return miscellaneousEntityMapper.servicesEntityToModel(servicesMySqlRepository.save(servicesEntity));


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
    public Price addPrice(Price price, Long physicianId, Long servicesId) {




        try{
            DoctorEntity doctorEntity = doctorMySqlRepository.findDoctorEntityByNationalCode(physicianId);
            ServicesEntity servicesEntity = servicesMySqlRepository.findServicesEntityById(servicesId);

            if(doctorEntity != null){

                PriceEntity newPrice = miscellaneousEntityMapper.priceModelToEntity(price);
                newPrice.setDoctor(doctorEntity);
                newPrice.setService(servicesEntity);
                return  miscellaneousEntityMapper.priceEntityToModel(priceMySqlRepository.save(newPrice));

            }else {
                throw  new DoctorNotFoundException();
            }
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
    public Price editPrice(Long id , Price price) {

        try{

                PriceEntity priceEntity = priceMySqlRepository.findPriceEntityById(id);

                if ( priceEntity != null ){

                    price.setId(id);
                    priceEntity.setCost(price.getCost());
                    priceEntity.setTime(price.getTime());
                    return  miscellaneousEntityMapper.priceEntityToModel(priceMySqlRepository.save(priceEntity));

                }
                throw new PriceNotFoundException();

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
                    prices.add(miscellaneousEntityMapper.priceEntityToModel(priceEntity));
                return prices;
            }
            throw new DoctorNotFoundException();


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
    public Long deletePrice(Long priceId) {
        try {

           PriceEntity priceEntity = priceMySqlRepository.findPriceEntityById(priceId);

           if(priceEntity != null) {
               priceMySqlRepository.delete(priceEntity);
               return priceId;
           }
           throw new PriceNotFoundException();

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
}
