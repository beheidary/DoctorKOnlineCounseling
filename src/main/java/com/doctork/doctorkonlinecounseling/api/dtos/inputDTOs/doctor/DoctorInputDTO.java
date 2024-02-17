package com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor;

import java.time.LocalDate;

import com.doctork.doctorkonlinecounseling.domain.doctor.EducationLevel;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;


public class DoctorInputDTO {


    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @NotNull
    @NotBlank
    private String nationalCode;

    @NotNull
    @NotBlank
    private String nationalId;

    @NotNull
    @NotBlank
    private String mobileNumber;

    @NotNull
    private EducationLevel educationLevel;
    @NotNull
    private Expertise expertise;

    @NotNull
    private String physicianSystemCode;


    public DoctorInputDTO(String firstName, String lastName, LocalDate dateOfBirth, String nationalCode, String nationalId, String mobileNumber, EducationLevel educationLevel, Expertise expertise, String physicianSystemCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationalCode = nationalCode;
        this.nationalId = nationalId;
        this.mobileNumber = mobileNumber;
        this.educationLevel = educationLevel;
        this.expertise = expertise;
        this.physicianSystemCode = physicianSystemCode;
    }


    public String getFirstName() {
        return firstName;
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

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Expertise getExpertise() {
        return expertise;
    }

    public void setExpertise(Expertise expertise) {
        this.expertise = expertise;
    }

    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }
}
