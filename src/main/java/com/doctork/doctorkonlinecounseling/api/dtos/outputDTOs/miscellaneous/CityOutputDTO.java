package com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous;

public class CityOutputDTO {

    private Long id;

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

    public CityOutputDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;

}
