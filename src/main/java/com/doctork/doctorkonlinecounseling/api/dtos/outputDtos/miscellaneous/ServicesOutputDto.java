package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.Enums.PriceStatus;

public class ServicesOutputDto {


    private Long Id;
    private String description;
    private String title;
    private PriceStatus priceStatus;


    public ServicesOutputDto(Long Id, String description, String title, PriceStatus priceStatus) {
        this.description = description;
        this.Id = Id;
        this.title = title;
        this.priceStatus = priceStatus;
    }

    public ServicesOutputDto() {

    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PriceStatus getStatus() {
        return priceStatus;
    }

    public void setStatus(PriceStatus priceStatus) {
        this.priceStatus = priceStatus;
    }



}
