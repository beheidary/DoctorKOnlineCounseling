package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.doctor.DoctorRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.DoctorSqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.doctor.DoctorEntityMapper;
import com.doctork.doctorkonlinecounseling.database.mongoRepositories.DoctorMongoRepository;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DoctorRepositoryImpl implements DoctorRepository {


    private final DoctorEntityMapper doctorEntityMapper;

    private final DoctorSqlRepository doctorSqlRepository;

    DoctorMongoRepository doctorMongoRepository;

    public DoctorRepositoryImpl(DoctorEntityMapper doctorEntityMapper, DoctorSqlRepository doctorSqlRepository,DoctorMongoRepository doctorMongoRepository) {
        this.doctorEntityMapper = doctorEntityMapper;
        this.doctorMongoRepository = doctorMongoRepository;
        this.doctorSqlRepository = doctorSqlRepository;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {


        try{

            DoctorEntity doctorEntity = doctorEntityMapper.modelToEntity(doctor);

            doctorEntity = doctorSqlRepository.save(doctorEntity);

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
    public List<Doctor> getDoctorsForSync() {

//        try {


//            DoctorEntity doctorEntity = doctorEntityMapper.modelToEntity(doctor);
//
//            doctorEntity = doctorSqlRepository.save(doctorEntity);
//
//            return doctorEntityMapper.entityToModel(doctorEntity);
//
//        }catch (QueryTimeoutException ex){
//
//            throw new DatabaseTimeOutException();
//
//        }
//        catch (DataIntegrityViolationException ex){
//
//            throw new InvalidDataException();
//
//        }
//        catch (Exception ex){
//
//            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
//
//        }

//        }

        return null;
    }
}
