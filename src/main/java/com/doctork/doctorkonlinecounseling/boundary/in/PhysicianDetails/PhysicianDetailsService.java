package com.doctork.doctorkonlinecounseling.boundary.in.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SicknessInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SicknessOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;

import java.util.List;
import java.util.Set;

public interface PhysicianDetailsService {

    void crateSickness (Sickness sickness);
    Set<Sickness> uploadSickness (Long physicianId, Set<Sickness> sicknesses );
    Set<Sickness> allSicknesses ();
    Set<Sickness> allPhysicianSicknesses (Long physicianId);


    void sicknessChangeState (Long sicknessId , State state);



}
