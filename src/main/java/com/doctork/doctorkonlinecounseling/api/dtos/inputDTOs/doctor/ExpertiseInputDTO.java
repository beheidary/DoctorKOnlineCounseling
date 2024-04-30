package com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor;

import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

public class ExpertiseInputDTO {


    private String name;

    private ExpertiseLatinNames latinName;


    public ExpertiseInputDTO(String name, ExpertiseLatinNames latinName) {
        this.name = name;
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

}
