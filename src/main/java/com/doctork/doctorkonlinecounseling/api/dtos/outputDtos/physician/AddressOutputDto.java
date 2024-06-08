package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.CityOutputDto;

import java.util.UUID;

public class AddressOutputDto {

    private Long id;
    private String postalCode;
    private String address;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public CityOutputDto getCity() {
        return city;
    }

    public void setCity(CityOutputDto city) {
        this.city = city;
    }


    public AddressOutputDto(Long id, String postalCode, String address, Double latitude, Double longitude, CityOutputDto city) {
        this.id = id;
        this.postalCode = postalCode;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    private Double latitude;
    private Double longitude;
    private CityOutputDto city;



}
