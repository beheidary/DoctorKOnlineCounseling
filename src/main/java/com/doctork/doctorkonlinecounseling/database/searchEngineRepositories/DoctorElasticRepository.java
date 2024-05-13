package com.doctork.doctorkonlinecounseling.database.searchEngineRepositories;
import org.springframework.data.elasticsearch.annotations.Query;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorElasticRepository extends ElasticsearchRepository<ElasticPhysicianEntity,Long> {


    List<ElasticPhysicianEntity> findByNameAndSpeciality(String fullName, String speciality);

    List<ElasticPhysicianEntity> findBySpeciality(String speciality);
    List<ElasticPhysicianEntity> findBySpecialityLike(String speciality);

    ElasticPhysicianEntity deleteBy_idT(String id);

    ElasticPhysicianEntity findBy_idT(String id);




}
