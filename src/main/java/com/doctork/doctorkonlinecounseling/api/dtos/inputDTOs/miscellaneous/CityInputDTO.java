package com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CityInputDTO {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityInputDTO(String name) {
        this.name = name;
    }

    @NotNull
    @NotBlank
    private String name;


}
