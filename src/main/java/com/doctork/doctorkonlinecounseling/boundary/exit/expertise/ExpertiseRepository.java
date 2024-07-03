package com.doctork.doctorkonlinecounseling.boundary.exit.expertise;

import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

import java.util.List;

public interface ExpertiseRepository {

    Expertise getExpertise(ExpertiseLatinNames LatinName);

    List<Expertise> getExpertises();

    Expertise addPhysicianExpertise(String nationalCode, Expertise expertise);
    Expertise addExpertise(Expertise expertise);
    Expertise editExpertise(Long expertiseId, Expertise expertise);

    List<TopExpertises> findBestExpertisePhysicians();



}
