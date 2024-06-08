package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.CareCenterOutputDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CompleteSuggestionOutputDto {
    private SuggestedSentencesOutputDto phraseSuggestions;

    private List<SuggestedExpertisesOutputDto> SuggestedExpertises;

    private List<SuggestedPhysiciansOutputDto> SuggestedPhysicians;

    private List<CareCenterOutputDto> careCenters;

    private List<String> diseases;


    public CompleteSuggestionOutputDto() {
    }

    public List<String> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<String> diseases) {
        this.diseases = diseases;
    }

    public SuggestedSentencesOutputDto getPhraseSuggestions() {
        return phraseSuggestions;
    }

    public void setPhraseSuggestions(SuggestedSentencesOutputDto phraseSuggestions) {

        this.phraseSuggestions = phraseSuggestions;
    }

    public List<CareCenterOutputDto> getCareCenters() {
        return careCenters;
    }

    public void setCareCenters(List<CareCenterOutputDto> careCenters) {
        this.careCenters = careCenters;
    }

    public List<SuggestedExpertisesOutputDto> getSuggestedExpertises() {
        return SuggestedExpertises;
    }

    public void setSuggestedExpertises(List<SuggestedExpertisesOutputDto> suggestedExpertises) {
        SuggestedExpertises = suggestedExpertises;
    }

    public List<SuggestedPhysiciansOutputDto> getSuggestedPhysicians() {
        return SuggestedPhysicians;
    }

    public void setSuggestedPhysicians(List<SuggestedPhysiciansOutputDto> suggestedPhysicians) {
        SuggestedPhysicians = suggestedPhysicians;
    }
}

