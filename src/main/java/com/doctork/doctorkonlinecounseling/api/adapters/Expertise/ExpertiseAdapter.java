package com.doctork.doctorkonlinecounseling.api.adapters.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.ExpertiseInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.ExpertiseOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

import java.util.List;

public interface ExpertiseAdapter {


    List<ExpertiseOutputDto> getExpertises ();

    ExpertiseOutputDto getExpertise (ExpertiseLatinNames lotinName);

    ExpertiseOutputDto addPhysicianExpertise (Long nationalCode, ExpertiseInputDto expertiseInputDTO);


    List<TopExpertisesDto> findBestExpertisePhysicians();




}
