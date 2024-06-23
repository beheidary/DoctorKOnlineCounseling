package com.doctork.doctorkonlinecounseling.boundary.in.Physician;

import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PhysicianService {

    Physician PhysicianCompleteProfile(Physician physician , Long nationalCode);

    Physician PhysicianEditProfile(Physician physician, Long nationalCode);

    List<Physician> topPhysician();

//    List<Physician> getPhysiciansForSync();

    Physician fetchPhysician(Long nationalCode);

    Physician fetchPhysician(UserEntity userEntity);

    Physician changeStatus (Long nationalCode , PhysicianStatus status);

    Physician changeState (Long nationalCode, State state);

}
