package com.doctork.doctorkonlinecounseling.UseCase.expertise;

import com.doctork.doctorkonlinecounseling.boundary.exit.expertise.ExpertiseRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.expertise.ExpertiseService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.domain.Expertise.RequestedExpertise;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExpertiseServiceImpl implements ExpertiseService {

    ExpertiseRepository expertiseRepository;

    public ExpertiseServiceImpl(ExpertiseRepository expertiseRepository) {
        this.expertiseRepository = expertiseRepository;
    }

    @Override
    public Expertise getExpertise(String latinName) {


        return expertiseRepository.getExpertise(latinName);

    }

    @Override
    public Expertise editExpertise(Long expertiseId, Expertise expertise) {
        return expertiseRepository.editExpertise(expertiseId,expertise);
    }

    @Override
    public List<Expertise> getExpertises() {
        return expertiseRepository.getExpertises();
    }

    @Override
    public List<TopExpertises> findBestExpertisePhysicians() {
        return expertiseRepository.findBestExpertisePhysicians();
    }

    @Override
    public Set<Expertise> uploadExpertise(String physicianId, Set<Expertise> expertise) {
        if (physicianId == null)
            throw new IdInputException();
        return expertiseRepository.uploadExpertise(physicianId,expertise);
    }

    @Override
    public Set<Expertise> allPhysicianExpertises(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return expertiseRepository.allPhysicianExpertises(physicianId);

    }

    @Override
    public void requestToAddExpertise(RequestedExpertise requestedExpertise) {
        expertiseRepository.requestToAddExpertise(requestedExpertise);
    }

    @Override
    public List<RequestedExpertise> waitingExpertises() {
        return expertiseRepository.waitingExpertises();
    }

    @Override
    public RequestedExpertise expertiseChangeState(RequestedExpertise requestedExpertise) {
        return expertiseRepository.expertiseChangeState(requestedExpertise);
    }
}
