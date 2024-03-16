package com.doctork.doctorkonlinecounseling.api.adapters.Elastic;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SearchHitDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SearchResultDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SuggestOutputDTO;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
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
    public Doctor setDoctorForSync(DoctorMongoEntity doctor) {

        return elasticService.setDoctorForSync(doctor);

    }

    @Override
    public ElasticDoctorEntity deleteDoctor(String id) {

        return elasticService.deleteDoctor(id);

    }

    @Override
    public SearchResultDTO search(String queryString) throws IOException {
        


        ObjectMapper objectMapper = new ObjectMapper();



        SearchHits<ElasticDoctorEntity> searchHits = elasticService.search(queryString);

        List<SearchHit> hitsList = new ArrayList<>();
        searchHits.forEach(hitsList::add);
        String jsonResult = null;
        try {
            jsonResult = objectMapper.writeValueAsString(hitsList);


            SearchResultDTO searchResultDTO = new SearchResultDTO();

            searchResultDTO.setMaxScore(searchHits.getMaxScore());
            searchResultDTO.setTotalHits(searchHits.getTotalHits());


            List<SearchHitDTO> searchHitDTOList = objectMapper.readValue(jsonResult, new TypeReference<List<SearchHitDTO>>() {});
            searchResultDTO.setSearchHits(searchHitDTOList);
            return searchResultDTO;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public SuggestOutputDTO TermSuggest(String queryString) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();


        String jsonResponse = String.valueOf(elasticService.TermSuggest(queryString));
        int startIndex = jsonResponse.indexOf(":") + 1;
        jsonResponse = jsonResponse.substring(startIndex);


        try {
            SuggestOutputDTO suggestOutputDTO = objectMapper.readValue(jsonResponse, SuggestOutputDTO.class);

            return suggestOutputDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        if(doctor == null)
            throw new IdInputException();

        return elasticService.addDoctor(doctor);
    }

    @Override
    public ElasticDoctorEntity editDoctor(String id, ElasticDoctorEntity doctor) {
        if(id == null)
            throw new IdInputException();

        return elasticService.editDoctor(id,doctor);
    }
}
