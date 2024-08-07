package com.doctork.doctorkonlinecounseling.boundary.in.Physician;

import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PhysicianService {

    Physician PhysicianCompleteProfile(Physician physician);

    Physician PhysicianEditProfile(Physician physician);

    List<Physician> topPhysician();

//    List<Physician> getPhysiciansForSync();

    Physician fetchPhysician(String nationalCode);

    Physician fetchPhysician(UserEntity userEntity);

    Physician changeStatus (String nationalCode , PhysicianStatus status);

    Physician changeState (String nationalCode, State state);

}
