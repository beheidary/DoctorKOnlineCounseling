package com.doctork.doctorkonlinecounseling.api.adapters.Physician;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.PhysicianInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.ExpertiseInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.ExpertiseOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;

import java.util.List;

public interface PhysicianAdapter {


    PhysicianOutputDto physicianCompleteProfile(PhysicianInputDto physicianInputDto, Long nationalCode);

    PhysicianOutputDto physicianEditProfile(PhysicianInputDto physicianInputDto , Long nationalCode);

    List<PhysicianOutputDto> topPhysician();


    PhysicianOutputDto fetchPhysician(Long nationalCode);



    PhysicianOutputDto changeStatus (Long nationalCode , PhysicianStatus status);
    PhysicianOutputDto changeState (Long nationalCode , State state);






}
