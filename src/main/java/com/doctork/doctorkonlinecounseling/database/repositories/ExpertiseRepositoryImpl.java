package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.expertise.ExpertiseRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.ExpertiseNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PhysicianNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Expertise.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ExpertiseMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PhysicianMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.ExpertiseEntityMapper;
import com.doctork.doctorkonlinecounseling.database.mappers.PhysicianEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ExpertiseRepositoryImpl implements ExpertiseRepository {


    private final ExpertiseMySqlRepository expertiseMySqlRepository;

    private final ExpertiseEntityMapper expertiseEntityMapper;
    private final PhysicianEntityMapper physicianEntityMapper;
    private final PhysicianMySqlRepository physicianMySqlRepository;
    private final EntityManager entityManager;


    public ExpertiseRepositoryImpl(PhysicianMySqlRepository physicianMySqlRepository, PhysicianEntityMapper physicianEntityMapper , EntityManager entityManager, ExpertiseMySqlRepository expertiseMySqlRepository, ExpertiseEntityMapper expertiseEntityMapper) {
        this.expertiseMySqlRepository = expertiseMySqlRepository;
        this.physicianMySqlRepository = physicianMySqlRepository;
        this.physicianEntityMapper = physicianEntityMapper;
        this.entityManager =entityManager;
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
    public Expertise addPhysicianExpertise(Long nationalCode, Expertise newExpertise) {

        try{

            PhysicianEntity physicianEntity = physicianMySqlRepository.findPhysicianEntityByNationalCode(nationalCode);

            if(physicianEntity !=null){

                try {

                    ExpertiseEntity oldExpertise = expertiseMySqlRepository.findExpertiseEntitiesByLatinName(newExpertise.getLatinName());


                    if (oldExpertise != null) {

                        oldExpertise.getPhysicians().add(physicianEntity);
                        oldExpertise = expertiseMySqlRepository.save(oldExpertise);
                        physicianEntity.getExpertises().add(oldExpertise);
                        physicianEntity.setState(State.Waiting);
                        physicianEntity = physicianMySqlRepository.save(physicianEntity);
                        return expertiseEntityMapper.entityToModelWithDoctor(oldExpertise);

                    } else {
                        Set<PhysicianEntity> physicians = new HashSet<>();
                        physicians.add(physicianEntity);
                        ExpertiseEntity expertise = new ExpertiseEntity();
                        expertise.setPhysicians(physicians);
                        expertise.setName(newExpertise.getName());
                        expertise.setLatinName(newExpertise.getLatinName());
                        expertise = expertiseMySqlRepository.save(expertise);
                        physicianEntity.getExpertises().add(expertise);
                        physicianEntity.setState(State.Waiting);
                        physicianEntity = physicianMySqlRepository.save(physicianEntity);
                        return expertiseEntityMapper.entityToModelWithDoctor(expertise);


                    }
                }catch (BaseException ex){
                    throw ex;
                }

            }else{

                throw new PhysicianNotFoundException();

            }

        }


        catch (BaseException ex){

            throw ex;

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


    @Override
    public List<TopExpertises> findBestExpertisePhysicians() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExpertiseEntity> cq = cb.createQuery(ExpertiseEntity.class);
        Root<ExpertiseEntity> expertiseEntityRoot = cq.from(ExpertiseEntity.class);
        TypedQuery<ExpertiseEntity> query = entityManager.createQuery(cq);
        List<ExpertiseEntity> expertiseEntities = query.getResultList();

        List<TopExpertises> expertise = new ArrayList<>();
        for(ExpertiseEntity expertiseEntity: expertiseEntities){
            List<com.doctork.doctorkonlinecounseling.domain.physician.Physician> physicians = new ArrayList<>();
            for (PhysicianEntity physicianEntity : expertiseEntity.getPhysicians()){
                physicians.add(physicianEntityMapper.entityToModel(physicianEntity));
            }
            TopExpertises expertise1 = expertiseEntityMapper.topEntityToModel(expertiseEntity);
            physicians.sort(Comparator.comparingDouble(com.doctork.doctorkonlinecounseling.domain.physician.Physician::getBusinessWeight).reversed());
            expertise1.setPhysicians(physicians);
            expertise.add(expertise1);
        }

        return expertise;
    }

}
