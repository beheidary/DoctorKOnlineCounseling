package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.patient.PatientRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PatientNotfoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Patient.PatientEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PatientMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.PatientEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PatientRepositoryImpl implements PatientRepository {


    private final PatientEntityMapper patientEntityMapper;
    private final PatientMySqlRepository patientMySqlRepository;


    public PatientRepositoryImpl(PatientEntityMapper patientEntityMapper, PatientMySqlRepository patientMySqlRepository) {
        this.patientEntityMapper = patientEntityMapper;
        this.patientMySqlRepository = patientMySqlRepository;
    }
    @Override
    public Patient patientCompleteProfile(Patient patient) {


        try{


            UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (patientMySqlRepository.findPatientEntityByNationalCode(patient.getNationalCode())== null && patientMySqlRepository.findPatientEntityByUser(userEntity) == null ){

                if(Objects.equals(userEntity.getRole().toString(), "Patient")){
                    PatientEntity patientEntity = patientEntityMapper.modelToEntity(patient);
                    patientEntity.setUser(userEntity);

                    patientEntity = patientMySqlRepository.save(patientEntity);

                    return patientEntityMapper.entityToModel(patientEntity);
                }
                else {
                    throw new PatientNotfoundException();
                }
            }else {
                throw new InvalidDataException();

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
