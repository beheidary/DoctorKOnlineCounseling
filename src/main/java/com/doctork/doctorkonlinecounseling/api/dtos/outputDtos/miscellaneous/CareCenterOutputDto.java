package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.AddressOutputDto;


public class CareCenterOutputDto {
    private String name;
    private String imageName;
    private AddressOutputDto addressOutputDto;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public AddressOutputDto getAddressOutputDto() {
        return addressOutputDto;
    }

    public void setAddressOutputDto(AddressOutputDto addressOutputDto) {
        this.addressOutputDto = addressOutputDto;
    }
}
