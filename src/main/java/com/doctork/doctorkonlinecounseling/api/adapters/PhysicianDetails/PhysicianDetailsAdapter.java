package com.doctork.doctorkonlinecounseling.api.adapters.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.EducationInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.PhysicianSocialMediaInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.EducationOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.PhysicianSocialMediaOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SicknessInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SicknessOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SocialMediaOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Education;

import java.util.List;
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

    void addEducation (UUID userId, EducationInputDto educationInputDto);

    Long deleteEducation ( UUID userId,Long educationId);

    void editEducation ( UUID userId, EducationInputDto educationInputDto ,Long educationId );

    List<EducationOutputDto> allPhysicianEducations(UUID userId);





}
