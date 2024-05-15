package com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianfakeEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public interface ElasticRepository {


    Physician addPhysician(Physician physician);

    Long deletePhysician(Long id);

    <T> SearchHits<T> search(Query query, Class<T> clazz) ;

    ElasticPhysicianEntity editPhysician(Long id ,Physician physician );

}
