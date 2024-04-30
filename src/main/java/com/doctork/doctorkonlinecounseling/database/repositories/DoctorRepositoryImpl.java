package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.doctor.DoctorRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.DoctorNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.DoctorMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ExpertiseMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.doctor.DoctorEntityMapper;
import com.doctork.doctorkonlinecounseling.database.mappers.doctor.ExpertiseEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.doctor.DoctorStatus;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class DoctorRepositoryImpl implements DoctorRepository {


    private final DoctorEntityMapper doctorEntityMapper;

    private final DoctorMySqlRepository doctorMySqlRepository;
    private final ExpertiseEntityMapper expertiseEntityMapper;
    private final EntityManager em;
    private final ExpertiseMySqlRepository expertiseMySqlRepository;




    public DoctorRepositoryImpl(EntityManager em, ExpertiseEntityMapper expertiseEntityMapper, ExpertiseMySqlRepository expertiseMySqlRepository, DoctorEntityMapper doctorEntityMapper, DoctorMySqlRepository doctorMySqlRepository) {
        this.doctorEntityMapper = doctorEntityMapper;
        this.expertiseEntityMapper = expertiseEntityMapper;
        this.expertiseMySqlRepository = expertiseMySqlRepository;
        this.doctorMySqlRepository = doctorMySqlRepository;
        this.em = em;
    }


    @Override
    public List<TopExpertises> findBestDoctorByExpertise() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ExpertiseEntity> cq = cb.createQuery(ExpertiseEntity.class);
        Root<ExpertiseEntity> expertiseEntityRoot = cq.from(ExpertiseEntity.class);
        TypedQuery<ExpertiseEntity> query = em.createQuery(cq);
        List<ExpertiseEntity> expertiseEntities = query.getResultList();

        List<TopExpertises> expertise = new ArrayList<>();
        for(ExpertiseEntity expertiseEntity: expertiseEntities){
            List<Doctor> doctors = new ArrayList<>();
            for (DoctorEntity doctorEntity : expertiseEntity.getDoctors()){
                doctors.add(doctorEntityMapper.entityToModel(doctorEntity));
            }
            TopExpertises expertise1 = expertiseEntityMapper.topEntityToModel(expertiseEntity);
            doctors.sort(Comparator.comparingDouble(Doctor::getBusinessWeight).reversed());
            expertise1.setDoctors(doctors);
            expertise.add(expertise1);
        }

        return expertise;
    }

    @Override
    public List<Doctor> topDoctors() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DoctorEntity> cq = cb.createQuery(DoctorEntity.class);
        Root<DoctorEntity> doctorEntityRoot = cq.from(DoctorEntity.class);
        TypedQuery<DoctorEntity> query = em.createQuery(cq);

//
//        Subquery<Double> maxBusinessWeightSubquery = cq.subquery(Double.class);
//        Root<DoctorEntity> subqueryRoot = maxBusinessWeightSubquery.from(DoctorEntity.class);
//        maxBusinessWeightSubquery.select(cb.max(subqueryRoot.get("businessWeight")));
//        cq.where(cb.equal(doctorEntityRoot.get("businessWeight"), maxBusinessWeightSubquery));
//        query.setMaxResults(3);

        List<DoctorEntity> doctorEntities = query.getResultList();
        doctorEntities.sort(Comparator.comparingDouble(DoctorEntity::getBusinessWeight).reversed());
        doctorEntities = doctorEntities.stream().limit(3).collect(Collectors.toList());
        List<Doctor> doctors = new ArrayList<>();
        for (DoctorEntity doctorEntity : doctorEntities){
            doctors.add(doctorEntityMapper.entityToModel(doctorEntity));
        }


        return doctors;

    }

    @Override
    public Doctor addDoctor(Doctor doctor) {

        // Todo add to elastic


        try{


            DoctorEntity doctorEntity = doctorEntityMapper.modelToEntity(doctor);
            if (doctorEntity.getBusinessWeight() == null)
                doctorEntity.setBusinessWeight(0.9);
            if(doctorEntity.getStatus() == null)
                doctorEntity.setStatus(DoctorStatus.Offline);

            doctorEntity = doctorMySqlRepository.save(doctorEntity);

            return doctorEntityMapper.entityToModel(doctorEntity);

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
    public Doctor editDoctor(Doctor doctor) {

        try{

            DoctorEntity doctorEntity = doctorMySqlRepository.findDoctorEntityByPhysicianSystemCode(doctor.getPhysicianSystemCode());

            if (doctorEntity == null){

                throw new DoctorNotFoundException();

            }else{

                doctorEntity.setDateOfBirth(doctor.getDateOfBirth());
                doctorEntity.setEducationLevel(doctor.getEducationLevel());

                doctorEntity = doctorMySqlRepository.save(doctorEntity);

                return doctorEntityMapper.entityToModel(doctorEntity);

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
    public Doctor fetchDoctor(String PSCode) {


        try{

           DoctorEntity doctorEntity = doctorMySqlRepository.findDoctorEntityByPhysicianSystemCode(PSCode);

           if (doctorEntity == null){

               throw new DoctorNotFoundException();

           }else{

               return doctorEntityMapper.entityToModelWithExpertise(doctorEntity);

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
    public Expertise addDoctorExpertise(String PSCode, Expertise newExpertise) {

        try{

            DoctorEntity doctorEntity = doctorMySqlRepository.findDoctorEntityByPhysicianSystemCode(PSCode);

            if(doctorEntity !=null){

                try {

                    ExpertiseEntity oldExpertise = expertiseMySqlRepository.findExpertiseEntitiesByLatinName(newExpertise.getLatinName());


                    if (oldExpertise != null) {

                        oldExpertise.getDoctors().add(doctorEntity);
                        oldExpertise = expertiseMySqlRepository.save(oldExpertise);
                        doctorEntity.getExpertises().add(oldExpertise);
                        doctorEntity = doctorMySqlRepository.save(doctorEntity);
                        return expertiseEntityMapper.entityToModelWithDoctor(oldExpertise);

                    } else {
                        // Todo there is bug doctors_expertise relation not set in first execute
                        Set<DoctorEntity> doctors = new HashSet<>();
                        doctors.add(doctorEntity);
                        ExpertiseEntity expertise = new ExpertiseEntity();
                        expertise.setDoctors(doctors);
                        expertise.setName(newExpertise.getName());
                        expertise.setLatinName(newExpertise.getLatinName());
                        return expertiseEntityMapper.entityToModelWithDoctor(expertiseMySqlRepository.save(expertise));


                    }
                }catch (BaseException ex){
                    throw ex;
                }

            }else{

                throw new DoctorNotFoundException();

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
    public Doctor changeStatus(String PSCode, DoctorStatus status) {


        try{

            DoctorEntity doctorEntity = doctorMySqlRepository.findDoctorEntityByPhysicianSystemCode(PSCode);

            if (doctorEntity == null){

                throw new DoctorNotFoundException();

            }else{

                doctorEntity.setStatus(status);
                doctorEntity = doctorMySqlRepository.save(doctorEntity);
                return doctorEntityMapper.entityToModel(doctorEntity);

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


}



