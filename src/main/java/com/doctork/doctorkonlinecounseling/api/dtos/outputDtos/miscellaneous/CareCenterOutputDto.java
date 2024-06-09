package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.AddressOutputDto;


public class CareCenterOutputDto {
    private String name;
    private String imageUrl;
    private AddressOutputDto addressOutputDto;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AddressOutputDto getAddressOutputDto() {
        return addressOutputDto;
    }

    public void setAddressOutputDto(AddressOutputDto addressOutputDto) {
        this.addressOutputDto = addressOutputDto;
    }
}
