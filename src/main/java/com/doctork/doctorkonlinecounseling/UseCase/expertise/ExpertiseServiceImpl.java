package com.doctork.doctorkonlinecounseling.UseCase.expertise;

import com.doctork.doctorkonlinecounseling.boundary.exit.expertise.ExpertiseRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.expertise.ExpertiseService;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import com.doctork.doctorkonlinecounseling.domain.doctor.ExpertiseLatinNames;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<Expertise> getExpertises() {
        return expertiseRepository.getExpertises();
    }
}
