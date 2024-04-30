package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.expertise.ExpertiseRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.ExpertiseNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ExpertiseMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.doctor.ExpertiseEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

            ExpertiseEntity expertiseEntity = expertiseMySqlRepository.findExpertiseEntitiesByLatinName(LatinName);


            if (expertiseEntity == null){

                throw new ExpertiseNotFoundException();

            }else{

                return expertiseEntityMapper.entityToModelWithDoctor(expertiseEntity);

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
    public List<Expertise> getExpertises() {

        try {


            List<ExpertiseEntity> expertiseEntities = expertiseMySqlRepository.findAll();

            return expertiseEntities.stream().map(expertiseEntityMapper::entityToModelWithDoctor).collect(Collectors.toList());

        } catch (QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex) {

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}
