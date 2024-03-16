package com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public interface ElasticRepository {


    Doctor syncDoctor(DoctorMongoEntity doctor);

    Doctor addDoctor(Doctor doctor);

    ElasticDoctorEntity deleteDoctor(String id);

    <T> SearchHits<T> search(Query query, Class<T> clazz) ;
    List<ElasticDoctorEntity> searchByRepository(String searchQuery);

    ElasticDoctorEntity editDoctor(String id , ElasticDoctorEntity doctor);

}
