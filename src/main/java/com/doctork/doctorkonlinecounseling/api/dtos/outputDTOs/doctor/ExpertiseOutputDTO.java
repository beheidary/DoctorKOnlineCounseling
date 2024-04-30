package com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor;

import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

import java.time.LocalDateTime;
import java.util.List;

public class ExpertiseOutputDTO {


    private Long id;

    private String name;

    private LocalDateTime saveDateTime;
    private LocalDateTime updateDateTime;
    private ExpertiseLatinNames latinName;
    private List<DoctorOutputDTO> doctors;

    public ExpertiseOutputDTO(Long id, String name, LocalDateTime saveDateTime, LocalDateTime updateDateTime, ExpertiseLatinNames latinName, List<DoctorOutputDTO> doctors) {
        this.id = id;
        this.name = name;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.latinName = latinName;
        this.doctors = doctors;
    }


    public ExpertiseOutputDTO() {

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
