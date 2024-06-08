package com.doctork.doctorkonlinecounseling.api.adapters.Elastic;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.*;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.AutoCompleteOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.CareCenterOutputDto;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianfakeEntity;
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
    public SuggestedSentencesOutputDto TermSuggest(String queryString) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();


        String jsonResponse = String.valueOf(elasticService.TermSuggest(queryString));
        int startIndex = jsonResponse.indexOf(":") + 1;
        jsonResponse = jsonResponse.substring(startIndex);


        try {
            SuggestedSentencesOutputDto suggestedSentencesOutputDTO = objectMapper.readValue(jsonResponse, SuggestedSentencesOutputDto.class);

            return suggestedSentencesOutputDTO;
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

    @Override
    public CompleteSuggestionOutputDto completeSuggests(String queryString) throws IOException {

        try {
            CompleteSuggestionOutputDto completeSuggestionOutputDto = new CompleteSuggestionOutputDto();
            ObjectMapper objectMapper = new ObjectMapper();


            String jsonResponse = String.valueOf(elasticService.TermSuggest(queryString));
            int startIndex = jsonResponse.indexOf(":") + 1;
            jsonResponse = jsonResponse.substring(startIndex);
            SuggestedSentencesOutputDto suggestedSentencesOutputDTO = objectMapper.readValue(jsonResponse, SuggestedSentencesOutputDto.class);

             completeSuggestionOutputDto.setPhraseSuggestions(suggestedSentencesOutputDTO);



            SearchHits<ElasticPhysicianfakeEntity> physiciansSearchHits = elasticService.physiciansSearch(queryString);
            SearchHits<ElasticPhysicianfakeEntity> expertisesSearchHits = elasticService.expertisesSearch(queryString);

            List<SearchHit> hitsList2 = new ArrayList<>();
            physiciansSearchHits.forEach(hitsList2::add);
            String jsonResult2 = null;

            jsonResult2 = objectMapper.writeValueAsString(hitsList2);
            List<SearchHitDto> physiciansSearchHitsList = objectMapper.readValue(jsonResult2, new TypeReference<List<SearchHitDto>>() {});

            List<SearchHit> hitsList = new ArrayList<>();
            expertisesSearchHits.forEach(hitsList::add);
            String jsonResult = null;

            jsonResult = objectMapper.writeValueAsString(hitsList);
            List<SearchHitDto> expertisesSearchHitsList = objectMapper.readValue(jsonResult, new TypeReference<List<SearchHitDto>>() {});

            List<CareCenterOutputDto> careCenters = new ArrayList<>();

            List<SuggestedPhysiciansOutputDto> suggestedPhysiciansOutputDtos = new ArrayList<>();

            List<SuggestedExpertisesOutputDto> suggestedExpertisesOutputDtos = new ArrayList<>();

            for (int i=0 ; i<=2 ; i++){
                SuggestedPhysiciansOutputDto suggestedPhysiciansOutputDto = new SuggestedPhysiciansOutputDto();
                SuggestedExpertisesOutputDto suggestedExpertisesOutputDto = new SuggestedExpertisesOutputDto();
                if (physiciansSearchHitsList.size()>0){
                suggestedPhysiciansOutputDto.setFullName(physiciansSearchHitsList.get(i).getContent().getFullName());
                suggestedPhysiciansOutputDto.setExpertise(physiciansSearchHitsList.get(i).getContent().getExpertise());
                suggestedPhysiciansOutputDto.setNationalCode(55L);
                suggestedPhysiciansOutputDtos.add(suggestedPhysiciansOutputDto);
                }

                if (expertisesSearchHitsList.size()>0) {
                    suggestedExpertisesOutputDto.setName(expertisesSearchHitsList.get(i).getContent().getExpertise());
                    suggestedExpertisesOutputDto.setNumberOfPhysicians(10);
                    suggestedExpertisesOutputDtos.add(suggestedExpertisesOutputDto);
                }

                }

            completeSuggestionOutputDto.setSuggestedPhysicians(suggestedPhysiciansOutputDtos);
            completeSuggestionOutputDto.setSuggestedExpertises(suggestedExpertisesOutputDtos);
            completeSuggestionOutputDto.setCareCenters(careCenters);

            return completeSuggestionOutputDto;


















        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
