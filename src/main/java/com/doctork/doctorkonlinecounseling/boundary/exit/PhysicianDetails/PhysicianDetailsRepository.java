package com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.PhysicianSocialMediaEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Education;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.PhysicianSocialMedia;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.SocialMedia;

import java.util.List;
import java.util.Set;

public interface PhysicianDetailsRepository {

    Sickness addSickness (Sickness sickness);
    Set<Sickness> uploadSickness (String physicianId, Set<Sickness> sickness);

    Set<Sickness> allSicknesses(State state);


    void sicknessChangeState (Long sicknessId , State state);

    Set<Sickness> allPhysicianSicknesses(String physicianId);
    Set<PhysicianSocialMedia> allPhysicianSocialMedias(String physicianId);

    Set<SocialMedia> allSocialMedias();
    Set<PhysicianSocialMedia> uploadSocialMedias (String physicianId, Set<PhysicianSocialMedia> physicianSocialMedia);

    SocialMedia addSocialMedia(SocialMedia socialMedia);

    void addEducation (String physicianId , Education education);

    Long deleteEducation ( String physicianId ,Long educationId);

    void editEducation ( String physicianId , Education education ,Long educationId );

    List<Education> allPhysicianEducations(String physicianId);



}
