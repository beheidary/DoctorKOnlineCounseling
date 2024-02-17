package com.doctork.doctorkonlinecounseling.database.searchEngineRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorElasticRepository extends ElasticsearchRepository<ElasticDoctorEntity,Long> {
}
