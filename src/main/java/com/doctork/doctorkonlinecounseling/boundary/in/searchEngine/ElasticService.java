package com.doctork.doctorkonlinecounseling.boundary.in.searchEngine;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SearchResultDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SuggestOutputDTO;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.io.IOException;
import java.util.List;

public interface ElasticService {


    Doctor setDoctorForSync(DoctorMongoEntity doctor);

    ElasticDoctorEntity deleteDoctor (String id );

    SearchHits<ElasticDoctorEntity> search(String queryString);

    SearchResponse<ElasticDoctorEntity> TermSuggest(String queryString) throws IOException;

    Doctor addDoctor(Doctor doctor);

    ElasticDoctorEntity editDoctor(String id , ElasticDoctorEntity doctor);

    //SearchResponse<ElasticDoctorEntity> autocomplete(String term, int size) throws IOException;







}
