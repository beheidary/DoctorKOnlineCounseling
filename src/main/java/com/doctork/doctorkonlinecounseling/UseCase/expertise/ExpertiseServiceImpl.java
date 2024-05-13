package com.doctork.doctorkonlinecounseling.UseCase.expertise;

import com.doctork.doctorkonlinecounseling.boundary.exit.expertise.ExpertiseRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.expertise.ExpertiseService;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertiseServiceImpl implements ExpertiseService {

    ExpertiseRepository expertiseRepository;

    public ExpertiseServiceImpl(ExpertiseRepository expertiseRepository) {
        this.expertiseRepository = expertiseRepository;
    }

    @Override
    public Expertise getExpertise(ExpertiseLatinNames latinName) {


        return expertiseRepository.getExpertise(latinName);

    }

    @Override
    public Expertise addPhysicianExpertise(Long nationalCode, Expertise expertise) {
        return expertiseRepository.addPhysicianExpertise(nationalCode,expertise);
    }

    @Override
    public List<Expertise> getExpertises() {
        return expertiseRepository.getExpertises();
    }

    @Override
    public List<TopExpertises> findBestExpertisePhysicians() {
        return expertiseRepository.findBestExpertisePhysicians();
    }
}
