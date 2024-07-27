package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import jakarta.validation.constraints.NotNull;

public class ServicesInputDto {


    @NotNull
    private String description;

    @NotNull
    private String title;

    @NotNull
    private Status priceStatus;


    public ServicesInputDto( String description, String title, Status priceStatus) {
        this.description = description;
        this.title = title;
        this.priceStatus = priceStatus;
    }

    public ServicesInputDto() {

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

    public Status getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(Status priceStatus) {
        this.priceStatus = priceStatus;
    }



}
