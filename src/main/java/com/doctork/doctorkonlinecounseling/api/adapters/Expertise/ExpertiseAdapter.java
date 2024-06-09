package com.doctork.doctorkonlinecounseling.api.adapters.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.ExpertiseInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.ExpertiseOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;

import java.util.List;

public interface ExpertiseAdapter {


    List<ExpertiseOutputDto> getExpertises ();

    ExpertiseOutputDto getExpertise (ExpertiseLatinNames lotinName);

    ExpertiseOutputDto addExpertise(ExpertiseInputDto expertiseInputDto);
    ExpertiseOutputDto editExpertise(Long expertiseId , ExpertiseInputDto expertiseInputDto);

    ExpertiseOutputDto addPhysicianExpertise (Long nationalCode, ExpertiseInputDto expertiseInputDto);


    List<TopExpertisesDto> findBestExpertisePhysicians();




}
