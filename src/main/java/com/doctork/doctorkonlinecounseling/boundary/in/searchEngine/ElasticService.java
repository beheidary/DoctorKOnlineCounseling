package com.doctork.doctorkonlinecounseling.boundary.in.searchEngine;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;

import java.util.List;

public interface ElasticService {


    Doctor doctorByName (String name);


    Doctor getDoctorForSync(DoctorMongoEntity doctor);

    String deleteEntity (int id);





}
