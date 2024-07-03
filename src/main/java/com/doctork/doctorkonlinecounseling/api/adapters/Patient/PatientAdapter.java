package com.doctork.doctorkonlinecounseling.api.adapters.Patient;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.PatientInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.user.PatientOutputDto;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;

public interface PatientAdapter {

    PatientOutputDto patientCompleteProfile(PatientInputDto patientInputDto);

    PatientOutputDto fetchPatient (String nationalCode);

    PatientOutputDto patientCheckProfile (UserEntity userEntity);


}
