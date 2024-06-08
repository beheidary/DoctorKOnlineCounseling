package com.doctork.doctorkonlinecounseling.api.adapters.Elastic;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.CompleteSuggestionOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.AutoCompleteOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.SearchResultDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.SuggestedSentencesOutputDto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.IOException;

public interface ElasticAdapter {


    SearchResultDto search(String queryString, @PositiveOrZero Integer pageNumber, @Positive Integer pageSize) throws IOException;

    SuggestedSentencesOutputDto TermSuggest(String queryString) throws IOException;
    AutoCompleteOutputDto autocomplete(String queryString , Integer resultSize) throws IOException;

    CompleteSuggestionOutputDto completeSuggests(String queryString) throws IOException;

}
