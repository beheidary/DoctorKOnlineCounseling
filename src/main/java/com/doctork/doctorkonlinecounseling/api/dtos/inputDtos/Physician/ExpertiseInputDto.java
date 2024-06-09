package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician;

import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

public class ExpertiseInputDto {


    private String name;

    private ExpertiseLatinNames latinName;

    private String imageName;



    public ExpertiseInputDto(String imageName, String name, ExpertiseLatinNames latinName) {
        this.name = name;
        this.imageName = imageName;
        this.latinName = latinName;
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
