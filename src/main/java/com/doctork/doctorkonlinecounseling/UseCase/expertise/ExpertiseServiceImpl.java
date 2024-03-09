package com.doctork.doctorkonlinecounseling.UseCase.expertise;

import com.doctork.doctorkonlinecounseling.boundary.exit.expertise.ExpertiseRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.expertise.ExpertiseService;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import com.doctork.doctorkonlinecounseling.domain.doctor.ExpertiseLatinNames;

import java.time.LocalDateTime;

public class ExpertiseServiceImpl implements ExpertiseService {

    ExpertiseRepository expertiseRepository;


    public ExpertiseServiceImpl(ExpertiseRepository expertiseRepository) {
        this.expertiseRepository = expertiseRepository;
    }


    @Override
    public Expertise addDoctorExpertise(String PSCode, Expertise expertise) {
        return null;
    }

    @Override
    public Expertise getExpertise(ExpertiseLatinNames latinName) {


        return expertiseRepository.getExpertise(latinName);




    }
}
