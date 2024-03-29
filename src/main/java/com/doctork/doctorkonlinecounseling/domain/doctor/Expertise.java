package com.doctork.doctorkonlinecounseling.domain.doctor;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Expertise {


    private Long id;


    private String name;


    private Long Expertise;

    private LocalDateTime saveDateTime;

    private LocalDateTime updateDateTime;

    private ExpertiseLatinNames latinName;

    private Set<Doctor> doctors = new HashSet<>();

    public Expertise(Long id, String name, Long expertise, LocalDateTime saveDateTime, LocalDateTime updateDateTime, ExpertiseLatinNames latinName, Set<Doctor> doctors) {
        this.id = id;
        this.name = name;
        Expertise = expertise;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.latinName = latinName;
        this.doctors = doctors;
    }

    public Expertise() {

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

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }
}
