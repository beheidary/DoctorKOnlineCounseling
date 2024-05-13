package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CityInputDto {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityInputDto(String name) {
        this.name = name;
    }

    @NotNull
    @NotBlank
    private String name;


}
