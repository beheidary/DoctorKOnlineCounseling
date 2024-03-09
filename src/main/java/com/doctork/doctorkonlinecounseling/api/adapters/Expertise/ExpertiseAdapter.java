package com.doctork.doctorkonlinecounseling.api.adapters.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;

public interface ExpertiseAdapter {

    ExpertiseOutputDTO addDoctorExpertise (String PSCode, ExpertiseInputDTO expertiseInputDTO);


}
