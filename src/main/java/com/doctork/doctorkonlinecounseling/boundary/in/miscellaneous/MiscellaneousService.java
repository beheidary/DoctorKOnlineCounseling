package com.doctork.doctorkonlinecounseling.boundary.in.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Article;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.FAQ;

import java.util.List;

public interface MiscellaneousService {

    List<FAQ> getFaq();

}
