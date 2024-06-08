package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos;

import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

public class SuggestedExpertisesOutputDto {

    private Long id;

    private String name;

    private ExpertiseLatinNames latinName;

    private String imageName;

    private Integer numberOfPhysicians;

    public Integer getNumberOfPhysicians() {
        return numberOfPhysicians;
    }

    public void setNumberOfPhysicians(Integer numberOfPhysicians) {
        this.numberOfPhysicians = numberOfPhysicians;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExpertiseLatinNames getLatinName() {
        return latinName;
    }

    public void setLatinName(ExpertiseLatinNames latinName) {
        this.latinName = latinName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
