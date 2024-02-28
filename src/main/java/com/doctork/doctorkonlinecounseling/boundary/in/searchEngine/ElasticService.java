package com.doctork.doctorkonlinecounseling.boundary.in.searchEngine;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface ElasticService {


    Doctor setDoctorForSync(DoctorMongoEntity doctor);

    ElasticDoctorEntity deleteDoctor (String id );

    List<Doctor> search(String queryString);

    ElasticDoctorEntity editDoctor(String id , ElasticDoctorEntity doctor);





}
