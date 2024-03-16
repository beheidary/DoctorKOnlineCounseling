package com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous;

import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SearchHitDTO {
    private String id;
    private double score;
    private List<String> sortValues;
    private ElasticDoctorEntity content;
//    private HighlightFieldsDTO highlightFields;
//     Add any additional fields as needed

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<String> getSortValues() {
        return sortValues;
    }

    public void setSortValues(List<String> sortValues) {
        this.sortValues = sortValues;
    }

    public ElasticDoctorEntity getContent() {
        return content;
    }

    public void setContent(ElasticDoctorEntity content) {
        this.content = content;
    }

//    public HighlightFieldsDTO getHighlightFields() {
//        return highlightFields;
//    }

//    public void setHighlightFields(HighlightFieldsDTO highlightFields) {
//        this.highlightFields = highlightFields;
//    }
}