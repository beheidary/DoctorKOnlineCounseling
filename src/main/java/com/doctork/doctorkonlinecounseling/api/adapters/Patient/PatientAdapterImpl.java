package com.doctork.doctorkonlinecounseling.api.adapters.Patient;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.PatientInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.user.PatientOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.PatientMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.patient.PatientService;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;
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
}
