package com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;

import java.util.List;

public interface ElasticRepository {


    Doctor syncDoctor(DoctorMongoEntity doctor);

    Doctor doctorByName (String name);

    String deleteEntity(int id);
}
