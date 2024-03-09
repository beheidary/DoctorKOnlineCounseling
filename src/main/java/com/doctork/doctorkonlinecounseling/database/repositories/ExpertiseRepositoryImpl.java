package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.expertise.ExpertiseRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ExpertiseMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.doctor.ExpertiseEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import com.doctork.doctorkonlinecounseling.domain.doctor.ExpertiseLatinNames;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExpertiseRepositoryImpl implements ExpertiseRepository {


    ExpertiseMySqlRepository expertiseMySqlRepository;

    ExpertiseEntityMapper expertiseEntityMapper;


    public ExpertiseRepositoryImpl(ExpertiseMySqlRepository expertiseMySqlRepository, ExpertiseEntityMapper expertiseEntityMapper) {
        this.expertiseMySqlRepository = expertiseMySqlRepository;
        this.expertiseEntityMapper = expertiseEntityMapper;
    }


    @Override
    public Expertise getExpertise(ExpertiseLatinNames LatinName) {


        try{

            return expertiseEntityMapper.entityToModel(expertiseMySqlRepository.findExpertiseEntitiesByLatinName(LatinName));

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
