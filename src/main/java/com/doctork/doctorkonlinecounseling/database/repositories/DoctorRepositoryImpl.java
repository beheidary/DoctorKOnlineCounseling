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
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.doctor.DoctorStatus;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class DoctorRepositoryImpl implements DoctorRepository {


    private final DoctorEntityMapper doctorEntityMapper;

    private final DoctorMySqlRepository doctorMySqlRepository;
    private final ExpertiseEntityMapper expertiseEntityMapper;

    private final ExpertiseMySqlRepository expertiseMySqlRepository;




    public DoctorRepositoryImpl(ExpertiseEntityMapper expertiseEntityMapper, ExpertiseMySqlRepository expertiseMySqlRepository, DoctorEntityMapper doctorEntityMapper, DoctorMySqlRepository doctorMySqlRepository) {
        this.doctorEntityMapper = doctorEntityMapper;
        this.expertiseEntityMapper = expertiseEntityMapper;
        this.expertiseMySqlRepository = expertiseMySqlRepository;
        this.doctorMySqlRepository = doctorMySqlRepository;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {

        // Todo add to elastic


        try{

            DoctorEntity doctorEntity = doctorEntityMapper.modelToEntity(doctor);

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
                doctorEntity.setMobileNumber(doctor.getMobileNumber());
                doctorEntity.setUpdateDateTime(doctor.getUpdateDateTime());

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

                        oldExpertise.setUpdateDateTime(newExpertise.getSaveDateTime());
                        oldExpertise.getDoctors().add(doctorEntity);
                        oldExpertise = expertiseMySqlRepository.save(oldExpertise);
                        doctorEntity.getExpertises().add(oldExpertise);
                        doctorEntity = doctorMySqlRepository.save(doctorEntity);
                        System.out.println(oldExpertise.getDoctors());
                        System.out.println(doctorEntity.getExpertises());
                        return expertiseEntityMapper.entityToModelWithDoctor(oldExpertise);

                    } else {

                        Set<DoctorEntity> doctors = new HashSet<>();
                        doctors.add(doctorEntity);
                        ExpertiseEntity expertise = new ExpertiseEntity();
                        expertise.setDoctors(doctors);

                        expertise.setName(newExpertise.getName());
                        expertise.setLatinName(newExpertise.getLatinName());
                        expertise.setSaveDateTime(newExpertise.getSaveDateTime());
                        expertise.setUpdateDateTime(newExpertise.getSaveDateTime());

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



