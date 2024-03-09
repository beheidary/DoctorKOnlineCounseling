package com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.ExpertiseLatinNames;

import java.time.LocalDateTime;
import java.util.List;

public class ExpertiseOutputDTO {


    private Long id;

    private String name;

    private Long Expertise;
    private LocalDateTime saveDateTime;
    private LocalDateTime updateDateTime;
    private ExpertiseLatinNames latinName;
    private List<DoctorOutputDTO> doctors;

    public ExpertiseOutputDTO(Long id, String name, Long expertise, LocalDateTime saveDateTime, LocalDateTime updateDateTime, ExpertiseLatinNames latinName, List<DoctorOutputDTO> doctors) {
        this.id = id;
        this.name = name;
        Expertise = expertise;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.latinName = latinName;
        this.doctors = doctors;
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

    public List<DoctorOutputDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorOutputDTO> doctors) {
        this.doctors = doctors;
    }
}
