package com.doctork.doctorkonlinecounseling.UseCase.searchEngine;

import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElasticServiceImpl implements ElasticService {

    private final ElasticRepository elasticRepository;

    public ElasticServiceImpl(ElasticRepository elasticRepository) {
        this.elasticRepository = elasticRepository;
    }

    @Override
    public Doctor syncDoctors(Doctor doctor) {

        // Todo fetch data from data base
        // Todo Map Entity to model
        // Todo sync with elastic repository


        doctor = elasticRepository.syncDoctor(doctor);





        return null;
    }

    @Override
    public Doctor doctorByName(String name) {
        return null;
    }
}
