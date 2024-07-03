package com.doctork.doctorkonlinecounseling.UseCase.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails.PhysicianDetailsRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.PhysicianDetails.PhysicianDetailsService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.PhysicianSocialMedia;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.SocialMedia;
import org.springframework.stereotype.Service;
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
    public Set<Sickness> uploadSickness(Long physicianId, Set<Sickness> sicknesses) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.uploadSickness(physicianId,sicknesses);
    }

    @Override
    public Set<Sickness> allSicknesses() {

        return physicianDetailsRepository.allSicknesses();
    }

    @Override
    public Set<Sickness> allPhysicianSicknesses(Long physicianId) {
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
    public Set<PhysicianSocialMedia> allPhysicianSocialMedias(Long physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianSocialMedias(physicianId);
    }

    @Override
    public Set<SocialMedia> allSocialMedias() {

        return physicianDetailsRepository.allSocialMedias();
    }

    @Override
    public Set<PhysicianSocialMedia> uploadSocialMedias(Long physicianId, Set<PhysicianSocialMedia> physicianSocialMedia) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.uploadSocialMedias(physicianId,physicianSocialMedia);
    }

    @Override
    public void addSocialMedia(SocialMedia socialMedia) {
        physicianDetailsRepository.addSocialMedia(socialMedia);
    }
}
