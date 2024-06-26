package com.doctork.doctorkonlinecounseling.api.adapters.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SicknessInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SicknessOutputDto;

import java.util.Set;
import java.util.UUID;

public interface PhysicianDetailsAdapter {

    void crateSickness (String sicknessName);
    Set<SicknessOutputDto> uploadSickness (UUID userId,Set<SicknessInputDto> sicknessInputDtos);

    Set<SicknessOutputDto> allSicknesses ();
    Set<SicknessOutputDto> allPhysicianSicknesses (UUID userId);


}
