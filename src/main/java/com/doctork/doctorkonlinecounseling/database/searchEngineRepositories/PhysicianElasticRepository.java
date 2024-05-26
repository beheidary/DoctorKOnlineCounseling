package com.doctork.doctorkonlinecounseling.database.searchEngineRepositories;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicianElasticRepository extends ElasticsearchRepository<ElasticPhysicianEntity,Long> {



}
