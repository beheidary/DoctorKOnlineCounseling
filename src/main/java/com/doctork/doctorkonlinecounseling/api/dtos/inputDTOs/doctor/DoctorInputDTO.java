package com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor;

import java.time.LocalDate;

import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;
import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;





public class DoctorInputDTO {


    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    private String description;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @NotNull
    @NotBlank
    private String nationalCode;


    @NotNull
    private EducationLevel educationLevel;

    @NotNull
    private String physicianSystemCode;

    @NotNull
    private Gender gender;


    public DoctorInputDTO(Gender gender,String description,String firstName, String lastName, LocalDate dateOfBirth, String nationalCode, EducationLevel educationLevel, String physicianSystemCode) {
        this.firstName = firstName;
        this.gender = gender;
        this.description = description;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationalCode = nationalCode;
        this.educationLevel = educationLevel;
        this.physicianSystemCode = physicianSystemCode;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getDescription() {
        return description;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }


    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }
}
