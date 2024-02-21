package com.doctork.doctorkonlinecounseling.UseCase.searchEngine;

import com.doctork.doctorkonlinecounseling.boundary.exit.doctor.DoctorRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElasticServiceImpl implements ElasticService {

    private final DoctorRepository doctorRepository;
    private final ElasticRepository elasticRepository;

    public ElasticServiceImpl(ElasticRepository elasticRepository, DoctorRepository doctorRepository) {
        this.elasticRepository = elasticRepository;
        this.doctorRepository = doctorRepository;
    }


    @Override
    public Doctor doctorByName(String name) {
        return null;
    }

    @Override
    public Doctor getDoctorForSync(DoctorMongoEntity Mongodoctor) {

        return elasticRepository.syncDoctor(Mongodoctor);

    }

    @Override
    public String deleteEntity(int id) {
        return elasticRepository.deleteEntity(id);
    }
}
