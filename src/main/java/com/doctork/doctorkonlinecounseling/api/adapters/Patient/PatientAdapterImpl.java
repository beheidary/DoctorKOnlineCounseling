package com.doctork.doctorkonlinecounseling.api.adapters.Patient;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.PatientInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.user.PatientOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.PatientMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.patient.PatientService;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.stereotype.Component;

@Component
public class PatientAdapterImpl implements PatientAdapter {

    PatientService patientService;
    PatientMapper patientMapper;

    public PatientAdapterImpl(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientOutputDto patientCompleteProfile(PatientInputDto patientInputDto) {

        Patient patient = patientMapper.inputToModel(patientInputDto);
        patient = patientService.patientCompleteProfile(patient);
        return patientMapper.modelToOutput(patient);
    }

    @Override
    public PatientOutputDto fetchPatient(Long nationalCode) {
        return patientMapper.modelToOutput(patientService.fetchPatient(nationalCode));
    }

    @Override
    public PatientOutputDto patientCheckProfile(UserEntity userEntity) {

        try {
            Patient patient = patientService.fetchPatient(userEntity);
            PatientOutputDto patientOutputDto = patientMapper.modelToOutput(patient);
            if (patientOutputDto.getNationalCode() == null || patientOutputDto.getFirstName() == null ||
                    patientOutputDto.getLastName() == null)
                patientOutputDto.setProfileNecessaryInfoInserted(Boolean.FALSE);
            return patientOutputDto;

        }
        catch (NullPointerException ex) //get error not find physician
        {
            Patient patient = new Patient();
            PatientOutputDto patientOutputDto = patientMapper.modelToOutput(patient);
            patientOutputDto.setProfileNecessaryInfoInserted(Boolean.FALSE);
            return patientOutputDto;
        }





    }
}
