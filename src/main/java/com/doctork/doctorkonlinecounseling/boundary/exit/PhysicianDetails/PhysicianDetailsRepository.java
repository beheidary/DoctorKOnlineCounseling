package com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.PhysicianSocialMediaEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.PhysicianSocialMedia;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.SocialMedia;

import java.util.List;
import java.util.Set;

public interface PhysicianDetailsRepository {

    Sickness addSickness (Sickness sickness);
    Set<Sickness> uploadSickness (Long physicianId, Set<Sickness> sickness);

    Set<Sickness> allSicknesses();


    void sicknessChangeState (Long sicknessId , State state);

    Set<Sickness> allPhysicianSicknesses(Long physicianId);
    Set<PhysicianSocialMedia> allPhysicianSocialMedias(Long physicianId);

    Set<SocialMedia> allSocialMedias();
    Set<PhysicianSocialMedia> uploadSocialMedias (Long physicianId, Set<PhysicianSocialMedia> physicianSocialMedia);

    SocialMedia addSocialMedia(SocialMedia socialMedia);


}
