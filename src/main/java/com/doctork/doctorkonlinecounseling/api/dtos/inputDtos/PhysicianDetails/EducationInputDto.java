package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;

import java.time.LocalDate;
import java.util.Date;

public class EducationInputDto {


    private EducationLevel educationLevel;

    private String FieldOfStudy;

    private LocalDate graduationDate;

    private String educationPlace;


    public EducationInputDto(EducationLevel educationLevel, String fieldOfStudy, LocalDate graduationDate, String educationPlace) {
        this.educationLevel = educationLevel;
        FieldOfStudy = fieldOfStudy;
        this.graduationDate = graduationDate;
        this.educationPlace = educationPlace;
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
