package com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.CityOutputDTO;

import java.util.UUID;

public class AddressOutputDTO {

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

    public CityOutputDTO getCity() {
        return city;
    }

    public void setCity(CityOutputDTO city) {
        this.city = city;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public AddressOutputDTO(Long id, String postalCode, String address, Double latitude, Double longitude, CityOutputDTO city, UUID userId) {
        this.id = id;
        this.postalCode = postalCode;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.userId = userId;
    }

    private Double latitude;
    private Double longitude;
    private CityOutputDTO city;
    private UUID userId;



}
