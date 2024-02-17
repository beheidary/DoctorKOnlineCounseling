package com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine;

import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;

import java.util.List;

public interface ElasticRepository {


    Doctor syncDoctor(Doctor doctor);

    Doctor doctorByName (String name);

}
