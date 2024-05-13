package com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public interface ElasticRepository {


    Physician syncDoctor(PhysicianMongoEntity doctor);

    Physician addDoctor(Physician physician);

    ElasticPhysicianEntity deleteDoctor(String id);

    <T> SearchHits<T> search(Query query, Class<T> clazz) ;
    List<ElasticPhysicianEntity> searchByRepository(String searchQuery);

    ElasticPhysicianEntity editDoctor(String id , ElasticPhysicianEntity doctor);

}
