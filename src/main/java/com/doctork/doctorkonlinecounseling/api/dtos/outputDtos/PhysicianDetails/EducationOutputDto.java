package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;

import java.time.LocalDate;
import java.util.Date;

public class EducationOutputDto {


    private Long id;

    private EducationLevel educationLevel;

    private String FieldOfStudy;

    private LocalDate graduationDate;

    private String educationPlace;


    public EducationOutputDto(Long id, EducationLevel educationLevel, String fieldOfStudy, LocalDate graduationDate, String educationPlace) {
        this.id = id;
        this.educationLevel = educationLevel;
        FieldOfStudy = fieldOfStudy;
        this.graduationDate = graduationDate;
        this.educationPlace = educationPlace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getFieldOfStudy() {
        return FieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        FieldOfStudy = fieldOfStudy;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getEducationPlace() {
        return educationPlace;
    }

    public void setEducationPlace(String educationPlace) {
        this.educationPlace = educationPlace;
    }
}
