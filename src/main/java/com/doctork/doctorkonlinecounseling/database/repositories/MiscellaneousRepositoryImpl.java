package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.Miscellaneous.MiscellaneousRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.ArticleEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ArticleMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.ArticleEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Article;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MiscellaneousRepositoryImpl implements MiscellaneousRepository {

    ArticleMySqlRepository articleMySqlRepository;
    ArticleEntityMapper articleEntityMapper;

    public MiscellaneousRepositoryImpl(ArticleEntityMapper articleEntityMapper,ArticleMySqlRepository articleMySqlRepository) {
        this.articleMySqlRepository = articleMySqlRepository;
        this.articleEntityMapper = articleEntityMapper;
    }

    @Override
    public List<Article> getAllArticles() {



        try{

            List<ArticleEntity> articleEntities = articleMySqlRepository.findAll();

           return articleEntityMapper.entityToModel(articleEntities);


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
