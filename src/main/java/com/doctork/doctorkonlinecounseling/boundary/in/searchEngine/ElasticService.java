package com.doctork.doctorkonlinecounseling.boundary.in.searchEngine;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.io.IOException;

public interface ElasticService {


    Doctor setDoctorForSync(DoctorMongoEntity doctor);

    ElasticDoctorEntity deleteDoctor (String id );

    SearchHits<ElasticDoctorEntity> search(String queryString, Integer pageNumber,Integer pageSize);

    SearchResponse<ElasticDoctorEntity> TermSuggest(String queryString) throws IOException;

    Doctor addDoctor(Doctor doctor);

    ElasticDoctorEntity editDoctor(String id , ElasticDoctorEntity doctor);

    SearchResponse<ElasticDoctorEntity> autocomplete(String term, int size) throws IOException;







}
