package com.doctork.doctorkonlinecounseling.api.adapters.Physician;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.PhysicianInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;

import java.util.List;

public interface PhysicianAdapter {


    PhysicianOutputDto physicianCompleteProfile(PhysicianInputDto physicianInputDto);

    PhysicianOutputDto physicianEditProfile(PhysicianInputDto physicianInputDto);

    List<PhysicianOutputDto> topPhysician();

    PhysicianOutputDto physicianCheckProfile(UserEntity userEntity);


    PhysicianOutputDto fetchPhysician(String nationalCode);
    PhysicianOutputDto fetchPhysician(UserEntity userEntity);


    PhysicianOutputDto changeStatus (String nationalCode , PhysicianStatus status);
    PhysicianOutputDto changeState (String nationalCode , State state);


}
