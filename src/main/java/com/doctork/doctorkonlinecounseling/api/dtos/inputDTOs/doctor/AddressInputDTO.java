package com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class AddressInputDTO {

    @NotNull
    @NotBlank
    private String postalCode;

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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public LocalDateTime getSaveDateTime() {
        return saveDateTime;
    }

    public void setSaveDateTime(LocalDateTime saveDateTime) {
        this.saveDateTime = saveDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public AddressInputDTO(String postalCode, String address, Long cityId, LocalDateTime saveDateTime, LocalDateTime updateDateTime) {
        this.postalCode = postalCode;
        this.address = address;
        this.cityId = cityId;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
    }

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @Positive
    private Long cityId;
    private LocalDateTime saveDateTime;
    private LocalDateTime updateDateTime;


}
