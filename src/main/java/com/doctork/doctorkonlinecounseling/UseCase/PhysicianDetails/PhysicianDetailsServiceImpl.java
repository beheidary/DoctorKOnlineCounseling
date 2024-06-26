package com.doctork.doctorkonlinecounseling.UseCase.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails.PhysicianDetailsRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.PhysicianDetails.PhysicianDetailsService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class PhysicianDetailsServiceImpl implements PhysicianDetailsService {

    private final PhysicianDetailsRepository physicianDetailsRepository;

    public PhysicianDetailsServiceImpl(PhysicianDetailsRepository physicianDetailsRepository) {
        this.physicianDetailsRepository = physicianDetailsRepository;
    }

    @Override
    public void crateSickness(Sickness sickness) {
        physicianDetailsRepository.crateSickness(sickness);
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
}
