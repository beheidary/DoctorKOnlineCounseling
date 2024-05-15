package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos;

import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianfakeEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SearchHitDto {
    private String id;
    private double score;
    private List<String> sortValues;
    private ElasticPhysicianfakeEntity content;
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

    public ElasticPhysicianfakeEntity getContent() {
        return content;
    }

    public void setContent(ElasticPhysicianfakeEntity content) {
        this.content = content;
    }

//    public HighlightFieldsDTO getHighlightFields() {
//        return highlightFields;
//    }

//    public void setHighlightFields(HighlightFieldsDTO highlightFields) {
//        this.highlightFields = highlightFields;
//    }
}