package com.doctork.doctorkonlinecounseling.api.adapters.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;

import java.util.List;

public interface ExpertiseAdapter {

    ExpertiseOutputDTO addDoctorExpertise (String PSCode, ExpertiseInputDTO expertiseInputDTO);

    List<ExpertiseOutputDTO> getExpertises ();



}
