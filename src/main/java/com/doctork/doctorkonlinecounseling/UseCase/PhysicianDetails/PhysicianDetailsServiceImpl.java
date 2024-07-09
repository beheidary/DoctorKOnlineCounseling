package com.doctork.doctorkonlinecounseling.UseCase.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails.PhysicianDetailsRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.PhysicianDetails.PhysicianDetailsService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Education;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.PhysicianSocialMedia;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.SocialMedia;
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
    public Long deleteEducation(String physicianId, Long educationId) {
        if (physicianId == null || educationId == null)
            throw new IdInputException();
        return physicianDetailsRepository.deleteEducation(physicianId,educationId);
    }

    @Override
    public void editEducation(String physicianId, Education education, Long educationId) {
        if (physicianId == null || educationId == null)
            throw new IdInputException();
        physicianDetailsRepository.editEducation(physicianId,education,educationId);

    }

    @Override
    public List<Education> allPhysicianEducations(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianEducations(physicianId);
    }
}
