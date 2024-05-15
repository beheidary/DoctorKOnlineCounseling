package com.doctork.doctorkonlinecounseling.api.adapters.Elastic;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.AutoCompleteOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.SearchHitDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.SearchResultDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.SuggestOutputDto;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianfakeEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class ElasticAdapterImpl implements ElasticAdapter {



    ElasticService elasticService;

    public ElasticAdapterImpl(ElasticService elasticService) {
        this.elasticService = elasticService;
    }


    @Override
    public SearchResultDto search(String queryString, Integer pageNumber, Integer pageSize) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();



        SearchHits<ElasticPhysicianfakeEntity> searchHits = elasticService.search(queryString,pageNumber,pageSize);

        List<SearchHit> hitsList = new ArrayList<>();
        searchHits.forEach(hitsList::add);
        String jsonResult = null;
        try {
            jsonResult = objectMapper.writeValueAsString(hitsList);


            SearchResultDto searchResultDTO = new SearchResultDto();

            searchResultDTO.setMaxScore(searchHits.getMaxScore());
            searchResultDTO.setTotalHits(searchHits.getTotalHits());


            List<SearchHitDto> searchHitDtoList = objectMapper.readValue(jsonResult, new TypeReference<List<SearchHitDto>>() {});
            searchResultDTO.setSearchHits(searchHitDtoList);
            return searchResultDTO;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public SuggestOutputDto TermSuggest(String queryString) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();


        String jsonResponse = String.valueOf(elasticService.TermSuggest(queryString));
        int startIndex = jsonResponse.indexOf(":") + 1;
        jsonResponse = jsonResponse.substring(startIndex);


        try {
            SuggestOutputDto suggestOutputDTO = objectMapper.readValue(jsonResponse, SuggestOutputDto.class);

            return suggestOutputDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public AutoCompleteOutputDto autocomplete(String queryString, Integer resultSize) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();


        String jsonResponse = String.valueOf(elasticService.autocomplete(queryString,resultSize));
        int startIndex = jsonResponse.indexOf(":") + 1;
        jsonResponse = jsonResponse.substring(startIndex);


        try {
            AutoCompleteOutputDto autoCompleteOutputDTO = objectMapper.readValue(jsonResponse, AutoCompleteOutputDto.class);

            return autoCompleteOutputDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
