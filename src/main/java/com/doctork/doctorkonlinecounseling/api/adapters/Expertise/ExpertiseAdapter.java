package com.doctork.doctorkonlinecounseling.api.adapters.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.RequestedExpertiseDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.ExpertiseInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.ExpertiseOutputDto;


import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ExpertiseAdapter {


    List<ExpertiseOutputDto> getExpertises ();

    ExpertiseOutputDto getExpertise (String latinName);
    ExpertiseOutputDto editExpertise(Long expertiseId , ExpertiseInputDto expertiseInputDto);


    List<TopExpertisesDto> findBestExpertisePhysicians();

    Set<ExpertiseOutputDto> uploadExpertise (UUID userId, Set<ExpertiseInputDto> expertise);
    Set<ExpertiseOutputDto> allPhysicianExpertises (UUID userId);

    void requestToAddExpertise (UUID userId , String name);

    List<RequestedExpertiseDto> waitingExpertises ();

    RequestedExpertiseDto expertiseChangeState (RequestedExpertiseDto requestedExpertise);





}
