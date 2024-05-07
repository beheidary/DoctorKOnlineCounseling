package com.doctork.doctorkonlinecounseling.api.adapters.Patient;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.PatientInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.user.PatientOutputDto;

public interface PatientAdapter {

    PatientOutputDto patientCompleteProfile(PatientInputDto patientInputDto);

}
