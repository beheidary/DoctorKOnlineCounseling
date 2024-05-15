package com.doctork.doctorkonlinecounseling.api.adapters.Elastic;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.AutoCompleteOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.SearchResultDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.SuggestOutputDto;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianfakeEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.IOException;

public interface ElasticAdapter {


    SearchResultDto search(String queryString, @PositiveOrZero Integer pageNumber, @Positive Integer pageSize) throws IOException;

    SuggestOutputDto TermSuggest(String queryString) throws IOException;
    AutoCompleteOutputDto autocomplete(String queryString , Integer resultSize) throws IOException;

}
