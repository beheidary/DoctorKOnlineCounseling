package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.Miscellaneous.MiscellaneousRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.ArticleEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.OtpDetailsEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ArticleMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.MiscellaneousEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MiscellaneousRepositoryImpl implements MiscellaneousRepository {

    ArticleMySqlRepository articleMySqlRepository;
    MiscellaneousEntityMapper miscellaneousEntityMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public MiscellaneousRepositoryImpl(MiscellaneousEntityMapper miscellaneousEntityMapper, ArticleMySqlRepository articleMySqlRepository) {
        this.articleMySqlRepository = articleMySqlRepository;
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
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
            
        }

    }

    @Override
    public Optional<OtpDetailsEntity> findLatestByMobileNumber(String phoneNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OtpDetailsEntity> query = cb.createQuery(OtpDetailsEntity.class);
        Root<OtpDetailsEntity> root = query.from(OtpDetailsEntity.class);

        Predicate mobileNumberPredicate = cb.equal(root.get("phoneNumber"), phoneNumber);
        query.where(mobileNumberPredicate);
        query.orderBy(cb.desc(root.get("createTime")));

        return entityManager.createQuery(query)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }

}
