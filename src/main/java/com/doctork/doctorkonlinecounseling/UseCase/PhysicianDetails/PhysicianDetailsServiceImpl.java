package com.doctork.doctorkonlinecounseling.UseCase.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails.PhysicianDetailsRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.PhysicianDetails.PhysicianDetailsService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PhysicianDetailsServiceImpl implements PhysicianDetailsService {

    private final PhysicianDetailsRepository physicianDetailsRepository;

    public PhysicianDetailsServiceImpl(PhysicianDetailsRepository physicianDetailsRepository) {
        this.physicianDetailsRepository = physicianDetailsRepository;
    }

    @Override
    public void addSickness(Sickness sickness) {
        physicianDetailsRepository.addSickness(sickness);
    }

    @Override
    public Set<Sickness> uploadSickness(String physicianId, Set<Sickness> sicknesses) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.uploadSickness(physicianId,sicknesses);
    }

    @Override
    public Set<Sickness> allSicknesses(State state) {

        return physicianDetailsRepository.allSicknesses(state);
    }

    @Override
    public Set<Sickness> allPhysicianSicknesses(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianSicknesses(physicianId);
    }

    @Override
    public void sicknessChangeState(Long sicknessId, State state) {
        if (sicknessId == null)
            throw new IdInputException();

        physicianDetailsRepository.sicknessChangeState(sicknessId,state);
    }

    @Override
    public Set<PhysicianSocialMedia> allPhysicianSocialMedias(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianSocialMedias(physicianId);
    }

    @Override
    public Set<SocialMedia> allSocialMedias() {

        return physicianDetailsRepository.allSocialMedias();
    }

    @Override
    public Set<PhysicianSocialMedia> uploadSocialMedias(String physicianId, Set<PhysicianSocialMedia> physicianSocialMedia) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.uploadSocialMedias(physicianId,physicianSocialMedia);
    }

    @Override
    public void addSocialMedia(SocialMedia socialMedia) {
        physicianDetailsRepository.addSocialMedia(socialMedia);
    }

    @Override
    public void addEducation(String physicianId, Education education) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.addEducation(physicianId,education);

    }

    @Override
    public Long deleteEducation(Physician physician, Long educationId) {
        return physicianDetailsRepository.deleteEducation(physician,educationId);
    }

    @Override
    public void editEducation(Physician physician, Education education, Long educationId) {
        physicianDetailsRepository.editEducation(physician,education,educationId);

    }

    @Override
    public List<Education> allPhysicianEducations(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianEducations(physicianId);
    }


    @Override
    public void addExperiences(String physicianId, Experiences experiences) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.addExperiences(physicianId, experiences);
    }

    @Override
    public Long deleteExperiences(Physician physician, Long experiencesId) {
        return physicianDetailsRepository.deleteExperiences(physician, experiencesId);
    }

    @Override
    public void editExperiences(Physician physician, Experiences experiences, Long experiencesId) {
        physicianDetailsRepository.editExperiences(physician, experiences, experiencesId);
    }

    @Override
    public List<Experiences> allPhysicianExperiences(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianExperiences(physicianId);
    }

    @Override
    public void addMembership(String physicianId, Membership membership) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.addMembership(physicianId, membership);
    }

    @Override
    public Long deleteMembership(Physician physician, Long membershipId) {
        return physicianDetailsRepository.deleteMembership(physician, membershipId);
    }

    @Override
    public void editMembership(Physician physician, Membership membership, Long membershipId) {
        physicianDetailsRepository.editMembership(physician, membership, membershipId);
    }

    @Override
    public List<Membership> allPhysicianMemberships(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianMemberships(physicianId);
    }

    @Override
    public void addAwardOrHonor(String physicianId, AwardsAndHonors awardsAndHonors) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.addAwardOrHonor(physicianId, awardsAndHonors);
    }

    @Override
    public Long deleteAwardOrHonor(Physician physician, Long awardOrHonorId) {
        return physicianDetailsRepository.deleteAwardOrHonor(physician, awardOrHonorId);
    }

    @Override
    public void editAwardOrHonor(Physician physician, AwardsAndHonors awardsAndHonors, Long awardOrHonorId) {
        physicianDetailsRepository.editAwardOrHonor(physician, awardsAndHonors, awardOrHonorId);
    }

    @Override
    public List<AwardsAndHonors> allPhysicianAwardsAndHonors(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianAwardsAndHonors(physicianId);
    }

    @Override
    public List<GalleryImage> allPhysicianGalleryImages(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianGalleryImages(physicianId);
    }

    @Override
    public void addGalleryImage(String physicianId, GalleryImage galleryImage) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.addGalleryImage(physicianId,galleryImage);

    }

    @Override
    public void deActiveGalleryImage(String physicianId, Long imageId) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.deActiveGalleryImage(physicianId,imageId);

    }

}
