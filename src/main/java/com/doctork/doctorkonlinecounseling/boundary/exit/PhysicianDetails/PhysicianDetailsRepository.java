package com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;

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

    Long deleteEducation (Physician physician, Long educationId);

    void editEducation ( Physician physician , Education education ,Long educationId );

    List<Education> allPhysicianEducations(String physicianId);

    void addExperiences(String physicianId, Experiences experiences);

    Long deleteExperiences(Physician physician, Long experiencesId);

    void editExperiences(Physician physician, Experiences experiences, Long experiencesId);

    List<Experiences> allPhysicianExperiences(String physicianId);


    void addMembership(String physicianId, Membership membership);

    Long deleteMembership(Physician physician, Long membershipId);

    void editMembership(Physician physician, Membership membership, Long membershipId);

    List<Membership> allPhysicianMemberships(String physicianId);



}
