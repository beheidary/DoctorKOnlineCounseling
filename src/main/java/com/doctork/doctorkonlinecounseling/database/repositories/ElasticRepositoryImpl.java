package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.database.mappers.searchEngine.ElasticEntityMapper;
import com.doctork.doctorkonlinecounseling.database.searchEngineRepositories.DoctorElasticRepository;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ElasticRepositoryImpl implements ElasticRepository {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticEntityMapper elasticEntityMapper;
    private final DoctorElasticRepository doctorElasticRepository;

    public ElasticRepositoryImpl(ElasticsearchOperations elasticsearchOperations, ElasticEntityMapper elasticEntityMapper, DoctorElasticRepository doctorElasticRepository) {
        this.elasticEntityMapper = elasticEntityMapper;
        this.doctorElasticRepository = doctorElasticRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }



    @Override
    public Doctor syncDoctor(Doctor doctor) {


        ElasticDoctorEntity elasticDoctorEntity = elasticEntityMapper.modelToEntity(doctor);
        ElasticDoctorEntity data = elasticsearchOperations.sa
        //doctor = doctorElasticRepository
        return null;
    }

    @Override
    public Doctor doctorByName(String name) {
        return null;
    }
}
