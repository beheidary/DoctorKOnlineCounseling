package com.doctork.doctorkonlinecounseling.boundary.exit.expertise;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import com.doctork.doctorkonlinecounseling.domain.doctor.ExpertiseLatinNames;

public interface ExpertiseRepository {

    Expertise getExpertise(ExpertiseLatinNames LatinName);


}
