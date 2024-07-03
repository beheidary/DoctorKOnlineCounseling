package com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianfakeEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.RabbitMqMessages.CostumeMessage;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;
import java.util.Stack;

public interface ElasticRepository {


    PhysicianEntity addPhysician(CostumeMessage message);

    String deletePhysician(String id);

    <T> SearchHits<T> search(Query query, Class<T> clazz) ;

    ElasticPhysicianEntity changeStatus (String id  , PhysicianStatus physicianStatus);

    ElasticPhysicianEntity changeState (String id  , State state);


    ElasticPhysicianEntity editPhysician(String id , PhysicianEntity physicianEntity );

}
