package com.doctork.doctorkonlinecounseling.boundary.in.expertise;

import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

import java.util.List;

public interface ExpertiseService {

    Expertise getExpertise(ExpertiseLatinNames latinName);



    List<Expertise> getExpertises();

    Expertise addPhysicianExpertise(Long nationalCode, Expertise expertise);
    Expertise addExpertise(Expertise expertise);
    Expertise editExpertise(Long expertiseId , Expertise expertise);


    List<TopExpertises> findBestExpertisePhysicians();





}
