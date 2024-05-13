package com.doctork.doctorkonlinecounseling.boundary.in.searchEngine;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.io.IOException;

public interface ElasticService {


    Physician setDoctorForSync(PhysicianMongoEntity doctor);

    ElasticPhysicianEntity deleteDoctor (String id );

    SearchHits<ElasticPhysicianEntity> search(String queryString, Integer pageNumber,Integer pageSize);

    SearchResponse<ElasticPhysicianEntity> TermSuggest(String queryString) throws IOException;

    Physician addDoctor(Physician physician);

    ElasticPhysicianEntity editDoctor(String id , ElasticPhysicianEntity doctor);

    SearchResponse<ElasticPhysicianEntity> autocomplete(String term, int size) throws IOException;







}
