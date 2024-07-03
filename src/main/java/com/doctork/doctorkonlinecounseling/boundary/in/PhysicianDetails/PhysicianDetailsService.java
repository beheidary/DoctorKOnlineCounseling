package com.doctork.doctorkonlinecounseling.boundary.in.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SicknessInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SicknessOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.PhysicianSocialMedia;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.SocialMedia;

import java.util.List;
import java.util.Set;

public interface PhysicianDetailsService {

    void addSickness (Sickness sickness);
    Set<Sickness> uploadSickness (String physicianId, Set<Sickness> sicknesses );
    Set<Sickness> allSicknesses ();
    Set<Sickness> allPhysicianSicknesses (String physicianId);


    void sicknessChangeState (Long sicknessId , State state);

    Set<PhysicianSocialMedia> allPhysicianSocialMedias(String physicianId);

    Set<SocialMedia> allSocialMedias();
    Set<PhysicianSocialMedia> uploadSocialMedias (String physicianId, Set<PhysicianSocialMedia> physicianSocialMedia);
    void addSocialMedia(SocialMedia socialMedia);





}
