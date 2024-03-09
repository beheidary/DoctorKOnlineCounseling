package com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor;

import com.doctork.doctorkonlinecounseling.domain.doctor.ExpertiseLatinNames;

import java.time.LocalDateTime;

public class ExpertiseInputDTO {


    private Long id;

    private String name;

    private Long Expertise;
    private LocalDateTime saveDateTime;
    private LocalDateTime updateDateTime;
    private ExpertiseLatinNames latinName;


    public ExpertiseInputDTO(Long id, String name, Long expertise, LocalDateTime saveDateTime, LocalDateTime updateDateTime, ExpertiseLatinNames latinName) {
        this.id = id;
        this.name = name;
        Expertise = expertise;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.latinName = latinName;
    }

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

    public Long getExpertise() {
        return Expertise;
    }

    public void setExpertise(Long expertise) {
        Expertise = expertise;
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

    public ExpertiseLatinNames getLatinName() {
        return latinName;
    }

    public void setLatinName(ExpertiseLatinNames latinName) {
        this.latinName = latinName;
    }

}
