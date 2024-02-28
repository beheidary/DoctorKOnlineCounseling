package com.doctork.doctorkonlinecounseling.database.searchEngineRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorElasticRepository extends ElasticsearchRepository<ElasticDoctorEntity,Long> {


    List<ElasticDoctorEntity> findByNameAndSpeciality(String fullName, String speciality);

    List<ElasticDoctorEntity> findBySpeciality(String speciality);
    List<ElasticDoctorEntity> findBySpecialityLike(String speciality);

    ElasticDoctorEntity deleteBy_idT(String id);

    ElasticDoctorEntity findBy_idT(String id);

}
