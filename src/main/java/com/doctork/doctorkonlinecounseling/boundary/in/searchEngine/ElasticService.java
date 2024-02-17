package com.doctork.doctorkonlinecounseling.boundary.in.searchEngine;

import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;

import java.util.List;

public interface ElasticService {

    Doctor syncDoctors(Doctor doctor);

    Doctor doctorByName (String name);




}
