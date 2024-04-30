package com.doctork.doctorkonlinecounseling.api.adapters.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

import java.util.List;

public interface ExpertiseAdapter {


    List<ExpertiseOutputDTO> getExpertises ();

    ExpertiseOutputDTO getExpertise (ExpertiseLatinNames lotinName);



}
