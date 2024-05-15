package com.doctork.doctorkonlinecounseling.boundary.in.searchEngine;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianfakeEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.io.IOException;

public interface ElasticService {



    SearchHits<ElasticPhysicianfakeEntity> search(String queryString, Integer pageNumber, Integer pageSize);

    SearchResponse<ElasticPhysicianfakeEntity> TermSuggest(String queryString) throws IOException;

    SearchResponse<ElasticPhysicianfakeEntity> autocomplete(String term, int size) throws IOException;







}
