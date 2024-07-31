package com.doctork.doctorkonlinecounseling.api.adapters.miscellaneous;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.ArticleOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.FAQOutputDto;

import java.util.List;

public interface MiscellaneousAdapter {

    List<FAQOutputDto> getFaq();

}
