package com.doctork.doctorkonlinecounseling.api.adapters.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.PhysicianSocialMediaInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.PhysicianSocialMediaOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SicknessInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SicknessOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SocialMediaOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;

import java.util.Set;
import java.util.UUID;

public interface PhysicianDetailsAdapter {

    void addSickness (String sicknessName);
    Set<SicknessOutputDto> uploadSickness (UUID userId,Set<SicknessInputDto> sicknessInputDtos);

    Set<SicknessOutputDto> allSicknesses ();
    Set<SicknessOutputDto> allSicknessesWithState (State state);
    Set<SicknessOutputDto> allPhysicianSicknesses (UUID userId);

    void sicknessChangeState (Long sicknessId , State state);


    Set<PhysicianSocialMediaOutputDto> allPhysicianSocialMedias(UUID userId);

    Set<SocialMediaOutputDto> allSocialMedias();
    Set<PhysicianSocialMediaOutputDto> uploadSocialMedias (UUID userId, Set<PhysicianSocialMediaInputDto> physicianSocialMediaInputDtos);
    void addSocialMedia(String persianName , String latinName);




}
