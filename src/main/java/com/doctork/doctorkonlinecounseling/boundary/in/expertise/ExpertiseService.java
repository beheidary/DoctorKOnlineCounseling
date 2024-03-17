package com.doctork.doctorkonlinecounseling.boundary.in.expertise;

import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import com.doctork.doctorkonlinecounseling.domain.doctor.ExpertiseLatinNames;

import java.util.List;

public interface ExpertiseService {

    Expertise addDoctorExpertise(String PSCode, Expertise expertise);

    Expertise getExpertise(ExpertiseLatinNames latinName);

    List<Expertise> getExpertises();




}
