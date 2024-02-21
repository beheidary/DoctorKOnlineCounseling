package com.doctork.doctorkonlinecounseling.database.repositories;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBase;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.database.mappers.searchEngine.ElasticEntityMapper;
import com.doctork.doctorkonlinecounseling.database.searchEngineRepositories.DoctorElasticRepository;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ElasticRepositoryImpl implements ElasticRepository {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticEntityMapper elasticEntityMapper;

//    private final MappingElasticsearchConverter mappingElasticsearchConverter;

    private final DoctorElasticRepository doctorElasticRepository;


//    private final ReactiveElasticsearchOperations reactiveElasticsearchOperations;


    public ElasticRepositoryImpl(ElasticsearchOperations elasticsearchOperations, ElasticEntityMapper elasticEntityMapper, DoctorElasticRepository doctorElasticRepository) {
        this.elasticEntityMapper = elasticEntityMapper;
        this.doctorElasticRepository = doctorElasticRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }



    @Override
    public Doctor syncDoctor(DoctorMongoEntity doctor) {


        ElasticDoctorEntity elasticDoctorEntity = elasticEntityMapper.mongoToElasticMapper(doctor);
        ElasticDoctorEntity data = elasticsearchOperations.save(elasticDoctorEntity);
        System.out.println(data);
        return elasticEntityMapper.ElasticToDomainMapper(data);
    }

    @Override
    public Doctor doctorByName(String name) {
        return null;
    }

    @Override
    public String deleteEntity(int id) {
        DeleteRequest request = DeleteRequest.of(d -> d.index("doctorlibrary"));


        return elasticsearchOperations.delete(request);
    }

}
